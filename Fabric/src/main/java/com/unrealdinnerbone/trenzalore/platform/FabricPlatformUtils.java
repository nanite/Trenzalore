package com.unrealdinnerbone.trenzalore.platform;

import com.unrealdinnerbone.trenzalore.api.platform.services.IPlatformUtils;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;
import org.jetbrains.annotations.Nullable;

public class FabricPlatformUtils implements IPlatformUtils {
    @Override
    public @Nullable <T extends Entity> Entity teleport(T entity, ServerLevel level, PortalInfo portalInfo) {
        return FabricDimensions.teleport(entity, level, portalInfo);
    }
}
