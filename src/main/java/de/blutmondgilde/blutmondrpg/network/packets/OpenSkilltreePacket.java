package de.blutmondgilde.blutmondrpg.network.packets;

import de.blutmondgilde.blutmondrpg.gui.SkillTreeScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;

public class OpenSkilltreePacket {
    public static void encode(OpenSkilltreePacket msg, PacketBuffer buffer) {

    }

    public static OpenSkilltreePacket decode(PacketBuffer buffer) {
        return new OpenSkilltreePacket();
    }

    public static void handle(final OpenSkilltreePacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            try {
                if (context.get().getDirection().equals(NetworkDirection.PLAY_TO_CLIENT)) {
                    new SkillTreeScreen().openGui();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        context.get().setPacketHandled(true);
    }
}
