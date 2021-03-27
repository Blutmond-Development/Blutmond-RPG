package de.blutmondgilde.blutmondrpg.handler;

import de.blutmondgilde.blutmondrpg.BlutmondRPG;
import de.blutmondgilde.blutmondrpg.race.Race;
import de.blutmondgilde.blutmondrpg.util.CapabilityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BlutmondRPG.MODID)
public class PlayerSizeHandler {

    @SubscribeEvent
    public static void changeSize(final EntityEvent.Size e) {
        if (!(e.getEntity() instanceof PlayerEntity)) return;
        if (e.getEntity() instanceof FakePlayer) return;

        e.getEntity().getCapability(CapabilityHelper.RACE_CAPABILITY).ifPresent(iRaceProvider -> {
            final Race race = iRaceProvider.getRace();
            final Entity entity = e.getEntity();

            e.setNewSize(race.getPlayerSize(e));
            e.setNewEyeHeight(race.getPlayerEyePos(e));
            entity.setBoundingBox(race.getPlayerBoundingBox(e));
        });
    }

    @SubscribeEvent
    public static void onRenderPlayerPre(final RenderPlayerEvent.Pre e) {
        PlayerEntity player = e.getPlayer();
        player.getCapability(CapabilityHelper.RACE_CAPABILITY).ifPresent(iRaceProvider -> {
            Race race = iRaceProvider.getRace();
            e.getMatrixStack().pushPose();
            e.getMatrixStack().scale(race.getSizeScaleFactor(), race.getSizeScaleFactor(), race.getSizeScaleFactor());

            e.getRenderer().shadowRadius = 0.5F * race.getSizeScaleFactor();
        });
    }

    @SubscribeEvent
    public static void onRenderPlayerPost(final RenderPlayerEvent.Post e) {
        PlayerEntity player = e.getPlayer();
        player.getCapability(CapabilityHelper.RACE_CAPABILITY).ifPresent(iRaceProvider -> e.getMatrixStack().popPose());
    }
}
