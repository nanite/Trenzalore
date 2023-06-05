package com.unrealdinnerbone.trenzalore.data;

import com.unrealdinnerbone.trenzalore.content.TrenzaloreTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class TagProviders
{
   public static class ItemTagProvider extends FabricTagProvider.ItemTagProvider {

       public ItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
           super(output, completableFuture);
       }

       @Override
       protected void addTags(HolderLookup.Provider arg) {
            getOrCreateTagBuilder(TrenzaloreTags.GOLD_ARMOR)
                    .add(Items.GOLDEN_HELMET)
                    .add(Items.GOLDEN_CHESTPLATE)
                    .add(Items.GOLDEN_LEGGINGS)
                    .add(Items.GOLDEN_BOOTS);
       }
   }
}
