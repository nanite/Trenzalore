package com.unrealdinnerbone.trenzalore;

import com.unrealdinnerbone.trenzalore.data.LootModifierGenerator;
import com.unrealdinnerbone.trenzalore.platform.ForgePlatformHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Trenzalore.MOD_ID)
public class TrenzaloreForge {
    
    public TrenzaloreForge() {
        Trenzalore.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(TrenzaloreForge::onData);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(TrenzaloreForge::onCreativeTab);
    }

    public static void onData(GatherDataEvent event) {
        event.getGenerator().addProvider(true, new LootModifierGenerator(event.getGenerator().getPackOutput()));
    }

    public static void onCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if(ForgePlatformHelper.getCreativeTabMap().containsKey(event.getTabKey())) {
            ForgePlatformHelper.getCreativeTabMap().get(event.getTabKey()).forEach(event::accept);
        }
    }

}