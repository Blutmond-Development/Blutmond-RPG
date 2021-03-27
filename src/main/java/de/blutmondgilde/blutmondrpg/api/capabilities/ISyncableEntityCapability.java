package de.blutmondgilde.blutmondrpg.api.capabilities;

import de.blutmondgilde.blutmondrpg.network.BlutmondNetwork;
import de.blutmondgilde.blutmondrpg.network.SyncEntityCapabilityPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;

public interface ISyncableEntityCapability extends INBTSerializable<CompoundNBT> {
    default void sync(@Nonnull LivingEntity entity) {
        BlutmondNetwork.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), new SyncEntityCapabilityPacket(entity.getId(), serializeNBT(), getCapabilityName()));
    }

    String getCapabilityName();
}
