package com.unrealdinnerbone.trenzalore;

import com.unrealdinnerbone.trenzalore.platform.NeoPlatformHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@Mod(Trenzalore.MOD_ID)
public class TrenzaloreNeo {
    
    public TrenzaloreNeo(IEventBus modEventBus) {
        Trenzalore.init();
        modEventBus.addListener(TrenzaloreNeo::onCreativeTab);
    }

    public static void onCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if(NeoPlatformHelper.getCreativeTabMap().containsKey(event.getTabKey())) {
            NeoPlatformHelper.getCreativeTabMap().get(event.getTabKey()).forEach(supplier -> event.accept(supplier.get()));
        }
    }

}