package de.blutmondgilde.blutmondrpg.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.blutmondgilde.blutmondrpg.capabilities.characterclass.CharacterClassCapabilityProvider;
import de.blutmondgilde.blutmondrpg.capabilities.characterclass.ICharacterClassCapability;
import de.blutmondgilde.blutmondrpg.commands.arguments.CharacterClassArgument;
import de.blutmondgilde.blutmondrpg.network.BlutmondNetwork;
import de.blutmondgilde.blutmondrpg.network.packets.OpenSkilltreePacket;
import de.blutmondgilde.blutmondrpg.network.packets.SyncCharacterClassCapability;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;

public class CommandHandler {
    private static final String[] masterKeys = {"brpg", "blutmond", "blutmondrpg"};

    public static void registerCommands(final RegisterCommandsEvent e) {
        CommandHandler.register(e.getDispatcher());
    }

    public static void register(final CommandDispatcher<CommandSource> dispatcher) {
        for (String keys : masterKeys) {
            dispatcher.register(LiteralArgumentBuilder.<CommandSource>literal(keys).then(CommandOpenSkillTree.register()).then(CommandChangeClass.register()));
        }
    }

    private static class CommandOpenSkillTree {
        static ArgumentBuilder<CommandSource, ?> register() {
            return Commands.literal("skilltree").requires(cs -> cs.hasPermissionLevel(0)).executes(cs -> {
                BlutmondNetwork.sendToPlayer(new OpenSkilltreePacket(), cs.getSource().asPlayer());
                return 1;
            });
        }
    }

    private static class CommandChangeClass {
        static ArgumentBuilder<CommandSource, ?> register() {
            return Commands.literal("class").then(CommandSet.register());
        }


        private static class CommandSet {
            static ArgumentBuilder<CommandSource, ?> register() {
                return Commands
                        .literal("set")
                        .then(Commands.argument("class", CharacterClassArgument.characterClass()).requires(cs -> cs.hasPermissionLevel(0)).executes(cs -> {
                            ICharacterClassCapability cap = cs
                                    .getSource()
                                    .asPlayer()
                                    .getCapability(CharacterClassCapabilityProvider.CAPABILITY)
                                    .orElseThrow(() -> new IllegalStateException("Could not load CharacterClassCapability from ServerEntity"));

                            cap.setClassType(CharacterClassArgument.getCharacterClass(cs, "class").getRegistryName());
                            cap.recalculateAllModifier();
                            BlutmondNetwork.sendToPlayer(new SyncCharacterClassCapability(cap), cs.getSource().asPlayer());

                            return 1;
                        }));
            }
        }
    }
}
