package com.unrealdinnerbone.trenzalore;

import com.unrealdinnerbone.trenzalore.platform.ForgePlatformHelper;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Trenzalore.MOD_ID)
public class TrenzaloreForge {
    
    public TrenzaloreForge() {
        Trenzalore.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(TrenzaloreForge::onCreativeTab);
    }

    public static void onCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if(ForgePlatformHelper.getCreativeTabMap().containsKey(event.getTabKey())) {
            ForgePlatformHelper.getCreativeTabMap().get(event.getTabKey()).forEach(event::accept);
        }
    }

}