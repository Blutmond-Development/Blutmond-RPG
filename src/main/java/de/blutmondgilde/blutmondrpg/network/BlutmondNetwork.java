package de.blutmondgilde.blutmondrpg.network;

import de.blutmondgilde.blutmondrpg.BlutmondRPG;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class BlutmondNetwork {
    private static final String PROTOCOL_VERSION = "1";
    private static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(BlutmondRPG.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void init() {
        int id = 0;
        INSTANCE.registerMessage(id++, SyncEntityCapabilityPacket.class, SyncEntityCapabilityPacket::encode, SyncEntityCapabilityPacket::decode, SyncEntityCapabilityPacket.Handler::handle);
    }

    public static void send(PacketDistributor.PacketTarget target, SyncEntityCapabilityPacket message) {
        INSTANCE.send(target, message);
    }
}
