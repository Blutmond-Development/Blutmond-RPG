package de.blutmondgilde.blutmondrpg.network;

import de.blutmondgilde.blutmondrpg.api.capabilities.ISyncableEntityCapability;
import de.blutmondgilde.blutmondrpg.util.CapabilityHelper;
import lombok.AllArgsConstructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;

import java.util.function.Supplier;

@AllArgsConstructor
public class SyncEntityCapabilityPacket {
    private final int entityId;
    private final CompoundNBT nbt;
    private final String capName;

    public static void encode(SyncEntityCapabilityPacket msg, PacketBuffer buffer) {
        buffer.writeInt(msg.entityId);
        buffer.writeNbt(msg.nbt);
        buffer.writeUtf(msg.capName);
    }

    public static SyncEntityCapabilityPacket decode(PacketBuffer buffer) {
        return new SyncEntityCapabilityPacket(buffer.readInt(), buffer.readNbt(), buffer.readUtf());
    }

    public static class Handler {
        public static void handle(final SyncEntityCapabilityPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ClientWorld world = Minecraft.getInstance().level;
                if (world == null) return;
                Entity entity = world.getEntity(msg.entityId);
                if (!(entity instanceof LivingEntity)) return;
                Capability<? extends ISyncableEntityCapability> capType = CapabilityHelper.getCapabilityFromName(msg.capName);
                if (capType == null) return;
                entity.getCapability(capType).ifPresent(cap -> cap.deserializeNBT(msg.nbt));
                LogManager.getLogger().debug("Synced local Capability.");
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
