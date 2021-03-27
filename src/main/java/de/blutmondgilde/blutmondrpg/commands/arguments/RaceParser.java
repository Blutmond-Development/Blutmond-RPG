package de.blutmondgilde.blutmondrpg.commands.arguments;


import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.blutmondgilde.blutmondrpg.race.Race;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public class RaceParser {
    private static final BiFunction<SuggestionsBuilder, Race, CompletableFuture<Suggestions>> DEFAULT_SUGGESTIONS_BUILDER = (suggestionsBuilder, race) -> suggestionsBuilder.buildFuture();
    public static final DynamicCommandExceptionType BAD_RACE_ID = new DynamicCommandExceptionType(o -> {
        return new TranslationTextComponent("blutmondrpg.commands.exceptions.unknown_race", o);
    });
    private BiFunction<SuggestionsBuilder, Race, CompletableFuture<Suggestions>> suggestionsBuilder = DEFAULT_SUGGESTIONS_BUILDER;
    private final StringReader reader;
    @Getter
    private Race race;


    public RaceParser parse() throws CommandSyntaxException {
        this.suggestionsBuilder = this::suggestRace;
        readRace();
        return this;
    }

    private CompletableFuture<Suggestions> suggestRace(SuggestionsBuilder suggestionsBuilder, Race race) {
        return ISuggestionProvider.suggest(GameRegistry.findRegistry(Race.class).getKeys().stream().map(ResourceLocation::toString), suggestionsBuilder);
    }

    private void readRace() throws CommandSyntaxException {
        int i = this.reader.getCursor();
        ResourceLocation resourceLocation = ResourceLocation.read(this.reader);

        IForgeRegistry<Race> registry = GameRegistry.findRegistry(Race.class);
        if (registry == null) return;
        Optional<Race> optionalRace = Optional.ofNullable(registry.getValue(resourceLocation));
        this.race = optionalRace.orElseThrow(() -> {
            this.reader.setCursor(i);
            return BAD_RACE_ID.createWithContext(this.reader, resourceLocation.toString());
        });
    }

}
