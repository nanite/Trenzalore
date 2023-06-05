package com.unrealdinnerbone.trenzalore.api.platform.services;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;
import org.jetbrains.annotations.Nullable;

public interface IPlatformUtils {
    @Nullable <T extends Entity> Entity teleport(T entity, ServerLevel level, PortalInfo portalInfo);
}
