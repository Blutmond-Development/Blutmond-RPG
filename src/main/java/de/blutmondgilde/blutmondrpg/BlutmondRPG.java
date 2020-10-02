package de.blutmondgilde.blutmondrpg;

import de.blutmondgilde.blutmondrpg.capabilities.CapabilityManager;
import de.blutmondgilde.blutmondrpg.network.BlutmondNetwork;
import de.blutmondgilde.blutmondrpg.util.Constants;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class BlutmondRPG {
    public BlutmondRPG() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(BlutmondNetwork::register); //Register Network Packets
        event.enqueueWork(CapabilityManager::register); //Register Capabilities
    }

    public void clientSetup(final FMLClientSetupEvent event) {
    }
}
