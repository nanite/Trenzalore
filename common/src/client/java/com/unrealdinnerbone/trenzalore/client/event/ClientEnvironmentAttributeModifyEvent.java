package com.unrealdinnerbone.trenzalore.client.event;

import net.minecraft.world.attribute.EnvironmentAttributeSystem;

public interface ClientEnvironmentAttributeModifyEvent {

    EnvironmentAttributeSystem.Builder addModifiers(EnvironmentAttributeSystem.Builder builder);
}
