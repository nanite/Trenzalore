package com.unrealdinnerbone.trenzalore;

import com.unrealdinnerbone.trenzalore.data.LootModifierGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Trenzalore.MOD_ID)
public class TrenzaloreForge {
    
    public TrenzaloreForge() {
        Trenzalore.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(TrenzaloreForge::onData);
    }

    public static void onData(GatherDataEvent event) {
        event.getGenerator().addProvider(true, new LootModifierGenerator(event.getGenerator().getPackOutput()));
    }

}