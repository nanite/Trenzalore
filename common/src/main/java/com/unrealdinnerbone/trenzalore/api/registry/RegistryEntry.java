package com.unrealdinnerbone.trenzalore.api.registry;

import net.minecraft.core.Holder;
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
        if (holder == null) {
            throw new IllegalStateException("Can't get holder before it is set");
        }
        return holder;
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "21.0.0")
    @Override
    public T get() {
        return getHolder().value();
    }

    public ResourceLocation getKey() {
        return key;
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "21.0.0")
    public Supplier<T> entry() {
        return entry;
    }

    public Supplier<T> creator() {
        return entry;
    }
}
