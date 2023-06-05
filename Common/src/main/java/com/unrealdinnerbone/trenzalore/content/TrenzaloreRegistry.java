package com.unrealdinnerbone.trenzalore.content;

import com.unrealdinnerbone.trenzalore.Trenzalore;
import com.unrealdinnerbone.trenzalore.api.platform.services.IRegistry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryFactory;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class TrenzaloreRegistry implements IRegistry {

    public static final RegistryObjects<Block> BLOCKS = RegistryFactory.create(Trenzalore.MOD_ID, Registries.BLOCK);

    public static final RegistryObjects<Item> ITEMS = RegistryFactory.create(Trenzalore.MOD_ID, Registries.ITEM);

    public static final RegistryEntry<Block> TEST_BLOCK =  BLOCKS.register("haha", () -> new Block(Block.Properties.copy(net.minecraft.world.level.block.Blocks.STONE)));

    public static final RegistryEntry<Item> TEST_ITEM = ITEMS.register("haha", () -> new BlockItem(TEST_BLOCK.get(), new Item.Properties()));

    @Override
    public List<RegistryObjects<?>> getRegistryObjects() {
        return List.of(BLOCKS, ITEMS);
    }

    @Override
    public String getModID() {
        return Trenzalore.MOD_ID;
    }
}
