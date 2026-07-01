# Mizuno Build 项目说明

## 项目定位

`Mizuno Build` 是一个面向 Minecraft `1.21.1` 的 NeoForge 模组，mod id 为 `mizuno_build`。它的目标不是替换原版材质，而是把水野工艺风格的建筑材质注册成新的方块，使原版方块和 Mizuno 风格方块可以在同一个世界中并存。

当前实现包含 `158` 个可转换建筑方块，并提供一个类似切石机的 `Mizuno Workshop` 工作台：左侧放入支持的原版方块，右侧输出对应的 Mizuno 方块。

## 前提条件

- Minecraft `1.21.1`
- NeoForge `21.1.234`，版本范围配置为 `[21.1,)`
- Java `21`
- Gradle Wrapper 已包含在仓库中，优先使用 `gradlew.bat`
- JEI 为可选兼容项，编译 API 为 `jei-1.21.1-neoforge-api:19.27.0.343`
- 素材来源依赖本地水野工艺资源包纹理，生成脚本默认读取 `../水野工艺/assets/minecraft/textures/block`

注意：水野工艺材质通常受原作者版权或授权限制。公开源码或上传 GitHub 前，应确认是否允许分发纹理文件；如果不允许，应只发布代码和生成脚本，不发布受限素材。

## 目录结构

```text
mizuno_workshop/
+-- build.gradle
+-- gradle.properties
+-- scripts/
|   +-- generate_resources.ps1
+-- src/main/
    +-- java/com/ligrk/mizunoworkshop/
    |   +-- MizunoWorkshop.java
    |   +-- block/
    |   +-- client/
    |   +-- compat/jei/
    |   +-- menu/
    |   +-- registry/
    +-- resources/
        +-- META-INF/neoforge.mods.toml
        +-- assets/mizuno_build/
        +-- data/mizuno_build/
```

## 核心架构

- `MizunoWorkshop.java` 是模组入口，注册方块、物品、菜单和创造模式标签页。
- `registry/ModBlocks.java` 是核心注册表，负责注册 `mizuno_workshop` 工作台、所有 Mizuno 方块，以及 `CONVERSIONS` 原版方块到 Mizuno 方块的转换表。
- 普通完整方块使用 `converted(...)` 注册；原木、柱子、深板岩等有方向的方块使用 `pillar(...)` 注册为 `RotatedPillarBlock`。
- `block/MizunoWorkshopBlock.java` 负责右键打开菜单。
- `menu/MizunoWorkshopMenu.java` 实现一个输入槽和一个输出槽，不使用 `BlockEntity`，转换结果直接来自 `ModBlocks.CONVERSIONS`。
- `menu/MizunoResultSlot.java` 在玩家取走输出时消耗一个输入物品。
- `client/MizunoWorkshopClient.java` 注册工作台界面，并把 Mizuno 玻璃类方块设置为透明渲染。
- `compat/jei/` 提供 JEI 兼容，把同一份转换表显示为 JEI 配方，工作台本身注册为配方催化剂。

## 资源与数据

资源命名空间统一为 `mizuno_build`：

- `assets/mizuno_build/blockstates`：方块状态，柱状方块包含 `axis=x/y/z`
- `assets/mizuno_build/models/block`：方块模型
- `assets/mizuno_build/models/item`：物品模型
- `assets/mizuno_build/textures/block`：Mizuno 方块纹理
- `assets/mizuno_build/textures/gui/container`：工作台 GUI
- `assets/mizuno_build/lang/en_us.json` 与 `zh_cn.json`：本地化文本
- `data/mizuno_build/recipe/mizuno_workshop.json`：工作台合成配方
- `data/mizuno_build/loot_table/blocks`：方块掉落表

资源可通过脚本重新生成：

```powershell
cd D:\game\文件\mizuno_workshop
powershell -NoProfile -ExecutionPolicy Bypass -File scripts\generate_resources.ps1
```

如果 Windows PowerShell 5.1 对中文路径或 UTF-8 文件处理异常，优先使用 PowerShell 7，或确认脚本与源码以 UTF-8 保存。

## 构建、运行与测试

```powershell
cd D:\game\文件\mizuno_workshop
.\gradlew.bat --no-daemon clean build
```

构建成功后，测试用 jar 位于：

```text
build\libs\mizuno_build-0.1.0.jar
```

放入客户端 `mods` 目录后即可在 Minecraft `1.21.1 + NeoForge` 环境测试。开发环境内可运行：

```powershell
.\gradlew.bat runClient
```

建议测试清单：

- 新建世界后确认 `Mizuno Build` 创造模式标签页存在。
- 合成并放置 `Mizuno Workshop`。
- 投入原版石头、木板、原木、玻璃、陶瓦、混凝土、羊毛等方块，确认输出正确。
- 测试 Shift 点击输入、输出和背包移动。
- 检查玻璃透明度、柱状方块朝向、掉落物和中文名称。
- 安装 JEI 后确认转换配方与工作台催化剂显示正常；不安装 JEI 时模组应正常加载。

## 维护约定

新增转换方块时，优先只修改 `ModBlocks.java` 的注册列表，然后运行资源生成脚本同步生成模型、方块状态、语言文件、掉落表和相关资源。不要手动维护一份独立 JEI 配方列表，JEI 应继续从 `ModBlocks.convertedBlocks()` 读取转换数据，避免菜单逻辑和展示逻辑不一致。

当前实现重点覆盖建筑用完整方块；楼梯、台阶、墙、门、活板门等非完整方块如果后续加入，需要额外处理方块状态、碰撞箱、旋转和模型，不应简单套用 `converted(...)`。
