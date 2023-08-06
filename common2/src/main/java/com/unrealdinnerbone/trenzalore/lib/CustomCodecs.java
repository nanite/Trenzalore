package com.unrealdinnerbone.trenzalore.lib;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.PrimitiveCodec;
import net.minecraft.world.item.crafting.Ingredient;

public class CustomCodecs {

    public static final Codec<Ingredient> INGREDIENT = new PrimitiveCodec<>() {

        @Override
        public <T> DataResult<Ingredient> read(DynamicOps<T> ops, T input) {
            try {
                JsonElement jsonElement = ops.convertTo(JsonOps.INSTANCE, input);
                return DataResult.success(Ingredient.fromJson(jsonElement));
            } catch(JsonParseException e) {
                return DataResult.error(e::getMessage);
            }
        }

        @Override
        public <T> T write(DynamicOps<T> ops, Ingredient value) {
            return JsonOps.INSTANCE.convertTo(ops, value.toJson());
        }
    };
}
