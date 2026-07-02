package com.ligrk.mizunoworkshop.registry;

import com.ligrk.mizunoworkshop.MizunoWorkshop;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MizunoWorkshop.MOD_ID);

    private static final List<MizunoBlockEntry> MIZUNO_BLOCKS = new ArrayList<>();

    public static final DeferredBlock<Block> MIZUNO_STONE = converted("mizuno_stone", Blocks.STONE);
    public static final DeferredBlock<Block> MIZUNO_GRANITE = converted("mizuno_granite", Blocks.GRANITE);
    public static final DeferredBlock<Block> MIZUNO_POLISHED_GRANITE = converted("mizuno_polished_granite", Blocks.POLISHED_GRANITE);
    public static final DeferredBlock<Block> MIZUNO_DIORITE = converted("mizuno_diorite", Blocks.DIORITE);
    public static final DeferredBlock<Block> MIZUNO_POLISHED_DIORITE = converted("mizuno_polished_diorite", Blocks.POLISHED_DIORITE);
    public static final DeferredBlock<Block> MIZUNO_ANDESITE = converted("mizuno_andesite", Blocks.ANDESITE);
    public static final DeferredBlock<Block> MIZUNO_POLISHED_ANDESITE = converted("mizuno_polished_andesite", Blocks.POLISHED_ANDESITE);
    public static final DeferredBlock<Block> MIZUNO_COBBLESTONE = converted("mizuno_cobblestone", Blocks.COBBLESTONE);
    public static final DeferredBlock<Block> MIZUNO_MOSSY_COBBLESTONE = converted("mizuno_mossy_cobblestone", Blocks.MOSSY_COBBLESTONE);
    public static final DeferredBlock<Block> MIZUNO_STONE_BRICKS = converted("mizuno_stone_bricks", Blocks.STONE_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_MOSSY_STONE_BRICKS = converted("mizuno_mossy_stone_bricks", Blocks.MOSSY_STONE_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_CRACKED_STONE_BRICKS = converted("mizuno_cracked_stone_bricks", Blocks.CRACKED_STONE_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_CHISELED_STONE_BRICKS = converted("mizuno_chiseled_stone_bricks", Blocks.CHISELED_STONE_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_DEEPSLATE = pillar("mizuno_deepslate", Blocks.DEEPSLATE);
    public static final DeferredBlock<Block> MIZUNO_COBBLED_DEEPSLATE = converted("mizuno_cobbled_deepslate", Blocks.COBBLED_DEEPSLATE);
    public static final DeferredBlock<Block> MIZUNO_POLISHED_DEEPSLATE = converted("mizuno_polished_deepslate", Blocks.POLISHED_DEEPSLATE);
    public static final DeferredBlock<Block> MIZUNO_DEEPSLATE_BRICKS = converted("mizuno_deepslate_bricks", Blocks.DEEPSLATE_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_CRACKED_DEEPSLATE_BRICKS = converted("mizuno_cracked_deepslate_bricks", Blocks.CRACKED_DEEPSLATE_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_DEEPSLATE_TILES = converted("mizuno_deepslate_tiles", Blocks.DEEPSLATE_TILES);
    public static final DeferredBlock<Block> MIZUNO_CRACKED_DEEPSLATE_TILES = converted("mizuno_cracked_deepslate_tiles", Blocks.CRACKED_DEEPSLATE_TILES);
    public static final DeferredBlock<Block> MIZUNO_CHISELED_DEEPSLATE = converted("mizuno_chiseled_deepslate", Blocks.CHISELED_DEEPSLATE);
    public static final DeferredBlock<Block> MIZUNO_TUFF = converted("mizuno_tuff", Blocks.TUFF);
    public static final DeferredBlock<Block> MIZUNO_CALCITE = converted("mizuno_calcite", Blocks.CALCITE);
    public static final DeferredBlock<Block> MIZUNO_BRICKS = converted("mizuno_bricks", Blocks.BRICKS);
    public static final DeferredBlock<Block> MIZUNO_MUD_BRICKS = converted("mizuno_mud_bricks", Blocks.MUD_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_PACKED_MUD = converted("mizuno_packed_mud", Blocks.PACKED_MUD);
    public static final DeferredBlock<Block> MIZUNO_SANDSTONE = converted("mizuno_sandstone", Blocks.SANDSTONE);
    public static final DeferredBlock<Block> MIZUNO_CUT_SANDSTONE = converted("mizuno_cut_sandstone", Blocks.CUT_SANDSTONE);
    public static final DeferredBlock<Block> MIZUNO_CHISELED_SANDSTONE = converted("mizuno_chiseled_sandstone", Blocks.CHISELED_SANDSTONE);
    public static final DeferredBlock<Block> MIZUNO_RED_SANDSTONE = converted("mizuno_red_sandstone", Blocks.RED_SANDSTONE);
    public static final DeferredBlock<Block> MIZUNO_CUT_RED_SANDSTONE = converted("mizuno_cut_red_sandstone", Blocks.CUT_RED_SANDSTONE);
    public static final DeferredBlock<Block> MIZUNO_CHISELED_RED_SANDSTONE = converted("mizuno_chiseled_red_sandstone", Blocks.CHISELED_RED_SANDSTONE);
    public static final DeferredBlock<Block> MIZUNO_PRISMARINE = converted("mizuno_prismarine", Blocks.PRISMARINE);
    public static final DeferredBlock<Block> MIZUNO_PRISMARINE_BRICKS = converted("mizuno_prismarine_bricks", Blocks.PRISMARINE_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_DARK_PRISMARINE = converted("mizuno_dark_prismarine", Blocks.DARK_PRISMARINE);
    public static final DeferredBlock<Block> MIZUNO_NETHERRACK = converted("mizuno_netherrack", Blocks.NETHERRACK);
    public static final DeferredBlock<Block> MIZUNO_NETHER_BRICKS = converted("mizuno_nether_bricks", Blocks.NETHER_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_RED_NETHER_BRICKS = converted("mizuno_red_nether_bricks", Blocks.RED_NETHER_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_CRACKED_NETHER_BRICKS = converted("mizuno_cracked_nether_bricks", Blocks.CRACKED_NETHER_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_CHISELED_NETHER_BRICKS = converted("mizuno_chiseled_nether_bricks", Blocks.CHISELED_NETHER_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_BLACKSTONE = converted("mizuno_blackstone", Blocks.BLACKSTONE);
    public static final DeferredBlock<Block> MIZUNO_POLISHED_BLACKSTONE = converted("mizuno_polished_blackstone", Blocks.POLISHED_BLACKSTONE);
    public static final DeferredBlock<Block> MIZUNO_POLISHED_BLACKSTONE_BRICKS = converted("mizuno_polished_blackstone_bricks", Blocks.POLISHED_BLACKSTONE_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_CRACKED_POLISHED_BLACKSTONE_BRICKS = converted("mizuno_cracked_polished_blackstone_bricks", Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_CHISELED_POLISHED_BLACKSTONE = converted("mizuno_chiseled_polished_blackstone", Blocks.CHISELED_POLISHED_BLACKSTONE);
    public static final DeferredBlock<Block> MIZUNO_END_STONE = converted("mizuno_end_stone", Blocks.END_STONE);
    public static final DeferredBlock<Block> MIZUNO_END_STONE_BRICKS = converted("mizuno_end_stone_bricks", Blocks.END_STONE_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_PURPUR_BLOCK = converted("mizuno_purpur_block", Blocks.PURPUR_BLOCK);
    public static final DeferredBlock<Block> MIZUNO_PURPUR_PILLAR = pillar("mizuno_purpur_pillar", Blocks.PURPUR_PILLAR);
    public static final DeferredBlock<Block> MIZUNO_QUARTZ_BLOCK = converted("mizuno_quartz_block", Blocks.QUARTZ_BLOCK);
    public static final DeferredBlock<Block> MIZUNO_CHISELED_QUARTZ_BLOCK = converted("mizuno_chiseled_quartz_block", Blocks.CHISELED_QUARTZ_BLOCK);
    public static final DeferredBlock<Block> MIZUNO_QUARTZ_BRICKS = converted("mizuno_quartz_bricks", Blocks.QUARTZ_BRICKS);
    public static final DeferredBlock<Block> MIZUNO_QUARTZ_PILLAR = pillar("mizuno_quartz_pillar", Blocks.QUARTZ_PILLAR);
    public static final DeferredBlock<Block> MIZUNO_OAK_PLANKS = converted("mizuno_oak_planks", Blocks.OAK_PLANKS);
    public static final DeferredBlock<Block> MIZUNO_SPRUCE_PLANKS = converted("mizuno_spruce_planks", Blocks.SPRUCE_PLANKS);
    public static final DeferredBlock<Block> MIZUNO_BIRCH_PLANKS = converted("mizuno_birch_planks", Blocks.BIRCH_PLANKS);
    public static final DeferredBlock<Block> MIZUNO_JUNGLE_PLANKS = converted("mizuno_jungle_planks", Blocks.JUNGLE_PLANKS);
    public static final DeferredBlock<Block> MIZUNO_ACACIA_PLANKS = converted("mizuno_acacia_planks", Blocks.ACACIA_PLANKS);
    public static final DeferredBlock<Block> MIZUNO_DARK_OAK_PLANKS = converted("mizuno_dark_oak_planks", Blocks.DARK_OAK_PLANKS);
    public static final DeferredBlock<Block> MIZUNO_MANGROVE_PLANKS = converted("mizuno_mangrove_planks", Blocks.MANGROVE_PLANKS);
    public static final DeferredBlock<Block> MIZUNO_CHERRY_PLANKS = converted("mizuno_cherry_planks", Blocks.CHERRY_PLANKS);
    public static final DeferredBlock<Block> MIZUNO_BAMBOO_PLANKS = converted("mizuno_bamboo_planks", Blocks.BAMBOO_PLANKS);
    public static final DeferredBlock<Block> MIZUNO_BAMBOO_MOSAIC = converted("mizuno_bamboo_mosaic", Blocks.BAMBOO_MOSAIC);
    public static final DeferredBlock<Block> MIZUNO_CRIMSON_PLANKS = converted("mizuno_crimson_planks", Blocks.CRIMSON_PLANKS);
    public static final DeferredBlock<Block> MIZUNO_WARPED_PLANKS = converted("mizuno_warped_planks", Blocks.WARPED_PLANKS);
    public static final DeferredBlock<Block> MIZUNO_OAK_LOG = pillar("mizuno_oak_log", Blocks.OAK_LOG);
    public static final DeferredBlock<Block> MIZUNO_SPRUCE_LOG = pillar("mizuno_spruce_log", Blocks.SPRUCE_LOG);
    public static final DeferredBlock<Block> MIZUNO_BIRCH_LOG = pillar("mizuno_birch_log", Blocks.BIRCH_LOG);
    public static final DeferredBlock<Block> MIZUNO_JUNGLE_LOG = pillar("mizuno_jungle_log", Blocks.JUNGLE_LOG);
    public static final DeferredBlock<Block> MIZUNO_ACACIA_LOG = pillar("mizuno_acacia_log", Blocks.ACACIA_LOG);
    public static final DeferredBlock<Block> MIZUNO_DARK_OAK_LOG = pillar("mizuno_dark_oak_log", Blocks.DARK_OAK_LOG);
    public static final DeferredBlock<Block> MIZUNO_MANGROVE_LOG = pillar("mizuno_mangrove_log", Blocks.MANGROVE_LOG);
    public static final DeferredBlock<Block> MIZUNO_CHERRY_LOG = pillar("mizuno_cherry_log", Blocks.CHERRY_LOG);
    public static final DeferredBlock<Block> MIZUNO_CRIMSON_STEM = pillar("mizuno_crimson_stem", Blocks.CRIMSON_STEM);
    public static final DeferredBlock<Block> MIZUNO_WARPED_STEM = pillar("mizuno_warped_stem", Blocks.WARPED_STEM);
    public static final DeferredBlock<Block> MIZUNO_BAMBOO_BLOCK = pillar("mizuno_bamboo_block", Blocks.BAMBOO_BLOCK);
    public static final DeferredBlock<Block> MIZUNO_TERRACOTTA = converted("mizuno_terracotta", Blocks.TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_WHITE_TERRACOTTA = converted("mizuno_white_terracotta", Blocks.WHITE_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_LIGHT_GRAY_TERRACOTTA = converted("mizuno_light_gray_terracotta", Blocks.LIGHT_GRAY_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_GRAY_TERRACOTTA = converted("mizuno_gray_terracotta", Blocks.GRAY_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_BLACK_TERRACOTTA = converted("mizuno_black_terracotta", Blocks.BLACK_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_BROWN_TERRACOTTA = converted("mizuno_brown_terracotta", Blocks.BROWN_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_RED_TERRACOTTA = converted("mizuno_red_terracotta", Blocks.RED_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_ORANGE_TERRACOTTA = converted("mizuno_orange_terracotta", Blocks.ORANGE_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_YELLOW_TERRACOTTA = converted("mizuno_yellow_terracotta", Blocks.YELLOW_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_LIME_TERRACOTTA = converted("mizuno_lime_terracotta", Blocks.LIME_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_GREEN_TERRACOTTA = converted("mizuno_green_terracotta", Blocks.GREEN_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_CYAN_TERRACOTTA = converted("mizuno_cyan_terracotta", Blocks.CYAN_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_LIGHT_BLUE_TERRACOTTA = converted("mizuno_light_blue_terracotta", Blocks.LIGHT_BLUE_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_BLUE_TERRACOTTA = converted("mizuno_blue_terracotta", Blocks.BLUE_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_PURPLE_TERRACOTTA = converted("mizuno_purple_terracotta", Blocks.PURPLE_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_MAGENTA_TERRACOTTA = converted("mizuno_magenta_terracotta", Blocks.MAGENTA_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_PINK_TERRACOTTA = converted("mizuno_pink_terracotta", Blocks.PINK_TERRACOTTA);
    public static final DeferredBlock<Block> MIZUNO_WHITE_CONCRETE = converted("mizuno_white_concrete", Blocks.WHITE_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_LIGHT_GRAY_CONCRETE = converted("mizuno_light_gray_concrete", Blocks.LIGHT_GRAY_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_GRAY_CONCRETE = converted("mizuno_gray_concrete", Blocks.GRAY_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_BLACK_CONCRETE = converted("mizuno_black_concrete", Blocks.BLACK_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_BROWN_CONCRETE = converted("mizuno_brown_concrete", Blocks.BROWN_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_RED_CONCRETE = converted("mizuno_red_concrete", Blocks.RED_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_ORANGE_CONCRETE = converted("mizuno_orange_concrete", Blocks.ORANGE_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_YELLOW_CONCRETE = converted("mizuno_yellow_concrete", Blocks.YELLOW_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_LIME_CONCRETE = converted("mizuno_lime_concrete", Blocks.LIME_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_GREEN_CONCRETE = converted("mizuno_green_concrete", Blocks.GREEN_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_CYAN_CONCRETE = converted("mizuno_cyan_concrete", Blocks.CYAN_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_LIGHT_BLUE_CONCRETE = converted("mizuno_light_blue_concrete", Blocks.LIGHT_BLUE_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_BLUE_CONCRETE = converted("mizuno_blue_concrete", Blocks.BLUE_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_PURPLE_CONCRETE = converted("mizuno_purple_concrete", Blocks.PURPLE_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_MAGENTA_CONCRETE = converted("mizuno_magenta_concrete", Blocks.MAGENTA_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_PINK_CONCRETE = converted("mizuno_pink_concrete", Blocks.PINK_CONCRETE);
    public static final DeferredBlock<Block> MIZUNO_WHITE_WOOL = converted("mizuno_white_wool", Blocks.WHITE_WOOL);
    public static final DeferredBlock<Block> MIZUNO_LIGHT_GRAY_WOOL = converted("mizuno_light_gray_wool", Blocks.LIGHT_GRAY_WOOL);
    public static final DeferredBlock<Block> MIZUNO_GRAY_WOOL = converted("mizuno_gray_wool", Blocks.GRAY_WOOL);
    public static final DeferredBlock<Block> MIZUNO_BLACK_WOOL = converted("mizuno_black_wool", Blocks.BLACK_WOOL);
    public static final DeferredBlock<Block> MIZUNO_BROWN_WOOL = converted("mizuno_brown_wool", Blocks.BROWN_WOOL);
    public static final DeferredBlock<Block> MIZUNO_RED_WOOL = converted("mizuno_red_wool", Blocks.RED_WOOL);
    public static final DeferredBlock<Block> MIZUNO_ORANGE_WOOL = converted("mizuno_orange_wool", Blocks.ORANGE_WOOL);
    public static final DeferredBlock<Block> MIZUNO_YELLOW_WOOL = converted("mizuno_yellow_wool", Blocks.YELLOW_WOOL);
    public static final DeferredBlock<Block> MIZUNO_LIME_WOOL = converted("mizuno_lime_wool", Blocks.LIME_WOOL);
    public static final DeferredBlock<Block> MIZUNO_GREEN_WOOL = converted("mizuno_green_wool", Blocks.GREEN_WOOL);
    public static final DeferredBlock<Block> MIZUNO_CYAN_WOOL = converted("mizuno_cyan_wool", Blocks.CYAN_WOOL);
    public static final DeferredBlock<Block> MIZUNO_LIGHT_BLUE_WOOL = converted("mizuno_light_blue_wool", Blocks.LIGHT_BLUE_WOOL);
    public static final DeferredBlock<Block> MIZUNO_BLUE_WOOL = converted("mizuno_blue_wool", Blocks.BLUE_WOOL);
    public static final DeferredBlock<Block> MIZUNO_PURPLE_WOOL = converted("mizuno_purple_wool", Blocks.PURPLE_WOOL);
    public static final DeferredBlock<Block> MIZUNO_MAGENTA_WOOL = converted("mizuno_magenta_wool", Blocks.MAGENTA_WOOL);
    public static final DeferredBlock<Block> MIZUNO_PINK_WOOL = converted("mizuno_pink_wool", Blocks.PINK_WOOL);
    public static final DeferredBlock<Block> MIZUNO_GLASS = converted("mizuno_glass", Blocks.GLASS);
    public static final DeferredBlock<Block> MIZUNO_WHITE_STAINED_GLASS = converted("mizuno_white_stained_glass", Blocks.WHITE_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_LIGHT_GRAY_STAINED_GLASS = converted("mizuno_light_gray_stained_glass", Blocks.LIGHT_GRAY_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_GRAY_STAINED_GLASS = converted("mizuno_gray_stained_glass", Blocks.GRAY_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_BLACK_STAINED_GLASS = converted("mizuno_black_stained_glass", Blocks.BLACK_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_BROWN_STAINED_GLASS = converted("mizuno_brown_stained_glass", Blocks.BROWN_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_RED_STAINED_GLASS = converted("mizuno_red_stained_glass", Blocks.RED_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_ORANGE_STAINED_GLASS = converted("mizuno_orange_stained_glass", Blocks.ORANGE_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_YELLOW_STAINED_GLASS = converted("mizuno_yellow_stained_glass", Blocks.YELLOW_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_LIME_STAINED_GLASS = converted("mizuno_lime_stained_glass", Blocks.LIME_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_GREEN_STAINED_GLASS = converted("mizuno_green_stained_glass", Blocks.GREEN_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_CYAN_STAINED_GLASS = converted("mizuno_cyan_stained_glass", Blocks.CYAN_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_LIGHT_BLUE_STAINED_GLASS = converted("mizuno_light_blue_stained_glass", Blocks.LIGHT_BLUE_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_BLUE_STAINED_GLASS = converted("mizuno_blue_stained_glass", Blocks.BLUE_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_PURPLE_STAINED_GLASS = converted("mizuno_purple_stained_glass", Blocks.PURPLE_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_MAGENTA_STAINED_GLASS = converted("mizuno_magenta_stained_glass", Blocks.MAGENTA_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_PINK_STAINED_GLASS = converted("mizuno_pink_stained_glass", Blocks.PINK_STAINED_GLASS);
    public static final DeferredBlock<Block> MIZUNO_COAL_BLOCK = converted("mizuno_coal_block", Blocks.COAL_BLOCK);
    public static final DeferredBlock<Block> MIZUNO_IRON_BLOCK = converted("mizuno_iron_block", Blocks.IRON_BLOCK);
    public static final DeferredBlock<Block> MIZUNO_GOLD_BLOCK = converted("mizuno_gold_block", Blocks.GOLD_BLOCK);
    public static final DeferredBlock<Block> MIZUNO_COPPER_BLOCK = converted("mizuno_copper_block", Blocks.COPPER_BLOCK);
    public static final DeferredBlock<Block> MIZUNO_EXPOSED_COPPER = converted("mizuno_exposed_copper", Blocks.EXPOSED_COPPER);
    public static final DeferredBlock<Block> MIZUNO_WEATHERED_COPPER = converted("mizuno_weathered_copper", Blocks.WEATHERED_COPPER);
    public static final DeferredBlock<Block> MIZUNO_OXIDIZED_COPPER = converted("mizuno_oxidized_copper", Blocks.OXIDIZED_COPPER);
    public static final DeferredBlock<Block> MIZUNO_CUT_COPPER = converted("mizuno_cut_copper", Blocks.CUT_COPPER);
    public static final DeferredBlock<Block> MIZUNO_DIAMOND_BLOCK = converted("mizuno_diamond_block", Blocks.DIAMOND_BLOCK);
    public static final DeferredBlock<Block> MIZUNO_EMERALD_BLOCK = converted("mizuno_emerald_block", Blocks.EMERALD_BLOCK);
    public static final DeferredBlock<Block> MIZUNO_LAPIS_BLOCK = converted("mizuno_lapis_block", Blocks.LAPIS_BLOCK);
    public static final DeferredBlock<Block> MIZUNO_REDSTONE_BLOCK = converted("mizuno_redstone_block", Blocks.REDSTONE_BLOCK);
    public static final DeferredBlock<Block> MIZUNO_AMETHYST_BLOCK = converted("mizuno_amethyst_block", Blocks.AMETHYST_BLOCK);
    public static final DeferredBlock<Block> MIZUNO_RAW_IRON_BLOCK = converted("mizuno_raw_iron_block", Blocks.RAW_IRON_BLOCK);
    public static final DeferredBlock<Block> MIZUNO_RAW_GOLD_BLOCK = converted("mizuno_raw_gold_block", Blocks.RAW_GOLD_BLOCK);
    public static final DeferredBlock<Block> MIZUNO_RAW_COPPER_BLOCK = converted("mizuno_raw_copper_block", Blocks.RAW_COPPER_BLOCK);

    private ModBlocks() {
    }

    public static List<MizunoBlockEntry> mizunoBlocks() {
        return Collections.unmodifiableList(MIZUNO_BLOCKS);
    }

    private static DeferredBlock<Block> converted(String name, Block vanillaBlock) {
        // 普通方块复制原版属性，只替换模型、贴图、掉落与合成。
        DeferredBlock<Block> block = BLOCKS.register(
                name,
                () -> new Block(BlockBehaviour.Properties.ofFullCopy(vanillaBlock))
        );
        registerMizunoBlock(name, vanillaBlock, block);
        return block;
    }

    private static DeferredBlock<Block> pillar(String name, Block vanillaBlock) {
        // 原木、柱子等保留原版轴向状态，避免放置方向丢失。
        DeferredBlock<Block> block = BLOCKS.register(
                name,
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(vanillaBlock))
        );
        registerMizunoBlock(name, vanillaBlock, block);
        return block;
    }

    private static void registerMizunoBlock(String name, Block vanillaBlock, DeferredBlock<Block> block) {
        blockItem(name, block);
        MIZUNO_BLOCKS.add(new MizunoBlockEntry(name, vanillaBlock, block));
    }

    private static <T extends Block> DeferredItem<BlockItem> blockItem(String name, Supplier<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public record MizunoBlockEntry(String name, Block input, Supplier<? extends Block> output) {
    }
}
