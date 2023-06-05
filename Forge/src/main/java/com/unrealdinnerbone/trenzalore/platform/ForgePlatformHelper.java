package com.unrealdinnerbone.trenzalore.platform;

import com.unrealdinnerbone.trenzalore.api.platform.services.IPlatformHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatform() {
        return "Forge";
    }

    @Override
    public Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }

}