package com.unrealdinnerbone.trenzalore.api.registry;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class RegistryEntry<T> implements Supplier<T> {

    private final String name;
    private final Supplier<T> entry;
    private Holder<T> holder;

    public RegistryEntry(String name, Supplier<T> entry) {
        this.name = name;
        this.entry = entry;
    }

    public <B> void setHolder(Holder<B> register) {
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
