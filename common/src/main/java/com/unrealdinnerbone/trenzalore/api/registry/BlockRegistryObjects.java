package com.unrealdinnerbone.trenzalore.api.registry;

import com.google.common.base.Suppliers;
import com.unrealdinnerbone.trenzalore.lib.IDUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class BlockRegistryObjects extends AbstractRegistryObjects<Block> {

    public BlockRegistryObjects(String modID) {
        super(modID, Registries.BLOCK);
    }

    public <A extends Block> RegistryEntry.BlockEntry<A> register(String name, Function<Block.Properties, A> object, UnaryOperator<Block.Properties> blockProperties) {
        Identifier rl = IDUtils.id(modID, name);
        ResourceKey<Block> key = ResourceKey.create(registryKey, rl);
        Supplier<A> memoize = Suppliers.memoize(() -> object.apply(blockProperties.apply(Block.Properties.of().setId(key))));
        RegistryEntry.BlockEntry<A> entry = new RegistryEntry.BlockEntry<>(rl, memoize);
        objects.add(entry);
        return entry;
    }


}
