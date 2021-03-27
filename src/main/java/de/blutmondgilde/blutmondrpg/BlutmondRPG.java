package de.blutmondgilde.blutmondrpg;

import de.blutmondgilde.blutmondrpg.commands.BlutmondRPGCommand;
import de.blutmondgilde.blutmondrpg.handler.CapabilityHandler;
import de.blutmondgilde.blutmondrpg.handler.CommandHandler;
import de.blutmondgilde.blutmondrpg.network.BlutmondNetwork;
import de.blutmondgilde.blutmondrpg.registries.RaceRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("blutmondrpg")
public class BlutmondRPG {
    public static final String MODID = "blutmondrpg";

    public BlutmondRPG() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        //Register Registries
        RaceRegistry.init(modBus);
        //Setup Mod
        modBus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent e) {
        //Init Capabilities
        CapabilityHandler.init();
        //Register Network
        BlutmondNetwork.init();
        //Register Commands
        CommandHandler.registerCommand(new BlutmondRPGCommand());
        MinecraftForge.EVENT_BUS.addListener(CommandHandler::init);
    }
}
