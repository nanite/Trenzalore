package com.unrealdinnerbone.trenzalore.api.registry;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface ReferencedHolder<T> extends Holder<T> {
    Holder<T> getHolder();

    @Override
    default T value() {
        return getHolder().value();
    }

    @Override
    default boolean isBound() {
        return getHolder().isBound();
    }

    @Override
    default boolean is(Identifier identifier) {
        return getHolder().is(identifier);
    }

    @Override
    default boolean is(ResourceKey<T> resourceKey) {
        return getHolder().is(resourceKey);
    }

    @Override
    default boolean is(Predicate<ResourceKey<T>> predicate) {
        return getHolder().is(predicate);
    }

    @Override
    default boolean is(TagKey<T> tagKey) {
        return getHolder().is(tagKey);
    }

    @Override
    @Deprecated
    default boolean is(Holder<T> holder) {
        return getHolder().is(holder);
    }

    @Override
    default Stream<TagKey<T>> tags() {
        return getHolder().tags();
    }

    @Override
    default Either<ResourceKey<T>, T> unwrap() {
        return getHolder().unwrap();
    }

    @Override
    default Optional<ResourceKey<T>> unwrapKey() {
        return getHolder().unwrapKey();
    }

    @Override
    default Holder.Kind kind() {
        return getHolder().kind();
    }

    @Override
    default boolean canSerializeIn(HolderOwner<T> holderOwner) {
        return getHolder().canSerializeIn(holderOwner);
    }
}
