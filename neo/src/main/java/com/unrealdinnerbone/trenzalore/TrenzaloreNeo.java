package com.unrealdinnerbone.trenzalore;

import com.mojang.logging.LogUtils;
import com.unrealdinnerbone.trenzalore.platform.NeoPlatformHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.Map;
import java.util.Optional;

@Mod(Trenzalore.MOD_ID)
public class TrenzaloreNeo {

    private static final Logger LOGGER = LogUtils.getLogger();

    public TrenzaloreNeo(IEventBus modEventBus) {
        Trenzalore.init();
        modEventBus.addListener(TrenzaloreNeo::onCreativeTab);
        for (Map.Entry<String, DeferredRegister<AttachmentType<?>>> stringDeferredRegisterEntry : NeoPlatformHelper.ATTACHMENT_TYPE_REGISTRY_OBJECTS.entrySet()) {
            String modId = stringDeferredRegisterEntry.getKey();
            DeferredRegister<AttachmentType<?>> deferredRegister = stringDeferredRegisterEntry.getValue();
            getBusFor(modId).ifPresentOrElse(deferredRegister::register, () -> LOGGER.error("Can't register to {} event bus mod not found, skipping registration of attachment types", modId));
        }
    }

    public static void onCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if(NeoPlatformHelper.getCreativeTabMap().containsKey(event.getTabKey())) {
            NeoPlatformHelper.getCreativeTabMap().get(event.getTabKey()).forEach(supplier -> event.accept(supplier.get()));
        }
    }


    public static Optional<IEventBus> getBusFor(String modId) {
        return ModList.get().getModContainerById(modId)
                .map(ModContainer::getEventBus);
    };

}