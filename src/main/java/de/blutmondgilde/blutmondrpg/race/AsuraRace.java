package de.blutmondgilde.blutmondrpg.race;

import de.blutmondgilde.blutmondrpg.util.PlayerAttributeSetBuilder;
import net.minecraft.util.text.TranslationTextComponent;

public class AsuraRace extends Race {
    public AsuraRace() {
        super(new TranslationTextComponent("blutmondrpg.race.asura"), PlayerAttributeSetBuilder.builder().bonusHealth(-6).bonusMovementSpeed(0.05).bonusAttackSpeed(1).bonusLuck(1).build());
    }

    @Override
    public float getSizeScaleFactor() {
        return 0.5F;
    }
}
