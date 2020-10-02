package de.blutmondgilde.blutmondrpg.capabilities.characterclass;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;

public class CharacterClassStorage implements Capability.IStorage<ICharacterClass> {
    private static final String classType = "classType", level = "level", exp = "exp", classHPModifier = "classHPModifier", classKnockbackResistanceModifier = "classKnockbackResistanceModifier", classMovementSpeedModifier = "classMovementSpeedModifier", classDamageModifier = "classDamageModifier", classKnockbackModifier = "classKnockbackModifier", classAttackSpeedModifier = "classAttackSpeedModifier", classArmorModifier = "classArmorModifier", classArmorToughnessModifier = "classArmorToughnessModifier";

    @Override
    public INBT writeNBT(Capability<ICharacterClass> capability, ICharacterClass instance, Direction side) {
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
        return nbt;
    }

    @Override
    public void readNBT(Capability<ICharacterClass> capability, ICharacterClass instance, Direction side, INBT nbt) {
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
    }
}
