package de.blutmondgilde.blutmondrpg.race;

import de.blutmondgilde.blutmondrpg.util.PlayerAttributeSetBuilder;
import net.minecraft.util.text.TranslationTextComponent;

public class CharrRace extends Race {
    public CharrRace() {
        super(new TranslationTextComponent("blutmondrpg.race.charr"), PlayerAttributeSetBuilder.builder().bonusHealth(4).bonusKnockbackResistance(0.01).build());
    }

    @Override
    public float getSizeScaleFactor() {
        return 1.1F;
    }
}
