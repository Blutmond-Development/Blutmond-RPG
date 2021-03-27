package de.blutmondgilde.blutmondrpg.race;

import de.blutmondgilde.blutmondrpg.util.PlayerAttributeSetBuilder;
import net.minecraft.util.text.TranslationTextComponent;

public class NornRace extends Race {
    public NornRace() {
        super(new TranslationTextComponent("blutmondrpg.race.norn"), PlayerAttributeSetBuilder.builder().bonusHealth(6).bonusKnockbackResistance(0.0125).bonusMovementSpeed(-0.02).bonusAttackKnockback(0.05).build());
    }

    @Override
    public float getSizeScaleFactor() {
        return 1.25F;
    }
}
