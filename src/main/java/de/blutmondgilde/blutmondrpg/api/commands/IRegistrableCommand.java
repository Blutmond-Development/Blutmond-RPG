package de.blutmondgilde.blutmondrpg.api.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;

public interface IRegistrableCommand {
    default void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(getCommand());
    }

    LiteralArgumentBuilder<CommandSource> getCommand();

    default boolean hasPermission(final CommandSource source, final String permissionNode) {
        try {
            return PermissionAPI.hasPermission(source.getPlayerOrException(), permissionNode);
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    default void registerPermissionNode(String node, DefaultPermissionLevel level, String description) {
        PermissionAPI.registerNode(node, level, description);
    }
}
