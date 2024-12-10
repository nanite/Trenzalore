package com.unrealdinnerbone.trenzalore.api.registry;

import com.google.common.base.Suppliers;
import com.unrealdinnerbone.trenzalore.lib.RLUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ItemRegistryObjects extends RegistryObjects<Item> {

    public ItemRegistryObjects(String modID) {
        super(modID, Registries.ITEM);
    }

    public <A extends Item> RegistryEntry<A> register(String name, Function<Item.Properties, A> object, UnaryOperator<Item.Properties> itemProperties) {
        ResourceLocation rl = RLUtils.rl(modID, name);
        ResourceKey<Item> key = ResourceKey.create(registryKey, rl);
        Supplier<A> memoize = Suppliers.memoize(() -> object.apply(itemProperties.apply(new Item.Properties()
                .setId(key))));
        RegistryEntry<A> entry = new RegistryEntry<>(rl, memoize);
        objects.add(entry);
        return entry;
    }


}
