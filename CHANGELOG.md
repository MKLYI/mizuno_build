# Changelog

## [Unreleased]

### Added

- 添加 `Mizuno 元素` 物品，使用由附魔之瓶改色得到的淡蓝色贴图。
- 添加 `Mizuno 元素` 配方：两个圆石无序合成。
- 为全部 Mizuno 方块添加 `原版方块 + Mizuno 元素` 无序合成配方。

### Changed

- Mizuno 方块获取方式改为普通合成，不再通过 Mizuno Workshop 转换。
- 资源生成脚本现在会生成元素贴图、元素配方和每个 Mizuno 方块的普通合成配方。
- 创造模式标签页图标改为 `Mizuno 元素`。

### Removed

- 移除 Mizuno Workshop 方块、菜单、GUI、旧合成表和旧掉落表。
- 移除 JEI 自定义转换分类和相关编译依赖。

## [0.1.0] - 2026-07-01

### Added

- 创建 `mizuno_build` NeoForge 模组，目标 Minecraft 版本为 `1.21.1`。
- 注册 158 个 Mizuno 风格建筑方块，覆盖石材、木材、陶瓦、混凝土、羊毛、玻璃和矿物块等类别。
- 添加创造模式标签页，便于集中查看 Mizuno 方块。
- 添加中英文语言文件、方块模型、方块状态和掉落表。
- 添加资源生成脚本 `scripts/generate_resources.ps1`。
