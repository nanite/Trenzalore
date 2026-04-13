package com.unrealdinnerbone.trenzalore.client;

import com.unrealdinnerbone.trenzalore.api.platform.Services;
import com.unrealdinnerbone.trenzalore.client.event.ClientEnvironmentAttributeModifyEvent;
import net.minecraft.world.attribute.EnvironmentAttributeSystem;

public class ClientEvents {

    private static final Iterable<ClientEnvironmentAttributeModifyEvent> ENVIRONMENT_ATTRIBUTE_MODIFY_EVENTS = Services.loadAll(ClientEnvironmentAttributeModifyEvent.class);

    public static EnvironmentAttributeSystem.Builder postEnvironmentAttributeModifyEvent(EnvironmentAttributeSystem.Builder builder) {
        for (ClientEnvironmentAttributeModifyEvent event : ENVIRONMENT_ATTRIBUTE_MODIFY_EVENTS) {
            builder = event.addModifiers(builder);
        }
        return builder;
    }
}
