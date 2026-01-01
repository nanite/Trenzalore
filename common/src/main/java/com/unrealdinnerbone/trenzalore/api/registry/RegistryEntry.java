package com.unrealdinnerbone.trenzalore.api.registry;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class RegistryEntry<T> implements Supplier<T> {

    private final Identifier key;
    private final Supplier<T> entry;

    @Nullable
    private Holder<T> holder;

    public RegistryEntry(Identifier key, Supplier<T> entry) {
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
            throw new IllegalStateException("Holder for " + key + " has not been set yet!");
        }
        return holder;
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "21.0.0")
    @Override
    public T get() {
        return getHolder().value();
    }

    public Identifier getKey() {
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
