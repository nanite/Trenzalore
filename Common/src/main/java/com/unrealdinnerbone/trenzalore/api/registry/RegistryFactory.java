package com.unrealdinnerbone.trenzalore.api.registry;

import com.unrealdinnerbone.trenzalore.api.platform.Services;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class RegistryFactory {

    public static <T> RegistryObjects<T> create(ResourceKey<Registry<T>> registry) {
        return new RegistryObjects<>(registry, new ArrayList<>());
    }

    public static <T extends CriterionTrigger<?>> T registerCriterion(T criterion) {
        return Services.PLATFORM.registerCriterion(criterion);
    }

    public static void registerCreativeTabItems(ResourceKey<CreativeModeTab> tabResourceKey, List<Supplier<? extends Item>> item) {
        Services.PLATFORM.addItemToCreativeTab(tabResourceKey, item);
    }
}
