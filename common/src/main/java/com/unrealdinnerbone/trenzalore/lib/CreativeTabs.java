package com.unrealdinnerbone.trenzalore.lib;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

public class CreativeTabs {

    public static final CreativeTabs INSTANCE = new CreativeTabs();

    public static final ResourceKey<CreativeModeTab> BUILDING_BLOCKS = CreativeModeTabs.BUILDING_BLOCKS;
    public static final ResourceKey<CreativeModeTab> COLORED_BLOCKS = CreativeModeTabs.COLORED_BLOCKS;
    public static final ResourceKey<CreativeModeTab> NATURAL_BLOCKS = CreativeModeTabs.NATURAL_BLOCKS;
    public static final ResourceKey<CreativeModeTab> FUNCTIONAL_BLOCKS = CreativeModeTabs.FUNCTIONAL_BLOCKS;
    public static final ResourceKey<CreativeModeTab> REDSTONE_BLOCKS = CreativeModeTabs.REDSTONE_BLOCKS;
    public static final ResourceKey<CreativeModeTab> TOOLS_AND_UTILITIES = CreativeModeTabs.TOOLS_AND_UTILITIES;
    public static final ResourceKey<CreativeModeTab> COMBAT = CreativeModeTabs.COMBAT;
    public static final ResourceKey<CreativeModeTab> FOOD_AND_DRINKS = CreativeModeTabs.FOOD_AND_DRINKS;
    public static final ResourceKey<CreativeModeTab> INGREDIENTS = CreativeModeTabs.INGREDIENTS;
    public static final ResourceKey<CreativeModeTab> SPAWN_EGGS = CreativeModeTabs.SPAWN_EGGS;
    public static final ResourceKey<CreativeModeTab> OP_BLOCKS = CreativeModeTabs.OP_BLOCKS;


    public ResourceKey<CreativeModeTab> buildingBlocks() {
        return BUILDING_BLOCKS;
    }

    public ResourceKey<CreativeModeTab> coloredBlocks() {
        return COLORED_BLOCKS;
    }

    public ResourceKey<CreativeModeTab> naturalBlocks() {
        return NATURAL_BLOCKS;
    }

    public ResourceKey<CreativeModeTab> functionalBlocks() {
        return FUNCTIONAL_BLOCKS;
    }

    public ResourceKey<CreativeModeTab> redstoneBlocks() {
        return REDSTONE_BLOCKS;
    }

    public ResourceKey<CreativeModeTab> toolsAndUtilities() {
        return TOOLS_AND_UTILITIES;
    }

    public ResourceKey<CreativeModeTab> combat() {
        return COMBAT;
    }

    public ResourceKey<CreativeModeTab> foodAndDrinks() {
        return FOOD_AND_DRINKS;
    }

    public ResourceKey<CreativeModeTab> ingredients() {
        return INGREDIENTS;
    }

    public ResourceKey<CreativeModeTab> spawnEggs() {
        return SPAWN_EGGS;
    }

    public ResourceKey<CreativeModeTab> opBlocks() {
        return OP_BLOCKS;
    }

}
