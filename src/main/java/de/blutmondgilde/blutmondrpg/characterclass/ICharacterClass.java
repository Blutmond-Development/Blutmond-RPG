package de.blutmondgilde.blutmondrpg.characterclass;

import net.minecraft.util.text.ITextComponent;

public interface ICharacterClass {
    double calculateHPModifier(int level);

    double calculateKnockbackResistanceModifier(int level);

    float calculateMovementSpeedModifier(int level);

    double calculateDamageModifier(int level);

    double calculateKnockbackModifier(int level);

    double calculateAttackSpeedModifier(int level);

    double calculateArmorModifier(int level);

    double calculateArmorToughnessModifier(int level);

    ITextComponent getDisplayName();
}
