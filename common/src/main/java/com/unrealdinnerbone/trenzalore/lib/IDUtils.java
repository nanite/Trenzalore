package com.unrealdinnerbone.trenzalore.lib;

import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

public class IDUtils {

    public static Identifier id(String modId, String path) {
        return Identifier.fromNamespaceAndPath(modId, path);
    }

    @Nullable
    public static Identifier idFull(String namespaceAndPath) {
        return Identifier.tryParse(namespaceAndPath);
    }
}
