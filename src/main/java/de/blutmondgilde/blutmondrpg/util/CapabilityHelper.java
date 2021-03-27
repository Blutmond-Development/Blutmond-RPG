package de.blutmondgilde.blutmondrpg.util;

import de.blutmondgilde.blutmondrpg.api.capabilities.IRaceProvider;
import de.blutmondgilde.blutmondrpg.api.capabilities.ISyncableEntityCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class CapabilityHelper {
    @CapabilityInject(IRaceProvider.class)
    public static Capability<IRaceProvider> RACE_CAPABILITY = null;

    public static Capability<? extends ISyncableEntityCapability> getCapabilityFromName(String name) {
        if (name.equals(RACE_CAPABILITY.getName())) return RACE_CAPABILITY;
        return null;
    }
}
