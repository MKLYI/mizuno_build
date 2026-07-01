# Changelog

本文件用于记录 `Mizuno Build` 的重要变更，便于后续发布、回溯和排查问题。

格式参考 [Keep a Changelog](https://keepachangelog.com/zh-CN/1.1.0/)，版本号跟随 `gradle.properties` 中的 `mod_version`。

## [Unreleased]

### Added

- 暂无。

### Changed

- 暂无。

### Fixed

- 暂无。

## [0.1.0] - 2026-07-01

### Added

- 创建 `mizuno_build` NeoForge 模组，目标 Minecraft 版本为 `1.21.1`。
- 添加 `Mizuno Workshop` 工作台，用于把支持的原版建筑方块转换为对应的 Mizuno 风格方块。
- 注册 158 个 Mizuno 风格建筑方块，覆盖石材、木材、陶瓦、混凝土、羊毛、玻璃和矿物块等类别。
- 添加创造模式标签页，便于在创造模式中集中查看 Mizuno 方块。
- 添加中英文语言文件，方块名称采用 `Mizuno + 原版方块名` 的形式。
- 添加工作台合成配方和方块掉落表。
- 添加 JEI 可选兼容，显示所有转换配方，并将 `Mizuno Workshop` 注册为配方催化剂。
- 添加资源生成脚本 `scripts/generate_resources.ps1`，用于从注册列表和本地纹理源生成模型、方块状态、语言文件、掉落表等资源。

### Changed

- 将模组命名空间统一为 `mizuno_build`，避免旧命名空间残留导致资源或注册项不一致。
- 将原木、柱子、深板岩、竹块等有方向的方块注册为 `RotatedPillarBlock`，支持正确的轴向状态。

### Fixed

- 清理旧的 `mizuno_workshop` 资源命名空间，避免打包后存在失效资源。
- 修正柱状方块的方块状态生成，支持 `axis=x/y/z`。
- 为 Mizuno 玻璃和染色玻璃注册透明渲染层，避免显示为不透明方块。
