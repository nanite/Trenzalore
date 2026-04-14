package com.unrealdinnerbone.trenzalore.platform;

import com.unrealdinnerbone.trenzalore.api.attachment.AttachmentReference;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;

public record FabricAttachmentReference<T>(AttachmentType<T> attachmentType) implements AttachmentReference<T> {

    public T level(Level level) {
       return level.getAttachedOrCreate(attachmentType);
    }

    public void level(Level level, T attachment) {
        level.setAttached(attachmentType, attachment);
    }

    public T chunk(ChunkAccess chunk) {
        return chunk.getAttachedOrCreate(attachmentType);
    }

    public void chunk(ChunkAccess chunk, T attachment) {
        chunk.setAttached(attachmentType, attachment);
    }

    public T entity(Entity entity) {
        return entity.getAttachedOrCreate(attachmentType);
    }

    public void entity(Entity entity, T attachment) {
        entity.setAttached(attachmentType, attachment);
    }

}
