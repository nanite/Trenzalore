package com.unrealdinnerbone.trenzalore.api.platform.services;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@ApiStatus.ScheduledForRemoval(inVersion = "3.0.0")
@Deprecated(forRemoval = true)
public interface IPlatformUtils {
    @Nullable <T extends Entity> Entity teleport(T entity, ServerLevel level, PortalInfo portalInfo);
}
