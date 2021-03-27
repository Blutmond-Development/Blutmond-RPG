package de.blutmondgilde.blutmondrpg.race;

import de.blutmondgilde.blutmondrpg.util.PlayerAttributeSetBuilder;
import net.minecraft.util.text.TranslationTextComponent;

public class HumanRace extends Race {
    public HumanRace() {
        super(new TranslationTextComponent("blutmondrpg.race.human"), PlayerAttributeSetBuilder.builder().build());
    }

    @Override
    public float getSizeScaleFactor() {
        return 1F;
    }
}
