package com.unrealdinnerbone.trenzalore.platform;

import com.unrealdinnerbone.trenzalore.api.platform.services.IPlatformHelper;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@ApiStatus.Internal
public class ForgePlatformHelper implements IPlatformHelper {

    private static final Map<ResourceKey<CreativeModeTab>, List<Supplier<? extends Item>>> CREATIVE_TAB_MAP = new HashMap<>();
    @Override
    public String getPlatform() {
        return "Forge";
    }

    @Override
    public Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public <T> void registryRegistryObjects(String modId, RegistryObjects<T> registryObjects) {
        DeferredRegister<T> deferredRegister = DeferredRegister.create(registryObjects.registryKey(), modId);
        registryObjects.objects().forEach(registryEntry -> deferredRegister.register(registryEntry.name(), registryEntry.entry()));
        deferredRegister.register(FMLJavaModLoadingContext.get().getModEventBus());
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
    public @Nullable <T extends Entity> Entity teleport(T entity, ServerLevel level, PortalInfo portalInfo) {
        return entity.changeDimension(level, new SimpleTeleporter(portalInfo));
    }
    public record SimpleTeleporter(PortalInfo portalInfo) implements ITeleporter {

        @Override
        public @Nullable PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
            return portalInfo;
        }
    }

    @ApiStatus.Internal
    public static Map<ResourceKey<CreativeModeTab>, List<Supplier<? extends Item>>> getCreativeTabMap() {
        return CREATIVE_TAB_MAP;
    }

}