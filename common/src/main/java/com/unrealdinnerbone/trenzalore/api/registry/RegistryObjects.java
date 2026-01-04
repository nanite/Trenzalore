package com.unrealdinnerbone.trenzalore.api.registry;

import com.google.common.base.Suppliers;
import com.unrealdinnerbone.trenzalore.lib.IDUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import java.util.function.Function;
import java.util.function.Supplier;

public class RegistryObjects<T> extends AbstractRegistryObjects<T> {

    public RegistryObjects(String modID, ResourceKey<Registry<T>> registryKey) {
        super(modID, registryKey);
    }

    public static <T> RegistryObjects<T> of(String modID, ResourceKey<Registry<T>> registryKey) {
        return new RegistryObjects<>(modID, registryKey);
    }

    public <A extends T> RegistryEntry<T, A> register(String name, Supplier<A> object) {
        Identifier rl = IDUtils.id(modID, name);
        RegistryEntry<T, A> entry = new RegistryEntry<>(rl, Suppliers.memoize(object::get));
        objects.add(entry);
        return entry;
    }

    public <A extends T> RegistryEntry<T, A> registerWithId(String name, Function<ResourceKey<T>, A> object) {
        Identifier rl = IDUtils.id(modID, name);
        ResourceKey<T> key = ResourceKey.create(registryKey, rl);
        Supplier<A> memoize = Suppliers.memoize(() -> object.apply(key));
        RegistryEntry<T, A> entry = new RegistryEntry<>(rl, memoize);
        objects.add(entry);
        return entry;
    }
}
