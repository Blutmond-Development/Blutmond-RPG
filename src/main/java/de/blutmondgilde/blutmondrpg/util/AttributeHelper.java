package de.blutmondgilde.blutmondrpg.util;

import de.blutmondgilde.blutmondrpg.BlutmondRPG;
import de.blutmondgilde.blutmondrpg.attributes.PlayerAttributeSet;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import org.apache.logging.log4j.LogManager;

import java.util.UUID;

public class AttributeHelper {
    private static final UUID MODIFIER_ID_HEALTH = UUID.fromString("d44bd552-99a3-4092-b5e1-34159b6354e6");
    private static final UUID MODIFIER_ID_KNOCKBACK_RESISTANCE = UUID.fromString("20fb4caa-931a-4f0a-9a35-ec062ce22cdf");
    private static final UUID MODIFIER_ID_ARMOR = UUID.fromString("5ccbc237-5e01-4196-bdd7-7db71c29c6f4");
    private static final UUID MODIFIER_ID_ARMOR_TOUGHNESS = UUID.fromString("f3d1ea24-a63e-450e-829d-708d7a2411c3");
    private static final UUID MODIFIER_ID_MOVEMENT_SPEED = UUID.fromString("eab07de4-5125-45b7-ac2f-d8a83678d518");
    private static final UUID MODIFIER_ID_ATTACK_DAMAGE = UUID.fromString("caf1c81d-f82e-468a-a260-155dabebb525");
    private static final UUID MODIFIER_ID_ATTACK_SPEED = UUID.fromString("bad293e1-e20e-4a8d-a254-a6ee43b6c8e1");
    private static final UUID MODIFIER_ID_ATTACK_KNOCKBACK = UUID.fromString("bac35e6b-62ba-43bf-a942-b58994a9b896");
    private static final UUID MODIFIER_ID_LUCK = UUID.fromString("dce3c20c-fbab-477c-911e-ae9d06434499");

    private static final String MODIFIER_PREFIX = BlutmondRPG.MODID + ".modifier";

    private static final String MODIFIER_NAME_HEALTH = MODIFIER_PREFIX + ".health";
    private static final String MODIFIER_NAME_KNOCKBACK_RESISTANCE = MODIFIER_PREFIX + ".knockback_resistance";
    private static final String MODIFIER_NAME_ARMOR = MODIFIER_PREFIX + ".armor";
    private static final String MODIFIER_NAME_ARMOR_TOUGHNESS = MODIFIER_PREFIX + ".armor_toughness";
    private static final String MODIFIER_NAME_MOVEMENT_SPEED = MODIFIER_PREFIX + ".movement_speed";
    private static final String MODIFIER_NAME_ATTACK_DAMAGE = MODIFIER_PREFIX + ".attack_damage";
    private static final String MODIFIER_NAME_ATTACK_SPEED = MODIFIER_PREFIX + ".attack_speed";
    private static final String MODIFIER_NAME_ATTACK_KNOCKBACK = MODIFIER_PREFIX + ".attack_knockback";
    private static final String MODIFIER_NAME_LUCK = MODIFIER_PREFIX + ".luck";

    public static final double PLAYER_BASE_HEALTH = 20;
    public static final double PLAYER_BASE_KNOCKBACK_RESISTANCE = 0;
    public static final double PLAYER_BASE_ARMOR = 0;
    public static final double PLAYER_BASE_ARMOR_TOUGHNESS = 0;
    public static final double PLAYER_BASE_MOVEMENT_SPEED = 0.1;
    public static final double PLAYER_BASE_ATTACK_DAMAGE = 1;
    public static final double PLAYER_BASE_ATTACK_SPEED = 4;
    public static final double PLAYER_BASE_ATTACK_KNOCKBACK = 0;
    public static final double PLAYER_BASE_LUCK = 0;

    public static void applyTo(final LivingEntity entity, final PlayerAttributeSet playerAttributes) {
        setModifier(entity, Attributes.MAX_HEALTH, MODIFIER_ID_HEALTH, MODIFIER_NAME_HEALTH, playerAttributes.getMaxHealth());
        setModifier(entity, Attributes.KNOCKBACK_RESISTANCE, MODIFIER_ID_KNOCKBACK_RESISTANCE, MODIFIER_NAME_KNOCKBACK_RESISTANCE, playerAttributes.getKnochbackResistance());
        setModifier(entity, Attributes.ARMOR, MODIFIER_ID_ARMOR, MODIFIER_NAME_ARMOR, playerAttributes.getArmor());
        setModifier(entity, Attributes.ARMOR_TOUGHNESS, MODIFIER_ID_ARMOR_TOUGHNESS, MODIFIER_NAME_ARMOR_TOUGHNESS, playerAttributes.getArmorToughness());
        setModifier(entity, Attributes.MOVEMENT_SPEED, MODIFIER_ID_MOVEMENT_SPEED, MODIFIER_NAME_MOVEMENT_SPEED, playerAttributes.getMovementSpeed());
        setModifier(entity, Attributes.ATTACK_DAMAGE, MODIFIER_ID_ATTACK_DAMAGE, MODIFIER_NAME_ATTACK_DAMAGE, playerAttributes.getAttackDamage());
        setModifier(entity, Attributes.ATTACK_SPEED, MODIFIER_ID_ATTACK_SPEED, MODIFIER_NAME_ATTACK_SPEED, playerAttributes.getAttackSpeed());
        setModifier(entity, Attributes.ATTACK_KNOCKBACK, MODIFIER_ID_ATTACK_KNOCKBACK, MODIFIER_NAME_ATTACK_KNOCKBACK, playerAttributes.getAttackKnockback());
        setModifier(entity, Attributes.LUCK, MODIFIER_ID_LUCK, MODIFIER_NAME_LUCK, playerAttributes.getLuck());

        if (entity.getHealth() > entity.getMaxHealth()) {
            entity.setHealth(entity.getMaxHealth());
        }
    }

    private static void setModifier(final LivingEntity entity, final Attribute attribute, final UUID modifierId, final String modifierName, final double initialValue) {
        ModifiableAttributeInstance instance = entity.getAttribute(attribute);
        if (instance == null) return;
        AttributeModifier modifier = instance.getModifier(modifierId);
        if (modifier != null) instance.removeModifier(modifierId);

        double modValue = initialValue-getBaseValue(attribute);
        instance.addPermanentModifier(new AttributeModifier(modifierId, modifierName, modValue, AttributeModifier.Operation.ADDITION));

        LogManager.getLogger().debug("Applied Attribute " + attribute.getDescriptionId() + " with " + modValue + " from " + getBaseValue(attribute) + " and " + initialValue);
    }

    private static double getBaseValue(final Attribute attribute) {
        if (attribute.equals(Attributes.MAX_HEALTH)) return PLAYER_BASE_HEALTH;
        if (attribute.equals(Attributes.KNOCKBACK_RESISTANCE)) return PLAYER_BASE_KNOCKBACK_RESISTANCE;
        if (attribute.equals(Attributes.ARMOR)) return PLAYER_BASE_ARMOR;
        if (attribute.equals(Attributes.ARMOR_TOUGHNESS)) return PLAYER_BASE_ARMOR_TOUGHNESS;
        if (attribute.equals(Attributes.MOVEMENT_SPEED)) return PLAYER_BASE_MOVEMENT_SPEED;
        if (attribute.equals(Attributes.ATTACK_DAMAGE)) return PLAYER_BASE_ATTACK_DAMAGE;
        if (attribute.equals(Attributes.ATTACK_SPEED)) return PLAYER_BASE_ATTACK_SPEED;
        if (attribute.equals(Attributes.ATTACK_KNOCKBACK)) return PLAYER_BASE_ATTACK_KNOCKBACK;
        if (attribute.equals(Attributes.LUCK)) return PLAYER_BASE_LUCK;

        return 0;
    }
}
