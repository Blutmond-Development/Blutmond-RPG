package de.blutmondgilde.blutmondrpg.handler;

import de.blutmondgilde.blutmondrpg.BlutmondRPG;
import de.blutmondgilde.blutmondrpg.api.capabilities.IRaceProvider;
import de.blutmondgilde.blutmondrpg.capabilities.RaceImpl;
import de.blutmondgilde.blutmondrpg.capabilities.SerializableCapabilityStorage;
import de.blutmondgilde.blutmondrpg.network.BlutmondNetwork;
import de.blutmondgilde.blutmondrpg.network.SyncEntityCapabilityPacket;
import de.blutmondgilde.blutmondrpg.util.CapabilityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.concurrent.Callable;


public class CapabilityHandler {
    public static void init() {
        registerCapability(IRaceProvider.class, new SerializableCapabilityStorage<IRaceProvider>(), RaceImpl::new);
    }

    private static <T extends INBTSerializable<CompoundNBT>> void registerCapability(Class<T> interfaceClass, SerializableCapabilityStorage<T> storage, Callable<? extends T> implementation) {
        CapabilityManager.INSTANCE.register(interfaceClass, storage, implementation);
    }

    @Mod.EventBusSubscriber(modid = BlutmondRPG.MODID)
    public static class RaceEvents {
        @SubscribeEvent
        public static void onAttachRaceToPlayer(final AttachCapabilitiesEvent<Entity> e) {
            if (!(e.getObject() instanceof PlayerEntity)) return;
            if (e.getObject() instanceof FakePlayer) return;
            e.addCapability(RaceImpl.Provider.NAME, new RaceImpl.Provider());
        }

        @SubscribeEvent
        public static void cloneEvent(PlayerEvent.Clone e) {
            e.getOriginal().getCapability(CapabilityHelper.RACE_CAPABILITY).ifPresent(oldCap -> e.getEntityLiving().getCapability(CapabilityHelper.RACE_CAPABILITY).ifPresent(newCap -> newCap.deserializeNBT(oldCap.serializeNBT())));
        }

        @SubscribeEvent
        public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent e) {
            e.getPlayer().getCapability(CapabilityHelper.RACE_CAPABILITY).ifPresent(iRaceProvider -> iRaceProvider.sync(e.getPlayer()));
        }

        @SubscribeEvent
        public static void onPlayerDimChange(PlayerEvent.PlayerChangedDimensionEvent e) {
            e.getPlayer().getCapability(CapabilityHelper.RACE_CAPABILITY).ifPresent(iRaceProvider -> iRaceProvider.sync(e.getPlayer()));
        }

        @SubscribeEvent
        public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent e) {
            e.getPlayer().getCapability(CapabilityHelper.RACE_CAPABILITY).ifPresent(iRaceProvider -> iRaceProvider.sync(e.getPlayer()));
        }

        @SubscribeEvent
        public static void onPlayerTacking(PlayerEvent.StartTracking e) {
            if (!(e.getTarget() instanceof LivingEntity)) return;
            if (!(e.getPlayer() instanceof ServerPlayerEntity)) return;
            LivingEntity target = (LivingEntity) e.getTarget();
            target.getCapability(CapabilityHelper.RACE_CAPABILITY).ifPresent(iRaceProvider -> BlutmondNetwork.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(e::getPlayer), new SyncEntityCapabilityPacket(target.getId(), iRaceProvider.serializeNBT(), iRaceProvider.getCapabilityName())));
        }
    }
}
