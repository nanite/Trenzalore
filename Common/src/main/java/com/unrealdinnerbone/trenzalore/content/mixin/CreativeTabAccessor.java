package com.unrealdinnerbone.trenzalore.content.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CreativeModeTabs.class)
public interface CreativeTabAccessor {

    @Accessor("BUILDING_BLOCKS")
    static ResourceKey<CreativeModeTab> buildingBlocks() {
        throw new UnsupportedOperationException("Mixin failed to apply");
    }
    @Accessor("COLORED_BLOCKS")
    static ResourceKey<CreativeModeTab> coloredBlocks() {
        throw new UnsupportedOperationException("Mixin failed to apply");
    }
    @Accessor("NATURAL_BLOCKS")
    static ResourceKey<CreativeModeTab> naturalBlocks() {
        throw new UnsupportedOperationException("Mixin failed to apply");
    }
    @Accessor("FUNCTIONAL_BLOCKS")
    static ResourceKey<CreativeModeTab> functionalBlocks() {
        throw new UnsupportedOperationException("Mixin failed to apply");
    }
    @Accessor("REDSTONE_BLOCKS")
    static ResourceKey<CreativeModeTab> redstoneBlocks() {
        throw new UnsupportedOperationException("Mixin failed to apply");
    }
    @Accessor("HOTBAR")
    static ResourceKey<CreativeModeTab> hotbar() {
        throw new UnsupportedOperationException("Mixin failed to apply");
    }
    @Accessor("SEARCH")
    static ResourceKey<CreativeModeTab> search() {
        throw new UnsupportedOperationException("Mixin failed to apply");
    }
    @Accessor("TOOLS_AND_UTILITIES")
    static ResourceKey<CreativeModeTab> toolsAndUtilities() {
        throw new UnsupportedOperationException("Mixin failed to apply");
    }
    @Accessor("COMBAT")
    static ResourceKey<CreativeModeTab> combat() {
        throw new UnsupportedOperationException("Mixin failed to apply");
    }
    @Accessor("FOOD_AND_DRINKS")
    static ResourceKey<CreativeModeTab> foodAndDrinks() {
        throw new UnsupportedOperationException("Mixin failed to apply");
    }
    @Accessor("INGREDIENTS")
    static ResourceKey<CreativeModeTab> ingredients() {
        throw new UnsupportedOperationException("Mixin failed to apply");
    }
    @Accessor("SPAWN_EGGS")
    static ResourceKey<CreativeModeTab> spawnEggs() {
        throw new UnsupportedOperationException("Mixin failed to apply");
    }
    @Accessor("OP_BLOCKS")
    static ResourceKey<CreativeModeTab> opBlocks() {
        throw new UnsupportedOperationException("Mixin failed to apply");
    }
    @Accessor("INVENTORY")
    static ResourceKey<CreativeModeTab> inventory() {
        throw new UnsupportedOperationException("Mixin failed to apply");
    }

}
