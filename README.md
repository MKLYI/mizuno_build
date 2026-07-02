# Mizuno Build 项目说明

`Mizuno Build` 是一个面向 Minecraft `1.21.1` 的 NeoForge 模组，mod id 为 `mizuno_build`。它把 Mizuno 风格的建筑材质注册为独立方块，让原版方块和 Mizuno 方块可以在同一个世界中并存。

当前实现包含 `158` 个 Mizuno 建筑方块。玩家先用两个圆石竖排合成 `2` 个 `Mizuno 元素`，再用 `原版方块 + Mizuno 元素` 无序合成对应的 Mizuno 方块。

## 前提条件

- Minecraft `1.21.1`
- NeoForge `21.1.234`
- Java `21`
- 使用仓库内的 `gradlew.bat`
- Mizuno 纹理来源依赖本地资源包，资源生成脚本会自动查找同级目录下包含 `assets/minecraft/textures` 的资源包

## 目录结构

```text
src/main/java/com/ligrk/mizunoworkshop/
+-- MizunoWorkshop.java
+-- registry/

src/main/resources/
+-- assets/mizuno_build/
+-- data/mizuno_build/
```

## 核心结构

- `MizunoWorkshop.java`：模组入口，注册方块、物品和创造模式标签页。
- `registry/ModItems.java`：注册 `mizuno_element`。
- `registry/ModBlocks.java`：注册全部 Mizuno 方块，并保留 `MizunoBlockEntry` 元数据供资源脚本生成合成表。
- `registry/ModCreativeTabs.java`：创造模式标签页，展示 `Mizuno 元素` 和全部 Mizuno 方块。
- `scripts/generate_resources.ps1`：从 `ModBlocks.java` 的注册列表生成模型、方块状态、掉落表、语言文件和普通合成配方。

## 构建与验证

```powershell
cd D:\game\文件\mizuno_workshop
.\gradlew.bat --no-daemon clean build
```

构建成功后，jar 位于：

```text
build\libs\mizuno_build-0.1.1.jar
```

建议在 NeoForge 客户端中检查：

- 两个圆石竖排能合成 `2` 个 `Mizuno 元素`。
- 原版方块和 `Mizuno 元素` 能合成对应 Mizuno 方块。
- 原木、石英柱等柱状方块放置方向正确。
- 玻璃和染色玻璃透明渲染正确。
- 方块掉落自身，创造模式标签页展示完整。

## 维护约定

新增 Mizuno 方块时，优先只修改 `ModBlocks.java` 的注册列表，然后运行：

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File scripts\generate_resources.ps1
```

脚本会同步生成模型、方块状态、语言文件、掉落表和 `原版方块 + Mizuno 元素` 合成配方。不要手动维护第二份方块配方列表。

## 素材与授权

本项目包含 Mizuno 派生纹理资源。公开发布前需要确认纹理再分发权限；如果只发布源码，建议让贡献者本地提供原资源包并运行生成脚本。
