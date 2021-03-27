package de.blutmondgilde.blutmondrpg.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.blutmondgilde.blutmondrpg.race.Race;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.concurrent.CompletableFuture;

public class RaceArgument implements ArgumentType<Race> {
    @Override
    public Race parse(StringReader reader) throws CommandSyntaxException {
        RaceParser parser = (new RaceParser(reader)).parse();
        return parser.getRace();
    }

    public static RaceArgument race() {
        return new RaceArgument();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return ISuggestionProvider.suggest(GameRegistry.findRegistry(Race.class).getKeys().stream().map(ResourceLocation::toString), builder);
    }
}
