$ErrorActionPreference = 'Stop'

$projectRoot = (Resolve-Path (Join-Path $PSScriptRoot '..')).Path
$resources = Join-Path $projectRoot 'src/main/resources'
$javaFile = Join-Path $projectRoot 'src/main/java/com/ligrk/mizunoworkshop/registry/ModBlocks.java'
$sourceTextures = (Resolve-Path (Join-Path $projectRoot '../水野工艺/assets/minecraft/textures/block')).Path

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
    "$assetRoot/textures/gui/container",
    "$dataRoot/recipe",
    "$dataRoot/loot_table/blocks"
) | ForEach-Object { [System.IO.Directory]::CreateDirectory($_) | Out-Null }

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

$zhFull = @{
    stone = '石头'
    granite = '花岗岩'
    polished_granite = '磨制花岗岩'
    diorite = '闪长岩'
    polished_diorite = '磨制闪长岩'
    andesite = '安山岩'
    polished_andesite = '磨制安山岩'
    cobblestone = '圆石'
    mossy_cobblestone = '苔石'
    stone_bricks = '石砖'
    mossy_stone_bricks = '苔石砖'
    cracked_stone_bricks = '裂纹石砖'
    chiseled_stone_bricks = '錾制石砖'
    deepslate = '深板岩'
    cobbled_deepslate = '深板岩圆石'
    polished_deepslate = '磨制深板岩'
    deepslate_bricks = '深板岩砖'
    cracked_deepslate_bricks = '裂纹深板岩砖'
    deepslate_tiles = '深板岩瓦'
    cracked_deepslate_tiles = '裂纹深板岩瓦'
    chiseled_deepslate = '錾制深板岩'
    tuff = '凝灰岩'
    calcite = '方解石'
    bricks = '红砖块'
    mud_bricks = '泥砖'
    packed_mud = '泥坯'
    sandstone = '砂岩'
    cut_sandstone = '切制砂岩'
    chiseled_sandstone = '錾制砂岩'
    red_sandstone = '红砂岩'
    cut_red_sandstone = '切制红砂岩'
    chiseled_red_sandstone = '錾制红砂岩'
    prismarine = '海晶石'
    prismarine_bricks = '海晶石砖'
    dark_prismarine = '暗海晶石'
    netherrack = '下界岩'
    nether_bricks = '下界砖块'
    red_nether_bricks = '红色下界砖块'
    cracked_nether_bricks = '裂纹下界砖块'
    chiseled_nether_bricks = '錾制下界砖块'
    blackstone = '黑石'
    polished_blackstone = '磨制黑石'
    polished_blackstone_bricks = '磨制黑石砖'
    cracked_polished_blackstone_bricks = '裂纹磨制黑石砖'
    chiseled_polished_blackstone = '錾制磨制黑石'
    end_stone = '末地石'
    end_stone_bricks = '末地石砖'
    purpur_block = '紫珀块'
    purpur_pillar = '紫珀柱'
    quartz_block = '石英块'
    chiseled_quartz_block = '錾制石英块'
    quartz_bricks = '石英砖'
    quartz_pillar = '石英柱'
    oak_planks = '橡木木板'
    spruce_planks = '云杉木板'
    birch_planks = '白桦木板'
    jungle_planks = '丛林木板'
    acacia_planks = '金合欢木板'
    dark_oak_planks = '深色橡木木板'
    mangrove_planks = '红树木板'
    cherry_planks = '樱花木板'
    bamboo_planks = '竹板'
    bamboo_mosaic = '竹马赛克'
    crimson_planks = '绯红木板'
    warped_planks = '诡异木板'
    oak_log = '橡木原木'
    spruce_log = '云杉原木'
    birch_log = '白桦原木'
    jungle_log = '丛林原木'
    acacia_log = '金合欢原木'
    dark_oak_log = '深色橡木原木'
    mangrove_log = '红树原木'
    cherry_log = '樱花原木'
    crimson_stem = '绯红菌柄'
    warped_stem = '诡异菌柄'
    bamboo_block = '竹块'
    terracotta = '陶瓦'
    glass = '玻璃'
    coal_block = '煤炭块'
    iron_block = '铁块'
    gold_block = '金块'
    copper_block = '铜块'
    exposed_copper = '斑驳的铜块'
    weathered_copper = '锈蚀的铜块'
    oxidized_copper = '氧化的铜块'
    cut_copper = '切制铜块'
    diamond_block = '钻石块'
    emerald_block = '绿宝石块'
    lapis_block = '青金石块'
    redstone_block = '红石块'
    amethyst_block = '紫水晶块'
    raw_iron_block = '粗铁块'
    raw_gold_block = '粗金块'
    raw_copper_block = '粗铜块'
}

$colorZh = @{
    white = '白色'
    light_gray = '淡灰色'
    gray = '灰色'
    black = '黑色'
    brown = '棕色'
    red = '红色'
    orange = '橙色'
    yellow = '黄色'
    lime = '黄绿色'
    green = '绿色'
    cyan = '青色'
    light_blue = '淡蓝色'
    blue = '蓝色'
    purple = '紫色'
    magenta = '品红色'
    pink = '粉红色'
}

function Get-ZhName($BaseName) {
    if ($zhFull.ContainsKey($BaseName)) {
        return $zhFull[$BaseName]
    }
    foreach ($color in $colorZh.Keys) {
        if ($BaseName -eq "${color}_terracotta") { return "$($colorZh[$color])陶瓦" }
        if ($BaseName -eq "${color}_concrete") { return "$($colorZh[$color])混凝土" }
        if ($BaseName -eq "${color}_wool") { return "$($colorZh[$color])羊毛" }
        if ($BaseName -eq "${color}_stained_glass") { return "$($colorZh[$color])染色玻璃" }
    }
    return (($BaseName -replace '_', ' ') -replace '\b(\w)', { param($m) $m.Value.ToUpper() })
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
$matches = [regex]::Matches($java, '(?:converted|pillar)\("([^"]+)",\s*Blocks\.([A-Z0-9_]+)\)')
$blocks = @()
foreach ($match in $matches) {
    $id = $match.Groups[1].Value
    $base = $id -replace '^mizuno_', ''
    $blocks += [pscustomobject]@{ id = $id; base = $base }
}

Copy-Item -Path (Join-Path $projectRoot '../水野工艺/assets/minecraft/textures/gui/container/stonecutter.png') -Destination (Join-Path $assetRoot 'textures/gui/container/mizuno_workshop.png') -Force

$langEn = [ordered]@{
    'block.mizuno_build.mizuno_workshop' = 'Mizuno Workshop'
    'container.mizuno_workshop' = 'Mizuno Workshop'
    'itemGroup.mizuno_build' = 'Mizuno Build'
    'jei.mizuno_build.mizuno_workshop' = 'Mizuno Workshop'
}
$langZh = [ordered]@{
    'block.mizuno_build.mizuno_workshop' = '水野工坊'
    'container.mizuno_workshop' = '水野工坊'
    'itemGroup.mizuno_build' = '水野建筑'
    'jei.mizuno_build.mizuno_workshop' = '水野工坊'
}

$workshopBlockstate = @{
    variants = @{
        '' = @{ model = 'mizuno_build:block/mizuno_workshop' }
    }
}
Write-Utf8NoBom (Join-Path $assetRoot 'blockstates/mizuno_workshop.json') (To-JsonText $workshopBlockstate)
Write-Utf8NoBom (Join-Path $assetRoot 'models/block/mizuno_workshop.json') (To-JsonText @{ parent = 'minecraft:block/stonecutter' })
Write-Utf8NoBom (Join-Path $assetRoot 'models/item/mizuno_workshop.json') (To-JsonText @{ parent = 'mizuno_build:block/mizuno_workshop' })

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

Write-Utf8NoBom (Join-Path $dataRoot 'loot_table/blocks/mizuno_workshop.json') (To-JsonText (& $loot 'mizuno_build:mizuno_workshop'))
Write-Utf8NoBom (Join-Path $dataRoot 'recipe/mizuno_workshop.json') (To-JsonText @{
    type = 'minecraft:crafting_shaped'
    category = 'decorations'
    pattern = @(' I ', 'SCS', 'SSS')
    key = @{
        I = 'minecraft:iron_ingot'
        S = 'minecraft:stone'
        C = 'minecraft:stonecutter'
    }
    result = @{ id = 'mizuno_build:mizuno_workshop' }
})

foreach ($block in $blocks) {
    $id = $block.id
    $baseName = $block.base
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
            Write-Utf8NoBom (Join-Path $assetRoot "blockstates/$id.json") (To-JsonText @{
                variants = @{
                    'axis=y' = @{ model = $modelName }
                    'axis=x' = @{ model = $modelName; x = 90; y = 90 }
                    'axis=z' = @{ model = $modelName; x = 90 }
                }
            })
        }
    } else {
        Copy-Texture $baseName
        $model = @{
            parent = 'minecraft:block/cube_all'
            textures = @{ all = "mizuno_build:block/$baseName" }
        }
    }

    if (-not ($special.ContainsKey($baseName) -and $special[$baseName].type -eq 'column')) {
        Write-Utf8NoBom (Join-Path $assetRoot "blockstates/$id.json") (To-JsonText @{
            variants = @{
                '' = @{ model = $modelName }
            }
        })
    }

    Write-Utf8NoBom (Join-Path $assetRoot "models/block/$id.json") (To-JsonText $model)
    Write-Utf8NoBom (Join-Path $assetRoot "models/item/$id.json") (To-JsonText @{ parent = $modelName })
    Write-Utf8NoBom (Join-Path $dataRoot "loot_table/blocks/$id.json") (To-JsonText (& $loot "mizuno_build:$id"))

    $englishBase = (Get-Culture).TextInfo.ToTitleCase(($baseName -replace '_', ' '))
    $langEn["block.mizuno_build.$id"] = "Mizuno $englishBase"
    $langZh["block.mizuno_build.$id"] = "Mizuno $(Get-ZhName $baseName)"
}

Write-Utf8NoBom (Join-Path $assetRoot 'lang/en_us.json') (To-JsonText $langEn)
Write-Utf8NoBom (Join-Path $assetRoot 'lang/zh_cn.json') (To-JsonText $langZh)

Write-Host "Generated $($blocks.Count) converted block resource sets."
