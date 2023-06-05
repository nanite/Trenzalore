package com.unrealdinnerbone.trenzalore.content.mixin;

import com.mojang.logging.LogUtils;
import com.unrealdinnerbone.trenzalore.content.TrenzaloreConfig;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class TrenzaloreMixinConfig implements IMixinConfigPlugin {

    private static final Logger LOGGER = LogUtils.getLogger();
    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        TrenzaloreConfig config = TrenzaloreConfig.CONFIG.get();
        if(mixinClassName.equals("com.unrealdinnerbone.trenzalore.content.mixin.BoatMixin") && config.boatFix()) {
            LOGGER.debug("Applying mixin {} to {} because it is enabled in the config", mixinClassName, targetClassName);
            return true;
        }
        if(mixinClassName.equals("com.unrealdinnerbone.trenzalore.content.mixin.PiglinBruteAIMixin") && config.fullBruteGoldArmor()) {
            LOGGER.debug("Applying mixin {} to {} because it is enabled in the config", mixinClassName, targetClassName);
            return true;
        }
        LOGGER.debug("Not applying mixin {} to {} because it is disabled in the config", mixinClassName, targetClassName);
        return false;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void postApply(String targetClassName, org.objectweb.asm.tree.ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void preApply(String targetClassName, org.objectweb.asm.tree.ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
