package de.blutmondgilde.blutmondrpg.capabilities;

import de.blutmondgilde.blutmondrpg.BlutmondRPG;
import de.blutmondgilde.blutmondrpg.api.capabilities.IRaceProvider;
import de.blutmondgilde.blutmondrpg.network.BlutmondNetwork;
import de.blutmondgilde.blutmondrpg.network.SyncEntityCapabilityPacket;
import de.blutmondgilde.blutmondrpg.race.Race;
import de.blutmondgilde.blutmondrpg.race.Races;
import de.blutmondgilde.blutmondrpg.util.AttributeHelper;
import de.blutmondgilde.blutmondrpg.util.CapabilityHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RaceImpl implements IRaceProvider {
    private Race race;
    private Pose lastPose = null;

    public RaceImpl() {
        this.race = Races.NONE;
    }

    @Override
    public Race getRace() {
        return this.race;
    }

    @Override
    public void setRace(Race race) {
        this.race = race;
    }

    @Override
    public void setLastPose(Pose pose) {
        this.lastPose = pose;
    }

    @Override
    public Pose getLastPose() {
        return this.lastPose;
    }

    @Override
    public void sync(@Nonnull LivingEntity entity) {
        BlutmondNetwork.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), new SyncEntityCapabilityPacket(entity.getId(), serializeNBT(), getCapabilityName()));
        AttributeHelper.applyTo(entity, this.race.getBaseStats());
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        IForgeRegistry<Race> raceRegistry = GameRegistry.findRegistry(Race.class);
        nbt.putString("race", raceRegistry.getKey(this.race).toString());
        if (this.lastPose != null) {
            nbt.putString("last_pose", this.lastPose.toString());
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        IForgeRegistry<Race> raceRegistry = GameRegistry.findRegistry(Race.class);
        this.race = raceRegistry.getValue(new ResourceLocation(nbt.getString("race")));

        if (nbt.contains("last_pose")) {
            this.lastPose = Pose.valueOf(nbt.getString("last_pose"));
        }
    }

    @Override
    public String getCapabilityName() {
        return CapabilityHelper.RACE_CAPABILITY.getName();
    }

    public static class Provider implements ICapabilitySerializable<CompoundNBT> {
        public static final ResourceLocation NAME = new ResourceLocation(BlutmondRPG.MODID, "race");
        private final RaceImpl impl;
        private final LazyOptional<IRaceProvider> capability;

        public Provider() {
            this.impl = new RaceImpl();
            this.capability = LazyOptional.of(() -> this.impl);
        }


        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            if (cap == CapabilityHelper.RACE_CAPABILITY) {
                return this.capability.cast();
            }
            return LazyOptional.empty();
        }

        @Override
        public CompoundNBT serializeNBT() {
            return this.impl.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt) {
            this.impl.deserializeNBT(nbt);
        }
    }
}
