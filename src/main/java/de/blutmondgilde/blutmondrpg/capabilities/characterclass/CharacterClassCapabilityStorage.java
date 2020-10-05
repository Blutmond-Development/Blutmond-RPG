package de.blutmondgilde.blutmondrpg.capabilities.characterclass;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;

public class CharacterClassCapabilityStorage implements Capability.IStorage<ICharacterClassCapability> {
    private static final String classType = "classType", level = "level", exp = "exp", classHPModifier = "classHPModifier", classKnockbackResistanceModifier = "classKnockbackResistanceModifier", classMovementSpeedModifier = "classMovementSpeedModifier", classDamageModifier = "classDamageModifier", classKnockbackModifier = "classKnockbackModifier", classAttackSpeedModifier = "classAttackSpeedModifier", classArmorModifier = "classArmorModifier", classArmorToughnessModifier = "classArmorToughnessModifier", mana = "mana", maxMana = "maxMana";

    /** Serialize a {@link Capability} to {@link CompoundNBT} Information */
    @Override
    public INBT writeNBT(Capability<ICharacterClassCapability> capability, ICharacterClassCapability instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString(classType, instance.getClassType().toString());
        nbt.putInt(level, instance.getLevel());
        nbt.putDouble(exp, instance.getExp());
        nbt.putDouble(classHPModifier, instance.getClassHPModifier());
        nbt.putDouble(classKnockbackResistanceModifier, instance.getClassKnockbackResistanceModifier());
        nbt.putFloat(classMovementSpeedModifier, instance.getClassMovementSpeedModifier());
        nbt.putDouble(classDamageModifier, instance.getClassDamageModifier());
        nbt.putDouble(classKnockbackModifier, instance.getClassKnockbackModifier());
        nbt.putDouble(classAttackSpeedModifier, instance.getClassAttackSpeedModifier());
        nbt.putDouble(classArmorModifier, instance.getClassArmorModifier());
        nbt.putDouble(classArmorToughnessModifier, instance.getClassArmorToughnessModifier());
        nbt.putDouble(maxMana, instance.getMaxMana());
        nbt.putDouble(mana, instance.getMana());
        return nbt;
    }

    /** Reads and writes {@link CompoundNBT} Information to a {@link Capability} instance */
    @Override
    public void readNBT(Capability<ICharacterClassCapability> capability, ICharacterClassCapability instance, Direction side, INBT nbt) {
        CompoundNBT data = (CompoundNBT) nbt;

        instance.setClassType(new ResourceLocation(data.getString(classType)));
        instance.setLevel(data.getInt(level));
        instance.setExp(data.getDouble(exp));
        instance.setClassHPModifier(data.getDouble(classHPModifier));
        instance.setClassKnockbackResistanceModifier(data.getDouble(classKnockbackResistanceModifier));
        instance.setClassMovementSpeedModifier(data.getFloat(classMovementSpeedModifier));
        instance.setClassDamageModifier(data.getDouble(classDamageModifier));
        instance.setClassKnockbackModifier(data.getDouble(classKnockbackModifier));
        instance.setClassAttackSpeedModifier(data.getDouble(classAttackSpeedModifier));
        instance.setClassArmorModifier(data.getDouble(classArmorModifier));
        instance.setClassArmorToughnessModifier(data.getDouble(classArmorToughnessModifier));
        instance.setMaxMana(data.getDouble(maxMana));
        instance.setMana(data.getDouble(mana));
    }
}
