package com.unrealdinnerbone.trenzalore.content;

import com.unrealdinnerbone.trenzalore.Trenzalore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TrenzaloreTags {

    public static TagKey<Item> GOLD_ARMOR = TagKey.create(Registries.ITEM, new ResourceLocation(Trenzalore.MOD_ID, "gold_armor"));
}
