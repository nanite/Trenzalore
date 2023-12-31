package com.unrealdinnerbone.trenzalore;

import com.unrealdinnerbone.trenzalore.api.platform.services.IRegistry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

import java.util.List;

public class TestItems implements IRegistry {

    private static final RegistryObjects<Item> ITEMS = RegistryObjects.of(Registries.ITEM);

    public static final RegistryEntry<Item> TEST_ITEM = ITEMS.register("test_item", () -> new Item(new Item.Properties()));
    @Override
    public List<RegistryObjects<?>> getRegistryObjects() {
        return List.of(ITEMS);
    }

    @Override
    public String getModID() {
        return Trenzalore.MOD_ID;
    }
}
