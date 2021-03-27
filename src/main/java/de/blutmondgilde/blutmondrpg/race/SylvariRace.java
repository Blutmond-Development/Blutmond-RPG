package de.blutmondgilde.blutmondrpg.race;

import de.blutmondgilde.blutmondrpg.util.PlayerAttributeSetBuilder;
import net.minecraft.util.text.TranslationTextComponent;

public class SylvariRace extends Race {
    public SylvariRace() {
        super(new TranslationTextComponent("blutmondrpg.race.sylvari"), PlayerAttributeSetBuilder.builder().build());
    }

    @Override
    public float getSizeScaleFactor() {
        return 0.95F;
    }
}
