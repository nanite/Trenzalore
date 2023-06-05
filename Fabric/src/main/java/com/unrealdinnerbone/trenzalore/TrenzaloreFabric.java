package com.unrealdinnerbone.trenzalore;

import com.unrealdinnerbone.trenzalore.events.LootEvents;
import net.fabricmc.api.ModInitializer;

public class TrenzaloreFabric implements ModInitializer {


    @Override
    public void onInitialize() {
        Trenzalore.init();
        LootEvents.init();
    }
}
