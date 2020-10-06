package de.blutmondgilde.blutmondrpg.capabilities.characterclass;

import net.minecraft.util.ResourceLocation;


public interface ICharacterClassCapability {
    ResourceLocation getClassType();

    void setClassType(ResourceLocation classType);

    int getLevel();

    void setLevel(int level);

    double getExp();

    void setExp(double exp);

    double getClassHPModifier();

    void setClassHPModifier(double classHPModifier);

    double getClassKnockbackResistanceModifier();

    void setClassKnockbackResistanceModifier(double classKnockbackResistanceModifier);

    float getClassMovementSpeedModifier();

    void setClassMovementSpeedModifier(float classMovementSpeedModifier);

    double getClassDamageModifier();

    void setClassDamageModifier(double classDamageModifier);

    double getClassKnockbackModifier();

    void setClassKnockbackModifier(double classKnockbackModifier);

    double getClassAttackSpeedModifier();

    void setClassAttackSpeedModifier(double classAttackSpeedModifier);

    double getClassArmorModifier();

    void setClassArmorModifier(double classArmorModifier);

    double getClassArmorToughnessModifier();

    void setClassArmorToughnessModifier(double classArmorToughnessModifier);

    void setMana(double mana);

    double getMana();

    void setMaxMana(double maxMana);

    double getMaxMana();

    void recalculateAllModifier();

    void setMagicDamageModifier(double damage);

    double getMagicDamageModifier();
}
