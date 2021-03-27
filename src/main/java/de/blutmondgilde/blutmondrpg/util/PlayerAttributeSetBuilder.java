package de.blutmondgilde.blutmondrpg.util;

import de.blutmondgilde.blutmondrpg.attributes.PlayerAttributeSet;

public class PlayerAttributeSetBuilder {
    private double maxHealth = AttributeHelper.PLAYER_BASE_HEALTH;
    private double knochbackResistance = AttributeHelper.PLAYER_BASE_KNOCKBACK_RESISTANCE;
    private double armor = AttributeHelper.PLAYER_BASE_ARMOR;
    private double armorToughness = AttributeHelper.PLAYER_BASE_ARMOR_TOUGHNESS;
    private double movementSpeed = AttributeHelper.PLAYER_BASE_MOVEMENT_SPEED;
    private double attackDamage = AttributeHelper.PLAYER_BASE_ATTACK_DAMAGE;
    private double attackSpeed = AttributeHelper.PLAYER_BASE_ATTACK_SPEED;
    private double attackKnockback = AttributeHelper.PLAYER_BASE_ATTACK_KNOCKBACK;
    private double luck = AttributeHelper.PLAYER_BASE_LUCK;

    public static PlayerAttributeSetBuilder builder() {
        return new PlayerAttributeSetBuilder();
    }

    public PlayerAttributeSetBuilder bonusHealth(double value) {
        this.maxHealth += value;
        return this;
    }

    public PlayerAttributeSetBuilder bonusKnockbackResistance(double value) {
        this.knochbackResistance += value;
        return this;
    }

    public PlayerAttributeSetBuilder bonusArmor(double value) {
        this.armor += value;
        return this;
    }

    public PlayerAttributeSetBuilder bonusArmorToughness(double value) {
        this.armorToughness += value;
        return this;
    }

    public PlayerAttributeSetBuilder bonusAttackSpeed(double value) {
        this.attackSpeed += value;
        return this;
    }

    public PlayerAttributeSetBuilder bonusMovementSpeed(double value) {
        this.movementSpeed += value;
        return this;
    }

    public PlayerAttributeSetBuilder bonusAttackDamage(double value) {
        this.attackDamage += value;
        return this;
    }

    public PlayerAttributeSetBuilder bonusAttackKnockback(double value) {
        this.attackKnockback += value;
        return this;
    }

    public PlayerAttributeSetBuilder bonusLuck(double value) {
        this.luck += value;
        return this;
    }

    public PlayerAttributeSet build() {
        return new PlayerAttributeSet(this.maxHealth, this.knochbackResistance, this.armor, this.armorToughness, this.movementSpeed, this.attackDamage, this.attackSpeed, this.attackKnockback, this.luck);
    }
}
