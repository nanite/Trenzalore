package com.unrealdinnerbone.trenzalore;

import com.unrealdinnerbone.trenzalore.platform.NeoPlatformHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(Trenzalore.MOD_ID)
public class TrenzaloreForge {
    
    public TrenzaloreForge(IEventBus modEventBus) {
        Trenzalore.init();
        modEventBus.addListener(TrenzaloreForge::onCreativeTab);
    }

    public static void onCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if(NeoPlatformHelper.getCreativeTabMap().containsKey(event.getTabKey())) {
            NeoPlatformHelper.getCreativeTabMap().get(event.getTabKey()).forEach(supplier -> event.accept(supplier.get()));
        }
    }

}