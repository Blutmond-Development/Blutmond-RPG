package de.blutmondgilde.blutmondrpg.handler;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import de.blutmondgilde.blutmondrpg.api.commands.IRegistrableCommand;
import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;

import java.util.ArrayList;

public class CommandHandler {
    private static final ArrayList<IRegistrableCommand> COMMANDS = Lists.newArrayList();

    public static void init(final RegisterCommandsEvent e) {
        CommandDispatcher<CommandSource> commandDispatcher = e.getDispatcher();
        COMMANDS.forEach(command -> command.register(commandDispatcher));
    }

    public static void registerCommand(IRegistrableCommand command){
        COMMANDS.add(command);
    }
}
