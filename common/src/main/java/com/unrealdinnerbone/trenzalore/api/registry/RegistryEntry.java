package com.unrealdinnerbone.trenzalore.api.registry;

import net.minecraft.core.Holder;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

public class RegistryEntry<T> implements Supplier<T> {

    private final String name;
    private final Supplier<T> entry;
    private Holder<T> holder;

    public RegistryEntry(String name, Supplier<T> entry) {
        this.name = name;
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

    public String name() {
        return name;
    }

    public Supplier<T> entry() {
        return entry;
    }
}
