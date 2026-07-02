$ErrorActionPreference = 'Stop'

$projectRoot = (Resolve-Path (Join-Path $PSScriptRoot '..')).Path
$resources = Join-Path $projectRoot 'src/main/resources'
$javaFile = Join-Path $projectRoot 'src/main/java/com/ligrk/mizunoworkshop/registry/ModBlocks.java'
$sourcePack = Get-ChildItem -Path (Join-Path $projectRoot '..') -Directory |
    Where-Object { Test-Path (Join-Path $_.FullName 'assets/minecraft/textures/block') } |
    Select-Object -First 1
if ($null -eq $sourcePack) {
    throw 'Missing source resource pack: assets/minecraft/textures/block'
}
$sourceTextures = (Resolve-Path (Join-Path $sourcePack.FullName 'assets/minecraft/textures/block')).Path
$sourceItemTextures = (Resolve-Path (Join-Path $sourcePack.FullName 'assets/minecraft/textures/item')).Path

$assetRoot = Join-Path $resources 'assets/mizuno_build'
$dataRoot = Join-Path $resources 'data/mizuno_build'

$oldAssets = Join-Path $resources 'assets/mizuno_workshop'
$oldData = Join-Path $resources 'data/mizuno_workshop'
if (Test-Path $oldAssets) { Remove-Item -Path $oldAssets -Recurse -Force }
if (Test-Path $oldData) { Remove-Item -Path $oldData -Recurse -Force }

@(
    "$assetRoot/blockstates",
    "$assetRoot/lang",
    "$assetRoot/models/block",
    "$assetRoot/models/item",
    "$assetRoot/textures/block",
    "$assetRoot/textures/item",
    "$dataRoot/recipe",
    "$dataRoot/loot_table/blocks"
) | ForEach-Object { New-Item -ItemType Directory -Path $_ -Force | Out-Null }

@(
    "$assetRoot/blockstates/mizuno_workshop.json",
    "$assetRoot/models/block/mizuno_workshop.json",
    "$assetRoot/models/item/mizuno_workshop.json",
    "$assetRoot/textures/gui/container/mizuno_workshop.png",
    "$dataRoot/loot_table/blocks/mizuno_workshop.json",
    "$dataRoot/recipe/mizuno_workshop.json"
) | ForEach-Object {
    if (Test-Path $_) {
        Remove-Item -LiteralPath $_ -Force
    }
}

function Write-Utf8NoBom($Path, $Text) {
    $encoding = [System.Text.UTF8Encoding]::new($false)
    [System.IO.File]::WriteAllText($Path, $Text, $encoding)
}

function To-JsonText($Value) {
    return ($Value | ConvertTo-Json -Depth 12)
}

function Copy-Texture($Name) {
    $src = Join-Path $sourceTextures "$Name.png"
    if (-not (Test-Path $src)) {
        throw "Missing texture: $Name.png"
    }
    $dst = Join-Path $assetRoot "textures/block/$Name.png"
    Copy-Item -Path $src -Destination $dst -Force
    $mcmeta = "$src.mcmeta"
    if (Test-Path $mcmeta) {
        Copy-Item -Path $mcmeta -Destination "$dst.mcmeta" -Force
    }
}

function Write-MizunoElementTexture {
    $src = Join-Path $sourceItemTextures 'experience_bottle.png'
    if (-not (Test-Path $src)) {
        throw 'Missing texture: experience_bottle.png'
    }

    $dst = Join-Path $assetRoot 'textures/item/mizuno_element.png'
    Add-Type -AssemblyName System.Drawing
    $bitmap = [System.Drawing.Bitmap]::new($src)
    try {
        for ($y = 0; $y -lt $bitmap.Height; $y++) {
            for ($x = 0; $x -lt $bitmap.Width; $x++) {
                $pixel = $bitmap.GetPixel($x, $y)
                if ($pixel.A -eq 0) {
                    continue
                }

                $greenDominant = $pixel.G -gt 80 -and $pixel.G -ge $pixel.B + 18 -and $pixel.G -ge $pixel.R - 20
                $yellowGreen = $pixel.R -gt 80 -and $pixel.G -gt 90 -and $pixel.B -lt 90
                if ($greenDominant -or $yellowGreen) {
                    $brightness = [Math]::Max($pixel.R, [Math]::Max($pixel.G, $pixel.B))
                    $red = [Math]::Min(255, [int]($brightness * 0.48))
                    $green = [Math]::Min(255, [int]($brightness * 0.88))
                    $blue = [Math]::Min(255, [int]($brightness * 1.12 + 20))
                    $bitmap.SetPixel($x, $y, [System.Drawing.Color]::FromArgb($pixel.A, $red, $green, $blue))
                }
            }
        }
        $bitmap.Save($dst, [System.Drawing.Imaging.ImageFormat]::Png)
    } finally {
        $bitmap.Dispose()
    }
}

$special = @{
    deepslate = @{ type = 'column'; side = 'deepslate'; top = 'deepslate_top' }
    sandstone = @{ type = 'bottom_top'; side = 'sandstone'; bottom = 'sandstone_bottom'; top = 'sandstone_top' }
    chiseled_sandstone = @{ type = 'column'; side = 'chiseled_sandstone'; top = 'chiseled_sandstone_top' }
    red_sandstone = @{ type = 'bottom_top'; side = 'red_sandstone'; bottom = 'red_sandstone_bottom'; top = 'red_sandstone_top' }
    chiseled_red_sandstone = @{ type = 'column'; side = 'chiseled_red_sandstone'; top = 'chiseled_red_sandstone_top' }
    blackstone = @{ type = 'column'; side = 'blackstone'; top = 'blackstone_top' }
    purpur_pillar = @{ type = 'column'; side = 'purpur_pillar'; top = 'purpur_pillar_top' }
    quartz_block = @{ type = 'bottom_top'; side = 'quartz_block_side'; bottom = 'quartz_block_bottom'; top = 'quartz_block_top' }
    chiseled_quartz_block = @{ type = 'column'; side = 'chiseled_quartz_block'; top = 'chiseled_quartz_block_top' }
    quartz_pillar = @{ type = 'column'; side = 'quartz_pillar'; top = 'quartz_pillar_top' }
    bamboo_block = @{ type = 'column'; side = 'bamboo_block'; top = 'bamboo_block_top' }
}

foreach ($wood in @('oak', 'spruce', 'birch', 'jungle', 'acacia', 'dark_oak', 'mangrove', 'cherry')) {
    $special["${wood}_log"] = @{ type = 'column'; side = "${wood}_log"; top = "${wood}_log_top" }
}
foreach ($stem in @('crimson', 'warped')) {
    $special["${stem}_stem"] = @{ type = 'column'; side = "${stem}_stem"; top = "${stem}_stem_top" }
}

$java = Get-Content -Raw -Path $javaFile
$matches = [regex]::Matches($java, '(converted|pillar)\("([^"]+)",\s*Blocks\.([A-Z0-9_]+)\)')
$blocks = @()
foreach ($match in $matches) {
    $kind = $match.Groups[1].Value
    $id = $match.Groups[2].Value
    $base = $id -replace '^mizuno_', ''
    $vanillaId = $match.Groups[3].Value.ToLowerInvariant()
    $blocks += [pscustomobject]@{ id = $id; base = $base; vanilla = $vanillaId; isPillar = ($kind -eq 'pillar') }
}

$existingZhPath = Join-Path $assetRoot 'lang/zh_cn.json'
$existingZh = @{}
if (Test-Path $existingZhPath) {
    $existingZhObject = Get-Content -Raw -Encoding UTF8 -Path $existingZhPath | ConvertFrom-Json
    foreach ($property in $existingZhObject.PSObject.Properties) {
        $existingZh[$property.Name] = $property.Value
    }
}

$zhItemGroup = if ($existingZh.ContainsKey('itemGroup.mizuno_build')) {
    $existingZh['itemGroup.mizuno_build']
} else {
    'Mizuno Build'
}
$zhElementName = -join ([char[]](77, 105, 122, 117, 110, 111, 32, 20803, 32032))

$langEn = [ordered]@{
    'itemGroup.mizuno_build' = 'Mizuno Build'
    'item.mizuno_build.mizuno_element' = 'Mizuno Element'
}
$langZh = [ordered]@{
    'itemGroup.mizuno_build' = $zhItemGroup
    'item.mizuno_build.mizuno_element' = $zhElementName
}

Write-MizunoElementTexture
Write-Utf8NoBom (Join-Path $assetRoot 'models/item/mizuno_element.json') (To-JsonText @{
    parent = 'minecraft:item/generated'
    textures = @{ layer0 = 'mizuno_build:item/mizuno_element' }
})

$loot = {
    param($ItemId)
    return @{
        type = 'minecraft:block'
        pools = @(
            @{
                rolls = 1
                entries = @(@{ type = 'minecraft:item'; name = $ItemId })
                conditions = @(@{ condition = 'minecraft:survives_explosion' })
            }
        )
    }
}

Write-Utf8NoBom (Join-Path $dataRoot 'recipe/mizuno_element.json') (To-JsonText @{
    type = 'minecraft:crafting_shapeless'
    category = 'misc'
    ingredients = @(
        @{ item = 'minecraft:cobblestone' },
        @{ item = 'minecraft:cobblestone' }
    )
    result = @{ count = 1; id = 'mizuno_build:mizuno_element' }
})

foreach ($block in $blocks) {
    $id = $block.id
    $baseName = $block.base
    $vanillaId = $block.vanilla
    $modelName = "mizuno_build:block/$id"

    if ($special.ContainsKey($baseName)) {
        $info = $special[$baseName]
        Copy-Texture $info.side
        Copy-Texture $info.top
        if ($info.type -eq 'bottom_top') {
            Copy-Texture $info.bottom
            $model = @{
                parent = 'minecraft:block/cube_bottom_top'
                textures = @{
                    side = "mizuno_build:block/$($info.side)"
                    bottom = "mizuno_build:block/$($info.bottom)"
                    top = "mizuno_build:block/$($info.top)"
                }
            }
        } else {
            $model = @{
                parent = 'minecraft:block/cube_column'
                textures = @{
                    side = "mizuno_build:block/$($info.side)"
                    end = "mizuno_build:block/$($info.top)"
                }
            }
            if ($block.isPillar) {
                Write-Utf8NoBom (Join-Path $assetRoot "blockstates/$id.json") (To-JsonText @{
                    variants = @{
                        'axis=y' = @{ model = $modelName }
                        'axis=x' = @{ model = $modelName; x = 90; y = 90 }
                        'axis=z' = @{ model = $modelName; x = 90 }
                    }
                })
            }
        }
    } else {
        Copy-Texture $baseName
        $model = @{
            parent = 'minecraft:block/cube_all'
            textures = @{ all = "mizuno_build:block/$baseName" }
        }
    }

    if ($baseName -eq 'glass' -or $baseName.EndsWith('_stained_glass')) {
        $model.render_type = 'minecraft:translucent'
    }

    if (-not $block.isPillar) {
        Write-Utf8NoBom (Join-Path $assetRoot "blockstates/$id.json") (To-JsonText @{
            variants = @{
                '' = @{ model = $modelName }
            }
        })
    }

    Write-Utf8NoBom (Join-Path $assetRoot "models/block/$id.json") (To-JsonText $model)
    Write-Utf8NoBom (Join-Path $assetRoot "models/item/$id.json") (To-JsonText @{ parent = $modelName })
    Write-Utf8NoBom (Join-Path $dataRoot "loot_table/blocks/$id.json") (To-JsonText (& $loot "mizuno_build:$id"))
    Write-Utf8NoBom (Join-Path $dataRoot "recipe/$id.json") (To-JsonText @{
        type = 'minecraft:crafting_shapeless'
        category = 'building'
        ingredients = @(
            @{ item = "minecraft:$vanillaId" },
            @{ item = 'mizuno_build:mizuno_element' }
        )
        result = @{ count = 1; id = "mizuno_build:$id" }
    })

    $englishBase = (Get-Culture).TextInfo.ToTitleCase(($baseName -replace '_', ' '))
    $langEn["block.mizuno_build.$id"] = "Mizuno $englishBase"
    $zhKey = "block.mizuno_build.$id"
    if ($existingZh.ContainsKey($zhKey)) {
        $langZh[$zhKey] = $existingZh[$zhKey]
    } else {
        $langZh[$zhKey] = "Mizuno $englishBase"
    }
}

Write-Utf8NoBom (Join-Path $assetRoot 'lang/en_us.json') (To-JsonText $langEn)
Write-Utf8NoBom (Join-Path $assetRoot 'lang/zh_cn.json') (To-JsonText $langZh)

Write-Host "Generated $($blocks.Count) Mizuno block resource sets."
