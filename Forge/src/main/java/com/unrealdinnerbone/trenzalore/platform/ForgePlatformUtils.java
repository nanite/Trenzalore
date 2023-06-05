package com.unrealdinnerbone.trenzalore.platform;

import com.unrealdinnerbone.trenzalore.api.platform.services.IPlatformUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraftforge.common.util.ITeleporter;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class ForgePlatformUtils implements IPlatformUtils {
    @Override
    public @Nullable <T extends Entity> Entity teleport(T entity, ServerLevel level, PortalInfo portalInfo) {
        return entity.changeDimension(level, new SimpleTeleporter(portalInfo));
    }

    public record SimpleTeleporter(PortalInfo portalInfo) implements ITeleporter {

        @Override
        public @Nullable PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
            return portalInfo;
        }
    }
}
