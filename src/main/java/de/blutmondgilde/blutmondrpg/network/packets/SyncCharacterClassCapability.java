package de.blutmondgilde.blutmondrpg.network.packets;

import de.blutmondgilde.blutmondrpg.capabilities.characterclass.CharacterClassCapabilityProvider;
import de.blutmondgilde.blutmondrpg.capabilities.characterclass.ICharacterClassCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import java.util.function.Supplier;

public class SyncCharacterClassCapability {
    private final ResourceLocation classType;
    private final int level;
    private final double exp;
    private final double mana;
    private final double maxMana;
    private final double magicDamage;
    private final double classHPModifier;
    private final double classKnockbackResistanceModifier;
    private final float classMovementSpeedModifier;
    private final double classDamageModifier;
    private final double classKnockbackModifier;
    private final double classAttackSpeedModifier;
    private final double classArmorModifier;
    private final double classArmorToughnessModifier;

    public SyncCharacterClassCapability(final ResourceLocation classType, final int level, final double exp, final double mana, final double maxMana, final double magicDamage, final double classHPModifier, final double classKnockbackResistanceModifier, final float classMovementSpeedModifier, final double classDamageModifier, final double classKnockbackModifier, final double classAttackSpeedModifier, final double classArmorModifier, final double classArmorToughnessModifier) {
        this.classType                        = classType;
        this.level                            = level;
        this.exp                              = exp;
        this.mana                             = mana;
        this.maxMana                          = maxMana;
        this.magicDamage                      = magicDamage;
        this.classHPModifier                  = classHPModifier;
        this.classKnockbackResistanceModifier = classKnockbackResistanceModifier;
        this.classMovementSpeedModifier       = classMovementSpeedModifier;
        this.classDamageModifier              = classDamageModifier;
        this.classKnockbackModifier           = classKnockbackModifier;
        this.classAttackSpeedModifier         = classAttackSpeedModifier;
        this.classArmorModifier               = classArmorModifier;
        this.classArmorToughnessModifier      = classArmorToughnessModifier;
    }

    public SyncCharacterClassCapability(final ICharacterClassCapability characterClassCapability) {
        this.classType                        = characterClassCapability.getClassType();
        this.level                            = characterClassCapability.getLevel();
        this.exp                              = characterClassCapability.getExp();
        this.mana                             = characterClassCapability.getMana();
        this.maxMana                          = characterClassCapability.getMaxMana();
        this.magicDamage                      = characterClassCapability.getMagicDamageModifier();
        this.classHPModifier                  = characterClassCapability.getClassHPModifier();
        this.classKnockbackResistanceModifier = characterClassCapability.getClassKnockbackResistanceModifier();
        this.classMovementSpeedModifier       = characterClassCapability.getClassMovementSpeedModifier();
        this.classDamageModifier              = characterClassCapability.getClassDamageModifier();
        this.classKnockbackModifier           = characterClassCapability.getClassKnockbackModifier();
        this.classAttackSpeedModifier         = characterClassCapability.getClassAttackSpeedModifier();
        this.classArmorModifier               = characterClassCapability.getClassArmorModifier();
        this.classArmorToughnessModifier      = characterClassCapability.getClassArmorToughnessModifier();
    }

    public static void encode(final SyncCharacterClassCapability msg, final PacketBuffer buffer) {
        buffer.writeResourceLocation(msg.classType);
        buffer.writeInt(msg.level);
        buffer.writeDouble(msg.exp);
        buffer.writeDouble(msg.mana);
        buffer.writeDouble(msg.maxMana);
        buffer.writeDouble(msg.magicDamage);
        buffer.writeDouble(msg.classHPModifier);
        buffer.writeDouble(msg.classKnockbackResistanceModifier);
        buffer.writeFloat(msg.classMovementSpeedModifier);
        buffer.writeDouble(msg.classDamageModifier);
        buffer.writeDouble(msg.classKnockbackModifier);
        buffer.writeDouble(msg.classAttackSpeedModifier);
        buffer.writeDouble(msg.classArmorModifier);
        buffer.writeDouble(msg.classArmorToughnessModifier);
    }

    public static SyncCharacterClassCapability decode(final PacketBuffer buffer) {
        return new SyncCharacterClassCapability(buffer.readResourceLocation(),
                                                buffer.readInt(),
                                                buffer.readDouble(),
                                                buffer.readDouble(),
                                                buffer.readDouble(),
                                                buffer.readDouble(),
                                                buffer.readDouble(),
                                                buffer.readDouble(),
                                                buffer.readFloat(),
                                                buffer.readDouble(),
                                                buffer.readDouble(),
                                                buffer.readDouble(),
                                                buffer.readDouble(),
                                                buffer.readDouble());
    }

    public static void handle(final SyncCharacterClassCapability msg, final Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            try {
                if (context.get().getDirection().equals(NetworkDirection.PLAY_TO_CLIENT)) {
                    ICharacterClassCapability characterClassCapability = Minecraft.getInstance().player
                            .getCapability(CharacterClassCapabilityProvider.CAPABILITY)
                            .orElseThrow(() -> new IllegalArgumentException("Exeption on writing local capability"));

                    characterClassCapability.setClassType(msg.classType);
                    characterClassCapability.setLevel(msg.level);
                    characterClassCapability.setExp(msg.exp);
                    characterClassCapability.setMana(msg.mana);
                    characterClassCapability.setMaxMana(msg.maxMana);
                    characterClassCapability.setMagicDamageModifier(msg.magicDamage);
                    characterClassCapability.setClassHPModifier(msg.classHPModifier);
                    characterClassCapability.setClassKnockbackResistanceModifier(msg.classKnockbackResistanceModifier);
                    characterClassCapability.setMagicDamageModifier(msg.classMovementSpeedModifier);
                    characterClassCapability.setClassDamageModifier(msg.classDamageModifier);
                    characterClassCapability.setClassKnockbackModifier(msg.classKnockbackModifier);
                    characterClassCapability.setClassAttackSpeedModifier(msg.classAttackSpeedModifier);
                    characterClassCapability.setClassArmorModifier(msg.classArmorModifier);
                    characterClassCapability.setClassAttackSpeedModifier(msg.classArmorToughnessModifier);

                    LogManager.getLogger("BlutmondNetwork/SyncTask").debug("Synced Character Class Capability");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        context.get().setPacketHandled(true);
    }
}
