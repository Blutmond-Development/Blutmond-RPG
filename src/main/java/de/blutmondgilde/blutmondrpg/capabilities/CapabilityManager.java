package de.blutmondgilde.blutmondrpg.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Mod;
import java.util.concurrent.Callable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityManager {
    /** Registers all {@link Capability} classes to the forge capability system */
    public static void register() {

    }

    /**
     * Registers a single {@link Capability} class to the forge capability system
     * @param type    Interface of the {@link Capability}
     * @param storage Class implementing {@link Capability.IStorage}
     * @param factory Class implementing the Interface of param type
     */
    private static <T> void register(Class<T> type, Callable<Capability.IStorage<T>> storage, Callable<? extends T> factory) {
        try {
            net.minecraftforge.common.capabilities.CapabilityManager.INSTANCE.register(type, storage.call(), factory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
