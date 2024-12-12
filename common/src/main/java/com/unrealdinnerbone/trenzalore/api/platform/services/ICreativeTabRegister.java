package com.unrealdinnerbone.trenzalore.api.platform.services;

import com.unrealdinnerbone.trenzalore.lib.CreativeTabs;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.function.Supplier;

public interface ICreativeTabRegister {

    void addItemToCreativeTab(ResourceKey<CreativeModeTab> tabResourceKey, List<Supplier<? extends Item>> item);

    default void addItemToCreativeTab(ResourceKey<CreativeModeTab> tabResourceKey, Supplier<? extends Item> item) {
        addItemToCreativeTab(tabResourceKey, List.of(item));
    }

    default CreativeTabs tabs() {
        return CreativeTabs.INSTANCE;
    }
}
