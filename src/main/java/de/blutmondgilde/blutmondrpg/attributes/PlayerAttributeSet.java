package de.blutmondgilde.blutmondrpg.attributes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerAttributeSet {
    @Getter
    private final double maxHealth;
    @Getter
    private final double knochbackResistance;
    @Getter
    private final double armor;
    @Getter
    private final double armorToughness;
    @Getter
    private final double movementSpeed;
    @Getter
    private final double attackDamage;
    @Getter
    private final double attackSpeed;
    @Getter
    private final double attackKnockback;
    @Getter
    private final double luck;
}
