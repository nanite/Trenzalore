package com.unrealdinnerbone.trenzalore.api.attachment;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;

public interface AttachmentReference<T> {

    T level(Level level);

    void level(Level level, T attachment);

    T chunk(ChunkAccess chunk);

    void chunk(ChunkAccess chunk, T attachment);

    T entity(Entity entity);

    void entity(Entity entity, T attachment);
}
