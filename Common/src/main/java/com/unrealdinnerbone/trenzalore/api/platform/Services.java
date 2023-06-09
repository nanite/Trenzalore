package com.unrealdinnerbone.trenzalore.api.platform;

import com.unrealdinnerbone.trenzalore.api.platform.services.IInternalUtils;
import com.unrealdinnerbone.trenzalore.api.platform.services.IPlatformHelper;
import com.unrealdinnerbone.trenzalore.api.platform.services.IPlatformUtils;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.ServiceLoader;

public class Services {
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    private static final DontBreakMyAPI DONT_BREAK_MY_API = new DontBreakMyAPI();

    @ApiStatus.Internal
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "3.0.0")

    public static final IInternalUtils INTERNAL = DONT_BREAK_MY_API;


    @ApiStatus.Internal
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "3.0.0")
    public static final IPlatformUtils UTILS = DONT_BREAK_MY_API;


    public static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
    }


    @ApiStatus.Internal
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "3.0.0")
    private static final class DontBreakMyAPI implements IInternalUtils, IPlatformUtils {

        @Override
        public <T> void registryRegistryObjects(String modId, RegistryObjects<T> registryObjects) {
            PLATFORM.registryRegistryObjects(modId, registryObjects);
        }

        @Override
        public @Nullable <T extends Entity> Entity teleport(T entity, ServerLevel level, PortalInfo portalInfo) {
            return PLATFORM.teleport(entity, level, portalInfo);
        }
    }
}