package com.unrealdinnerbone.trenzalore.api.registry;

import java.util.function.Supplier;

public record RegistryEntry<T>(String name, Supplier<T> entry) implements Supplier<T> {
    @Override
    public T get() {
        return entry.get();
    }
}
