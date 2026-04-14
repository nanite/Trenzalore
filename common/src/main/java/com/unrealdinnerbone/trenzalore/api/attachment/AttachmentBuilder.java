package com.unrealdinnerbone.trenzalore.api.attachment;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

import java.util.function.Supplier;

public class AttachmentBuilder<T> {

    private boolean copyOnDeath;
    private MapCodec<T> codec;
    private StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec;
    private Supplier<T> defaultValue;

    public static <T> AttachmentBuilder<T> create() {
        return new AttachmentBuilder<>();
    }

    public AttachmentBuilder<T> copyOnDeath(boolean copyOnDeath) {
        this.copyOnDeath = copyOnDeath;
        return this;
    }

    public AttachmentBuilder<T> codec(MapCodec<T> codec) {
        this.codec = codec;
        return this;
    }

    public AttachmentBuilder<T> streamCodec(StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
        this.streamCodec = streamCodec;
        return this;
    }

    public AttachmentBuilder<T> defaultValue(Supplier<T> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public boolean copyOnDeath() {
        return copyOnDeath;
    }

    public MapCodec<T> codec() {
        return codec;
    }

    public StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
        return streamCodec;
    }

    public Supplier<T> defaultValue() {
        return defaultValue;
    }
}
