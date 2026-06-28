package com.unrealdinnerbone.trenzalore.platform;

import com.mojang.logging.LogUtils;
import com.unrealdinnerbone.trenzalore.api.attachment.AttachmentBuilder;
import com.unrealdinnerbone.trenzalore.api.attachment.AttachmentReference;
import com.unrealdinnerbone.trenzalore.api.platform.services.IPlatformHelper;
import com.unrealdinnerbone.trenzalore.api.registry.AbstractRegistryObjects;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FabricPlatformHelper implements IPlatformHelper {

    private static final Logger LOGGER = LogUtils.getLogger();

    private static final Map<ResourceKey<CreativeModeTab>, Event<CreativeModeTabEvents.ModifyOutput>> creativeTabEvents = new HashMap<>();

    @Override
    public String getPlatform() {
        return "Fabric";
    }

    @Override
    public Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    @ApiStatus.Internal
    public void addItemToCreativeTab(ResourceKey<CreativeModeTab> tabResourceKey, List<Supplier<? extends Item>> items) {
        if(!creativeTabEvents.containsKey(tabResourceKey)) {
            creativeTabEvents.put(tabResourceKey, CreativeModeTabEvents.modifyOutputEvent(tabResourceKey));
        }
        creativeTabEvents.get(tabResourceKey).register(entries -> items.forEach(itemSupplier -> entries.accept(itemSupplier.get())));
    }

    @Override
    public <T> void registryRegistryObjects(String modId, AbstractRegistryObjects<T> registryObjects) {
        ResourceKey<Registry<T>> registryKey = registryObjects.registryKey();
        BuiltInRegistries.REGISTRY.get(registryKey.identifier()).ifPresentOrElse(registry -> {
            Registry<T> theRegistry = (Registry<T>) registry.value();
            for (RegistryEntry<T> object : registryObjects.objects()) {
                Registry.register(theRegistry, object.key(), object.getEntry().get());
            }
        }, () -> LOGGER.error("Failed to find registry: {}", registryKey.identifier()));
    }


    @Override
    public <T> AttachmentReference<T> registerAttachment(Identifier identifier, Consumer<AttachmentBuilder<T>> consumerr) {
        AttachmentBuilder<T> builder = new AttachmentBuilder<>();
        consumerr.accept(builder);
        AttachmentType<T> attachmentType = AttachmentRegistry.create(identifier, b -> {
            if (builder.copyOnDeath()) {
                b.copyOnDeath();
            }
            if (builder.streamCodec() != null) {
                b.syncWith(builder.streamCodec(), AttachmentSyncPredicate.all());
            }
            if (builder.defaultValue() != null) {
                b.initializer(builder.defaultValue());
            }
            if (builder.codec() != null) {
                b.persistent(builder.codec().codec());
            }
        });
        return new FabricAttachmentReference<>(attachmentType);
    }
}
