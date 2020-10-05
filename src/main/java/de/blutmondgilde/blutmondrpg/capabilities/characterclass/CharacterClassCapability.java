package de.blutmondgilde.blutmondrpg.capabilities.characterclass;

import de.blutmondgilde.blutmondrpg.capabilities.CapabilityManager;
import de.blutmondgilde.blutmondrpg.characterclass.CharacterClasses;
import de.blutmondgilde.blutmondrpg.util.Constants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import java.util.Optional;
import java.util.UUID;

public class CharacterClassCapability implements ICharacterClassCapability {
    /** {@link ResourceLocation} to identify the CharacterClassType */
    private ResourceLocation classType;
    /** Current player level */
    private int level;
    /** Current player exp */
    private double exp;
    /** Current player mana */
    private double mana;
    /** Maximum player mana */
    private double maxMana;

    /** {@link AttributeModifier} for player {@link Attributes#MAX_HEALTH} */
    private double classHPModifier;
    /** {@link AttributeModifier} for player {@link Attributes#KNOCKBACK_RESISTANCE} */
    private double classKnockbackResistanceModifier;
    /** {@link AttributeModifier} for player {@link Attributes#MOVEMENT_SPEED} */
    private float classMovementSpeedModifier;
    /** {@link AttributeModifier} for player {@link Attributes#ATTACK_DAMAGE} */
    private double classDamageModifier;
    /** {@link AttributeModifier} for player {@link Attributes#ATTACK_KNOCKBACK} */
    private double classKnockbackModifier;
    /** {@link AttributeModifier} for player {@link Attributes#ATTACK_SPEED} */
    private double classAttackSpeedModifier;
    /** {@link AttributeModifier} for player {@link Attributes#ARMOR} */
    private double classArmorModifier;
    /** {@link AttributeModifier} for player {@link Attributes#ARMOR_TOUGHNESS} */
    private double classArmorToughnessModifier;

    /** Default constructor with default values */
    public CharacterClassCapability() {
        this.classType                        = CharacterClasses.DEFAULT_CLASS.getId();
        this.level                            = 1;
        this.exp                              = 0;
        this.classHPModifier                  = 1;
        this.classKnockbackResistanceModifier = 1;
        this.classMovementSpeedModifier       = 1;
        this.classDamageModifier              = 1;
        this.classKnockbackModifier           = 1;
        this.classAttackSpeedModifier         = 1;
        this.classArmorModifier               = 1;
        this.classArmorToughnessModifier      = 1;
        this.mana                             = 1;
        this.maxMana                          = 1;
    }

    public ResourceLocation getClassType() {
        return classType;
    }

    public void setClassType(ResourceLocation classType) {
        this.classType = classType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    @Override
    public void setMana(double mana) {
        this.mana = mana;
    }

    @Override
    public double getMana() {
        return mana;
    }

    @Override
    public void setMaxMana(double maxMana) {
        this.maxMana = maxMana;
    }

    @Override
    public double getMaxMana() {
        return maxMana;
    }

    public double getClassHPModifier() {
        return classHPModifier;
    }

    public void setClassHPModifier(double classHPModifier) {
        this.classHPModifier = classHPModifier;
    }

    public double getClassKnockbackResistanceModifier() {
        return classKnockbackResistanceModifier;
    }

    public void setClassKnockbackResistanceModifier(double classKnockbackResistanceModifier) {
        this.classKnockbackResistanceModifier = classKnockbackResistanceModifier;
    }

    public float getClassMovementSpeedModifier() {
        return classMovementSpeedModifier;
    }

    public void setClassMovementSpeedModifier(float classMovementSpeedModifier) {
        this.classMovementSpeedModifier = classMovementSpeedModifier;
    }

    public double getClassDamageModifier() {
        return classDamageModifier;
    }

    public void setClassDamageModifier(double classDamageModifier) {
        this.classDamageModifier = classDamageModifier;
    }

    public double getClassKnockbackModifier() {
        return classKnockbackModifier;
    }

    public void setClassKnockbackModifier(double classKnockbackModifier) {
        this.classKnockbackModifier = classKnockbackModifier;
    }

    public double getClassAttackSpeedModifier() {
        return classAttackSpeedModifier;
    }

    public void setClassAttackSpeedModifier(double classAttackSpeedModifier) {
        this.classAttackSpeedModifier = classAttackSpeedModifier;
    }

    public double getClassArmorModifier() {
        return classArmorModifier;
    }

    public void setClassArmorModifier(double classArmorModifier) {
        this.classArmorModifier = classArmorModifier;
    }

    public double getClassArmorToughnessModifier() {
        return classArmorToughnessModifier;
    }

    public void setClassArmorToughnessModifier(double classArmorToughnessModifier) {
        this.classArmorToughnessModifier = classArmorToughnessModifier;
    }

    public static void attachCapability(final AttachCapabilitiesEvent<Entity> e) {
        if (!(e.getObject() instanceof PlayerEntity)) return;
        final PlayerEntity player = (PlayerEntity) e.getObject();
        e.addCapability(Constants.CAPABILITY_CHARACTER_CLASS, new CharacterClassCapabilityProvider());

        Optional<UUID> playerUUID = Optional.of(player.getUniqueID());
        CapabilityManager.logDebug("Attached CharacterClass Capability to player " + playerUUID.map(UUID::toString).orElse("Unknown"));
    }
}
