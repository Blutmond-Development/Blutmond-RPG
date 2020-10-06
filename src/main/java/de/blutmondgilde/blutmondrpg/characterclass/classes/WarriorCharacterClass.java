package de.blutmondgilde.blutmondrpg.characterclass.classes;

import com.feed_the_beast.mods.ftbguilibrary.icon.Icon;
import de.blutmondgilde.blutmondrpg.characterclass.CharacterClass;
import de.blutmondgilde.blutmondrpg.util.Constants;
import net.minecraft.util.ResourceLocation;
import java.util.Optional;

public class WarriorCharacterClass extends CharacterClass {
    public WarriorCharacterClass() {
        super(Icon.getIcon(new ResourceLocation(Constants.MOD_ID, "textures/icons/warrior.png")), Optional.empty());
    }

    @Override
    public double calculateHPModifier(int level) {
        return 20 + level * 2;
    }

    @Override
    public double calculateKnockbackResistanceModifier(int level) {
        return 0;
    }

    @Override
    public float calculateMovementSpeedModifier(int level) {
        return 0.7F + level * 0.0075F;
    }

    @Override
    public double calculateDamageModifier(int level) {
        return 4.0D;
    }

    @Override
    public double calculateKnockbackModifier(int level) {
        return 0;
    }

    @Override
    public double calculateAttackSpeedModifier(int level) {
        return 4.0D;
    }

    @Override
    public double calculateArmorModifier(int level) {
        return 4.0D;
    }

    @Override
    public double calculateArmorToughnessModifier(int level) {
        return 4.0D;
    }

    @Override
    public double calculateMaxMana(int level) {
        return level * 2;
    }
}
