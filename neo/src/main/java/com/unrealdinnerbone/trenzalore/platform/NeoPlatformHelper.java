package com.unrealdinnerbone.trenzalore.platform;

import com.unrealdinnerbone.trenzalore.api.attachment.AttachmentBuilder;
import com.unrealdinnerbone.trenzalore.api.attachment.AttachmentReference;
import com.unrealdinnerbone.trenzalore.api.platform.services.IPlatformHelper;
import com.unrealdinnerbone.trenzalore.api.registry.AbstractRegistryObjects;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.At;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

@ApiStatus.Internal
public class NeoPlatformHelper implements IPlatformHelper {

    private static final Map<ResourceKey<CreativeModeTab>, List<Supplier<? extends Item>>> CREATIVE_TAB_MAP = new HashMap<>();
    public static final Map<String, DeferredRegister<AttachmentType<?>>> ATTACHMENT_TYPE_REGISTRY_OBJECTS = new HashMap<>();

    @Override
    public String getPlatform() {
        return "Neo";
    }

    @Override
    public Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public <T> void registryRegistryObjects(String modId, AbstractRegistryObjects<T> registryObjects) {
        DeferredRegister<T> deferredRegister = DeferredRegister.create(registryObjects.registryKey(), modId);
        registryObjects.objects().forEach(registryEntry -> registryEntry
                .setHolder(deferredRegister.register(registryEntry.getIDKey().getPath(), registryEntry::entryValue)));
        IEventBus modEventBus = ModList.get().getModContainerById(modId)
                .orElseThrow(() -> new IllegalArgumentException("Mod Not Found: " + modId))
                .getEventBus();
        if(modEventBus == null) {
            throw new IllegalArgumentException("Mod Does not have and event bus: " + modId);
        }
        deferredRegister.register(modEventBus);
    }


    @Override
    public void addItemToCreativeTab(ResourceKey<CreativeModeTab> tabResourceKey, List<Supplier<? extends Item>> item) {
        if(CREATIVE_TAB_MAP.containsKey(tabResourceKey)) {
            CREATIVE_TAB_MAP.get(tabResourceKey).addAll(item);
        }else {
            CREATIVE_TAB_MAP.put(tabResourceKey, new ArrayList<>(item));
        }
    }

    @Override
    public <T> AttachmentReference<T> registerAttachment(Identifier identifier, Consumer<AttachmentBuilder<T>> consumer) {
        AttachmentBuilder<T> builder = new AttachmentBuilder<>();
        consumer.accept(builder);
        DeferredRegister<AttachmentType<?>> registryObjects = getAttachmentTypeRegistryObjects(identifier.getNamespace());
        DeferredHolder<AttachmentType<?>, AttachmentType<T>> register = registryObjects.register(identifier.getPath(), () -> {
            AttachmentType.Builder<T> attachmentBuilder = AttachmentType
                    .builder(builder.defaultValue());
            if (builder.copyOnDeath()) {
                attachmentBuilder = attachmentBuilder.copyOnDeath();
            }
            if (builder.codec() != null) {
                attachmentBuilder = attachmentBuilder.serialize(builder.codec());
            }
            if (builder.streamCodec() != null) {
                attachmentBuilder = attachmentBuilder.sync(builder.streamCodec());
            }
            return attachmentBuilder
                    .build();
        });
        return new NeoForgeAttachmentReference<>(register);
    }

    @ApiStatus.Internal
    public static Map<ResourceKey<CreativeModeTab>, List<Supplier<? extends Item>>> getCreativeTabMap() {
        return CREATIVE_TAB_MAP;
    }


    private DeferredRegister<AttachmentType<?>> getAttachmentTypeRegistryObjects(String modId) {
        return ATTACHMENT_TYPE_REGISTRY_OBJECTS.computeIfAbsent(modId, id -> DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, id));
    }


}