package com.unrealdinnerbone.trenzalore.api.registry;

import com.unrealdinnerbone.trenzalore.api.platform.Services;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Regeneration {

    public static <T> RegistryObjects<T> create(ResourceKey<Registry<T>> registry) {
        return RegistryObjects.of(registry);
    }

    public static <T extends CriterionTrigger<?>> T registerCriterion(ResourceLocation name, T criterion) {
        return CriteriaTriggers.register(name.toString(), criterion);
    }
    public static void addItemToCreateTab(ResourceKey<CreativeModeTab> tabResourceKey, Supplier<? extends Item> item) {
        Services.PLATFORM.addItemToCreativeTab(tabResourceKey, List.of(item));
    }

    public static void addItemsToCreateTab(ResourceKey<CreativeModeTab> tabResourceKey, List<Supplier<? extends Item>> item) {
        Services.PLATFORM.addItemToCreativeTab(tabResourceKey, item);
    }

    public static <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BiFunction<BlockPos, BlockState, T> creator, Block... validBlocks) {
        return BlockEntityType.Builder.of(creator::apply, validBlocks).build(null);
    }

}
