package de.blutmondgilde.blutmondrpg.capabilities;

import de.blutmondgilde.blutmondrpg.capabilities.characterclass.CharacterClassCapability;
import de.blutmondgilde.blutmondrpg.capabilities.characterclass.CharacterClassCapabilityStorage;
import de.blutmondgilde.blutmondrpg.capabilities.characterclass.ICharacterClassCapability;
import net.minecraftforge.common.capabilities.Capability;
import org.apache.logging.log4j.LogManager;
import java.util.concurrent.Callable;

public class CapabilityManager {
    /** Registers all {@link Capability} classes to the forge capability system */
    public static void register() {
        register(ICharacterClassCapability.class, CharacterClassCapabilityStorage::new, CharacterClassCapability::new);
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

    /**
     * Logs Information at Debug level
     * @param log Information which should be logged
     */
    public static void logDebug(final String log) {
        LogManager.getLogger("BlutmondRPG/Capabilities").debug(log);
    }
}
