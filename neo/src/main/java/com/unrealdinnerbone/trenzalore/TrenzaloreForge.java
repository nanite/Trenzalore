package com.unrealdinnerbone.trenzalore;

import com.unrealdinnerbone.trenzalore.platform.NeoPlatformHelper;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(Trenzalore.MOD_ID)
public class TrenzaloreForge {
    
    public TrenzaloreForge() {
        Trenzalore.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(TrenzaloreForge::onCreativeTab);
    }

    public static void onCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if(NeoPlatformHelper.getCreativeTabMap().containsKey(event.getTabKey())) {
            NeoPlatformHelper.getCreativeTabMap().get(event.getTabKey()).forEach(supplier -> event.accept(supplier.get()));
        }
    }

}