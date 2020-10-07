package de.blutmondgilde.blutmondrpg.characterclass.classes;

import com.feed_the_beast.mods.ftbguilibrary.icon.Icon;
import de.blutmondgilde.blutmondrpg.characterclass.CharacterClass;
import de.blutmondgilde.blutmondrpg.util.Constants;
import net.minecraft.util.ResourceLocation;
import java.util.Optional;

public class ScoutCharacterClass extends CharacterClass {
    public ScoutCharacterClass() {
        super(Icon.getIcon(new ResourceLocation(Constants.MOD_ID, "textures/icons/scout.png")), Optional.empty());
    }

    @Override
    public double calculateHPModifier(int level) {
        return 15 + level * 2;
    }

    @Override
    public double calculateKnockbackResistanceModifier(int level) {
        return 0;
    }

    @Override
    public float calculateMovementSpeedModifier(int level) {
        return 0.8F + level * 0.01F;
    }

    @Override
    public double calculateDamageModifier(int level) {
        return 2.0D;
    }

    @Override
    public double calculateKnockbackModifier(int level) {
        return 0;
    }

    @Override
    public double calculateAttackSpeedModifier(int level) {
        return 6.0D;
    }

    @Override
    public double calculateArmorModifier(int level) {
        return 2.0D;
    }

    @Override
    public double calculateArmorToughnessModifier(int level) {
        return 2.0D;
    }

    @Override
    public double calculateMaxMana(int level) {
        return 10 + level * 2;
    }

    @Override
    public double calculateMagicDamageModifier(int level) {
        return 1.5D;
    }
}
