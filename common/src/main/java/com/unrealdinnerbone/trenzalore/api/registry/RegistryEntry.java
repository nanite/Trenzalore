package com.unrealdinnerbone.trenzalore.api.registry;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

public class RegistryEntry<T> implements Supplier<T> {

    private final ResourceLocation key;
    private final Supplier<T> entry;
    private Holder<T> holder;

    public RegistryEntry(ResourceLocation key, Supplier<T> entry) {
        this.key = key;
        this.entry = entry;
    }

    @ApiStatus.Internal
    public <B> void setHolder(Holder<B> register) {
        if(holder != null) {
            throw new RuntimeException("Holder already set");
        }
        this.holder = (Holder<T>) register;
    }

    public Holder<T> getHolder() {
        return holder;
    }

    @Override
    public T get() {
        return entry.get();
    }

    public ResourceLocation getKey() {
        return key;
    }

    public Supplier<T> entry() {
        return entry;
    }
}
