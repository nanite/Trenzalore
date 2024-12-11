package com.unrealdinnerbone.trenzalore.api.registry;

import com.unrealdinnerbone.trenzalore.api.platform.Services;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Regeneration {

    public static <T> RegistryObjects<T> create(String modID, ResourceKey<Registry<T>> registry) {
        return RegistryObjects.of(modID, registry);
    }

    public static ItemRegistryObjects createItemRegistry(String modID) {
        return new ItemRegistryObjects(modID);
    }

    public static void addItemToCreateTab(ResourceKey<CreativeModeTab> tabResourceKey, Supplier<? extends Item> item) {
        Services.PLATFORM.addItemToCreativeTab(tabResourceKey, List.of(item));
    }

    public static void addItemsToCreateTab(ResourceKey<CreativeModeTab> tabResourceKey, List<Supplier<? extends Item>> item) {
        Services.PLATFORM.addItemToCreativeTab(tabResourceKey, item);
    }

    @Deprecated(forRemoval = true)
    @ApiStatus.ScheduledForRemoval(inVersion = "22.0.0")
    public static <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BlockEntityType.BlockEntitySupplier<T> creator, Block... validBlocks) {
        return new BlockEntityType<>(creator, Set.of(validBlocks));
    }

    public static <T extends BlockEntity> BlockEntityType<T> createBEType(BESuppler<T> creator, Block... validBlocks) {
        return new BlockEntityType<>(creator, Set.of(validBlocks));
    }

    public static <T extends AbstractContainerMenu> MenuType<T> createMenuType(BiFunction<Integer, Inventory, T> supplier) {
        return new MenuType<>(supplier::apply, FeatureFlagSet.of());
    }


    public interface BESuppler<T extends BlockEntity> extends BlockEntityType.BlockEntitySupplier<T> {

        @Override
        @NotNull
        T create(BlockPos blockPos, BlockState blockState);
    }

}
