package com.unrealdinnerbone.trenzalore.lib;

import net.minecraft.resources.ResourceLocation;

public class RLUtils
{
    public static ResourceLocation rl(String modId, String path) {
        return ResourceLocation.fromNamespaceAndPath(modId, path);
    }

    public static ResourceLocation rlFull(String namespaceAndPath) {
        return ResourceLocation.tryParse(namespaceAndPath);
    }
}
