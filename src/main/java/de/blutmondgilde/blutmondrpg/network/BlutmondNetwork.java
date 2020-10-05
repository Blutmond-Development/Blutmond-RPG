package de.blutmondgilde.blutmondrpg.network;

import de.blutmondgilde.blutmondrpg.BlutmondRPG;
import de.blutmondgilde.blutmondrpg.util.Constants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class BlutmondNetwork {
    /** Protocol version of our Network handler (this should always be 1) */
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    /** Network Channel which is used to send packets from one Side to another */
    private static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Constants.MOD_ID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    /** Method to register the Network Packets. This method is callen in {@link BlutmondRPG#setup(FMLCommonSetupEvent)} */
    public static void register() {
        int disc = 0;

    }

    /**
     * Method to send a given Network Packet Object to an given Target.
     * @param target  {@link PacketDistributor.PacketTarget} which will receive the Packet
     * @param message Network Packet Object
     * @param <MSG>   Network Packet Class registered in {@link BlutmondNetwork#register()}
     */
    private static <MSG> void send(PacketDistributor.PacketTarget target, MSG message) {
        HANDLER.send(target, message);
    }

    /**
     * Client Side only method to send a Packet to the Server Side
     * @param message Network Packet Object
     * @param <MSG>   Network Packet Class registered in {@link BlutmondNetwork#register()}
     */
    @OnlyIn(Dist.CLIENT)
    public static <MSG> void sendToServer(MSG message) {
        HANDLER.sendToServer(message);
    }

    /**
     * Server Side method to send a Packet to a given Player
     * @param message Network Packet Object
     * @param player  {@link PlayerEntity} which will receive the Packet
     * @param <MSG>   Network Packet Class registered in {@link BlutmondNetwork#register()}
     */
    public static <MSG> void sendToPlayer(MSG message, final PlayerEntity player) {
        send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), message);
    }
}

