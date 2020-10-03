package de.blutmondgilde.blutmondrpg.characterclass.classes;

import de.blutmondgilde.blutmondrpg.characterclass.CharacterClass;

public class DefaultCharacterClass extends CharacterClass {
    @Override
    public double calculateHPModifier(int level) {
        return 2;
    }

    @Override
    public double calculateKnockbackResistanceModifier(int level) {
        return 0;
    }

    @Override
    public float calculateMovementSpeedModifier(int level) {
        return 0;
    }

    @Override
    public double calculateDamageModifier(int level) {
        return 0;
    }

    @Override
    public double calculateKnockbackModifier(int level) {
        return 0;
    }

    @Override
    public double calculateAttackSpeedModifier(int level) {
        return 0;
    }

    @Override
    public double calculateArmorModifier(int level) {
        return 0;
    }

    @Override
    public double calculateArmorToughnessModifier(int level) {
        return 0;
    }
}
