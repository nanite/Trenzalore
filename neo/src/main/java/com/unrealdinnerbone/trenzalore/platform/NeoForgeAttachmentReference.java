package com.unrealdinnerbone.trenzalore.platform;

import com.unrealdinnerbone.trenzalore.api.attachment.AttachmentReference;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;

public record NeoForgeAttachmentReference<T>(DeferredHolder<AttachmentType<?>, AttachmentType<T>> holder) implements AttachmentReference<T> {

    @Override
    public T level(Level level) {
        return level.getData(holder);
    }

    @Override
    public void level(Level level, T attachment) {
        level.setData(holder, attachment);
    }

    @Override
    public T chunk(ChunkAccess chunk) {
        return chunk.getData(holder);
    }

    @Override
    public void chunk(ChunkAccess chunk, T attachment) {
        chunk.setData(holder, attachment);
        chunk.markUnsaved();
    }

    @Override
    public T entity(Entity entity) {
        return entity.getData(holder);
    }

    @Override
    public void entity(Entity entity, T attachment) {
        entity.setData(holder, attachment);
    }
}
