package de.blutmondgilde.blutmondrpg.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.blutmondgilde.blutmondrpg.BlutmondRPG;
import de.blutmondgilde.blutmondrpg.api.capabilities.IRaceProvider;
import de.blutmondgilde.blutmondrpg.api.commands.IRegistrableCommand;
import de.blutmondgilde.blutmondrpg.commands.arguments.RaceArgument;
import de.blutmondgilde.blutmondrpg.race.Race;
import de.blutmondgilde.blutmondrpg.race.Races;
import de.blutmondgilde.blutmondrpg.util.CapabilityHelper;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.server.permission.DefaultPermissionLevel;

public class BlutmondRPGCommand implements IRegistrableCommand {
    private static final String PERMISSION_NODE_BASE = BlutmondRPG.MODID;
    private static final String PERMISSION_NODE_RACE = PERMISSION_NODE_BASE + ".race";

    public BlutmondRPGCommand() {
        registerPermissionNode(PERMISSION_NODE_BASE + ".base", DefaultPermissionLevel.ALL, "");

        registerPermissionNode(PERMISSION_NODE_RACE + ".base", DefaultPermissionLevel.ALL, "");
        registerPermissionNode(PERMISSION_NODE_RACE + ".info", DefaultPermissionLevel.ALL, "");
        registerPermissionNode(PERMISSION_NODE_RACE + ".info.other", DefaultPermissionLevel.OP, "");
        registerPermissionNode(PERMISSION_NODE_RACE + ".setup", DefaultPermissionLevel.ALL, "");
        registerPermissionNode(PERMISSION_NODE_RACE + ".set", DefaultPermissionLevel.OP, "");
        registerPermissionNode(PERMISSION_NODE_RACE + ".set.other", DefaultPermissionLevel.OP, "");
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> getCommand() {
        LiteralArgumentBuilder<CommandSource> masterCommand = Commands.literal("blutmondrpg").requires(source -> hasPermission(source, PERMISSION_NODE_BASE + ".base"));
        masterCommand.then(
                Commands.literal("race").requires(source -> hasPermission(source, PERMISSION_NODE_BASE + ".race.base")).then(
                        Commands.literal("info").requires(source -> hasPermission(source, PERMISSION_NODE_RACE + ".info")).executes(BlutmondRPGCommand::onRaceInfoCommand).then(
                                Commands.argument("player", EntityArgument.player()).requires(source -> hasPermission(source, PERMISSION_NODE_RACE + ".info.other")).executes(BlutmondRPGCommand::onRaceInfoOtherCommand)
                        )
                ).then(
                        Commands.literal("setup").requires(source -> hasPermission(source, PERMISSION_NODE_RACE + ".setup")).then(
                                Commands.argument("race", RaceArgument.race()).requires(source -> hasPermission(source, PERMISSION_NODE_RACE + ".setup")).executes(BlutmondRPGCommand::onRaceSetupCommand)
                        )
                ).then(
                        Commands.literal("set").requires(source -> hasPermission(source, PERMISSION_NODE_RACE + ".set")).then(
                                Commands.argument("race", RaceArgument.race()).requires(source -> hasPermission(source, PERMISSION_NODE_RACE + ".set")).executes(BlutmondRPGCommand::onRaceSetCommand).then(
                                        Commands.argument("player", EntityArgument.player()).requires(source -> hasPermission(source, PERMISSION_NODE_RACE + ".set.other")).executes(BlutmondRPGCommand::onRaceSetOtherCommand)
                                )
                        )
                )
        );

        return masterCommand;
    }

    private static int onRaceInfoCommand(CommandContext<CommandSource> context) throws CommandSyntaxException {
        context.getSource().getPlayerOrException().getCapability(CapabilityHelper.RACE_CAPABILITY)
                .ifPresent(iRaceProvider -> context.getSource()
                        .sendSuccess(new TranslationTextComponent("blutmondrpg.commands.feedback.race.info", iRaceProvider.getRace().getName()), true));
        return 1;
    }

    private static int onRaceInfoOtherCommand(CommandContext<CommandSource> context) {
        PlayerEntity target = context.getArgument("player", PlayerEntity.class);
        target.getCapability(CapabilityHelper.RACE_CAPABILITY)
                .ifPresent(iRaceProvider -> context.getSource()
                        .sendSuccess(new TranslationTextComponent("blutmondrpg.commands.feedback.race.info.other", target.getDisplayName().getString(), iRaceProvider.getRace().getName()), true));
        return 1;
    }

    private static int onRaceSetupCommand(CommandContext<CommandSource> context) throws CommandSyntaxException {
        IRaceProvider raceCap = context.getSource().getPlayerOrException().getCapability(CapabilityHelper.RACE_CAPABILITY).orElseThrow(() -> new IllegalStateException("Exception while setup race capability."));
        if (!raceCap.getRace().equals(Races.NONE)) {
            context.getSource().sendSuccess(new TranslationTextComponent("blutmondrpg.commands.feedback.setup.race.already"), true);
            return 1;
        }
        raceCap.setRace(context.getArgument("race", Race.class));
        raceCap.sync(context.getSource().getPlayerOrException());
        return 1;
    }

    private static int onRaceSetCommand(CommandContext<CommandSource> context) throws CommandSyntaxException {
        IRaceProvider raceCap = context.getSource().getPlayerOrException().getCapability(CapabilityHelper.RACE_CAPABILITY).orElseThrow(() -> new IllegalStateException("Exception while setup race capability."));
        Race race = context.getArgument("race", Race.class);
        raceCap.setRace(race);
        raceCap.sync(context.getSource().getPlayerOrException());
        context.getSource().sendSuccess(new TranslationTextComponent("blutmondrpg.commands.feedback.set.race", race.getName().getString()), true);
        return 1;
    }

    private static int onRaceSetOtherCommand(CommandContext<CommandSource> context) {
        PlayerEntity target = context.getArgument("player", PlayerEntity.class);
        IRaceProvider raceCap = target.getCapability(CapabilityHelper.RACE_CAPABILITY).orElseThrow(() -> new IllegalStateException("Exception while setup race capability."));
        Race race = context.getArgument("race", Race.class);
        raceCap.setRace(race);
        raceCap.sync(target);
        context.getSource().sendSuccess(new TranslationTextComponent("blutmondrpg.commands.feedback.set.race.other", target.getDisplayName().getString(), race.getName().getString()), true);
        return 1;
    }
}
