package de.blutmondgilde.blutmondrpg.capabilities.characterclass;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CharacterClassProvider implements ICapabilitySerializable<INBT> {
    /** {@link MinecraftForge} injects our {@link Capability} Information into this Field */
    @CapabilityInject(ICharacterClass.class)
    public static final Capability<ICharacterClass> CAPABILITY = null;
    /** Creates the {@link LazyOptional} of our {@link Capability} */
    private final LazyOptional<ICharacterClass> instance = LazyOptional.of(CAPABILITY::getDefaultInstance);

    /**
     * Returns the {@link LazyOptional} of our {@link Capability} we can get
     */
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == CAPABILITY ? instance.cast() : LazyOptional.empty();
    }

    /**
     * Uses the {@link Capability.IStorage#writeNBT} Method from our Storage Class to serialize our Information.
     */
    @Override
    public INBT serializeNBT() {
        return CAPABILITY.getStorage().writeNBT(CAPABILITY, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null);
    }

    /**
     * Uses the {@link Capability.IStorage#readNBT} Method from our Storage Class to read the given Information into our {@link Capability}
     */
    @Override
    public void deserializeNBT(INBT nbt) {
        CAPABILITY.getStorage().readNBT(CAPABILITY, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
    }
}
