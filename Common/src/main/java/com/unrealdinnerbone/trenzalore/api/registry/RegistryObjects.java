package com.unrealdinnerbone.trenzalore.api.registry;

import com.google.common.base.Suppliers;
import com.unrealdinnerbone.trenzalore.content.mixin.CreativeTabAccessor;
import joptsimple.HelpFormatter;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.commands.HelpCommand;
import net.minecraft.world.item.CreativeModeTabs;

import java.util.List;
import java.util.function.Supplier;

public record RegistryObjects<T>(ResourceKey<Registry<T>> registryKey, List<RegistryEntry<? extends T>> objects) {

    public <A extends T> RegistryEntry<A> register(String name, Supplier<A> object) {
        RegistryEntry<A> entry = new RegistryEntry<>(name, Suppliers.memoize(object::get));
        objects.add(entry);
        return entry;
    }

}
