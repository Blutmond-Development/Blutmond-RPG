package de.blutmondgilde.blutmondrpg.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.blutmondgilde.blutmondrpg.characterclass.CharacterClass;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class CharacterClassArgument implements ArgumentType<CharacterClass> {

    public static CharacterClassArgument characterClass() {
        return new CharacterClassArgument();
    }

    public static CharacterClass getCharacterClass(CommandContext<CommandSource> context, String name) {
        return context.getArgument(name, CharacterClass.class);
    }

    @Override
    public CharacterClass parse(StringReader reader) throws CommandSyntaxException {
        return GameRegistry.findRegistry(CharacterClass.class).getValue(new ResourceLocation(reader.readQuotedString()));
    }

    @Override
    public Collection<String> getExamples() {
        return Collections.singletonList("\"blutmondrpg:none\"");
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        ArrayList<String> list = new ArrayList<>();
        GameRegistry
                .findRegistry(CharacterClass.class)
                .getEntries()
                .forEach(entry -> list.add('"' + Objects.requireNonNull(entry.getValue().getRegistryName()).toString() + '"'));
        return ISuggestionProvider.suggest(list, builder);
    }
}
