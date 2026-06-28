package com.unrealdinnerbone.trenzalore.api.registry;

import com.google.common.base.Suppliers;
import com.unrealdinnerbone.trenzalore.lib.IDUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ItemRegistryObjects extends AbstractRegistryObjects<Item> {

    public ItemRegistryObjects(String modID) {
        super(modID, Registries.ITEM);
    }

    public <A extends Item> RegistryEntry.ItemEntry<A> register(String name, Function<Item.Properties, A> object, UnaryOperator<Item.Properties> itemProperties) {
        Identifier rl = IDUtils.id(modID, name);
        ResourceKey<Item> key = ResourceKey.create(registryKey, rl);
        Supplier<A> memoize = Suppliers.memoize(() -> object.apply(itemProperties.apply(new Item.Properties().setId(key))));
        RegistryEntry.ItemEntry<A> entry = new RegistryEntry.ItemEntry<>(key, memoize);
        objects.add(entry);
        return entry;
    }

    public RegistryEntry.ItemEntry<BlockItem> registerBlockItem(String name, Supplier<Block> block, UnaryOperator<Item.Properties> itemProperties) {
        return register(name, (properties) -> new BlockItem(block.get(), properties), itemProperties);
    }

    public RegistryEntry.ItemEntry<BlockItem> registerBlockItem(String name, RegistryEntry<Block> block, UnaryOperator<Item.Properties> itemProperties) {
        return register(name, (properties) -> new BlockItem(block.getEntry().get(), properties), itemProperties);
    }


}
