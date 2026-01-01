package com.unrealdinnerbone.trenzalore.api.registry;

import com.google.common.base.Suppliers;
import com.unrealdinnerbone.trenzalore.lib.IDUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class RegistryObjects<T> {

    protected final String modID;
    protected final ResourceKey<Registry<T>> registryKey;
    protected final List<RegistryEntry<? extends T>> objects;

    public RegistryObjects(String modID, ResourceKey<Registry<T>> registryKey) {
        this.modID = modID;
        this.registryKey = registryKey;
        this.objects = new ArrayList<>();
    }

    public static <T> RegistryObjects<T> of(String modID, ResourceKey<Registry<T>> registryKey) {
        return new RegistryObjects<>(modID, registryKey);
    }

    public <A extends T> RegistryEntry<A> register(String name, Supplier<A> object) {
        Identifier rl = IDUtils.id(modID, name);
        RegistryEntry<A> entry = new RegistryEntry<>(rl, Suppliers.memoize(object::get));
        objects.add(entry);
        return entry;
    }

    public <A extends T> RegistryEntry<A> registerWithId(String name, Function<ResourceKey<T>, A> object) {
        Identifier rl = IDUtils.id(modID, name);
        ResourceKey<T> key = ResourceKey.create(registryKey, rl);
        Supplier<A> memoize = Suppliers.memoize(() -> object.apply(key));
        RegistryEntry<A> entry = new RegistryEntry<>(rl, memoize);
        objects.add(entry);
        return entry;
    }

    public ResourceKey<Registry<T>> registryKey() {
        return registryKey;
    }

    public List<RegistryEntry<? extends T>> objects() {
        return objects;
    }

}
