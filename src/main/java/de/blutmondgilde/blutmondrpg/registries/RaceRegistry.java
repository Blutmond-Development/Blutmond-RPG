package de.blutmondgilde.blutmondrpg.registries;

import de.blutmondgilde.blutmondrpg.BlutmondRPG;
import de.blutmondgilde.blutmondrpg.race.*;
import de.blutmondgilde.blutmondrpg.util.PlayerAttributeSetBuilder;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryBuilder;

public class RaceRegistry {
    private static final DeferredRegister<Race> RACE_REGISTRY = DeferredRegister.create(Race.class, BlutmondRPG.MODID);
    private static final RegistryObject<Race> NONE = RACE_REGISTRY.register("none", () -> new Race(new TranslationTextComponent("blutmondrpg.race.none"), PlayerAttributeSetBuilder.builder().build()) {
        @Override
        public float getSizeScaleFactor() {
            return 1F;
        }
    });

    private static final RegistryObject<Race> HUMAN = RACE_REGISTRY.register("human", HumanRace::new);
    private static final RegistryObject<Race> ASURA = RACE_REGISTRY.register("asura", AsuraRace::new);
    private static final RegistryObject<Race> SYLVARI = RACE_REGISTRY.register("sylvari", SylvariRace::new);
    private static final RegistryObject<Race> NORN = RACE_REGISTRY.register("norn", NornRace::new);
    private static final RegistryObject<Race> CHARR = RACE_REGISTRY.register("charr", CharrRace::new);


    public static void init(final IEventBus modEventBus) {
        RACE_REGISTRY.makeRegistry("races", RegistryBuilder::new);
        RACE_REGISTRY.register(modEventBus);
    }
}
