package de.blutmondgilde.blutmondrpg;

import de.blutmondgilde.blutmondrpg.capabilities.CapabilityManager;
import de.blutmondgilde.blutmondrpg.capabilities.characterclass.CharacterClassCapability;
import de.blutmondgilde.blutmondrpg.characterclass.CharacterClasses;
import de.blutmondgilde.blutmondrpg.network.BlutmondNetwork;
import de.blutmondgilde.blutmondrpg.util.Constants;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class BlutmondRPG {
    public BlutmondRPG() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus(); // Gets BlutmondRPG Event Bus
        modBus.addListener(this::setup); // Register Client & Server Event Handler
        modBus.addListener(this::clientSetup); // Register Client only Event Handler
        CharacterClasses.register(modBus); //Registers Default Character Classes

        final IEventBus forgeBus = MinecraftForge.EVENT_BUS; // Gets Minecraft Forge Event Bus
        forgeBus.addGenericListener(Entity.class, CharacterClassCapability::attachCapability); // Register Capability CharacterClass


    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(BlutmondNetwork::register); //Register Network Packets
        event.enqueueWork(CapabilityManager::register); //Register Capabilities
    }

    public void clientSetup(final FMLClientSetupEvent event) {
    }
}
