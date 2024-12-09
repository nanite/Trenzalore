package com.unrealdinnerbone.trenzalore.api.platform.services;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.function.Supplier;

public interface ICreativeTabRegister {

    @ApiStatus.Internal
    void addItemToCreativeTab(ResourceKey<CreativeModeTab> tabResourceKey, List<Supplier<? extends Item>> item);
}
