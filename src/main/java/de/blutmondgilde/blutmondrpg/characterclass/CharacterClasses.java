package de.blutmondgilde.blutmondrpg.characterclass;

import de.blutmondgilde.blutmondrpg.BlutmondRPG;
import de.blutmondgilde.blutmondrpg.characterclass.classes.DefaultCharacterClass;
import de.blutmondgilde.blutmondrpg.characterclass.classes.MageCharacterClass;
import de.blutmondgilde.blutmondrpg.characterclass.classes.PriestCharacterClass;
import de.blutmondgilde.blutmondrpg.characterclass.classes.ScoutCharacterClass;
import de.blutmondgilde.blutmondrpg.characterclass.classes.WarriorCharacterClass;
import de.blutmondgilde.blutmondrpg.util.Constants;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryBuilder;
import org.apache.logging.log4j.LogManager;

public class CharacterClasses {
    private static final DeferredRegister<CharacterClass> CHARACTER_CLASS_REGISTRY = DeferredRegister.create(CharacterClass.class, Constants.MOD_ID);
    public static final RegistryObject<CharacterClass> DEFAULT_CLASS = CHARACTER_CLASS_REGISTRY.register("none", DefaultCharacterClass::new);
    public static final RegistryObject<CharacterClass> MAGE_CLASS = CHARACTER_CLASS_REGISTRY.register("mage", MageCharacterClass::new);
    public static final RegistryObject<CharacterClass> PRIEST_CLASS = CHARACTER_CLASS_REGISTRY.register("priest", PriestCharacterClass::new);
    public static final RegistryObject<CharacterClass> SCOUT_CLASS = CHARACTER_CLASS_REGISTRY.register("scout", ScoutCharacterClass::new);
    public static final RegistryObject<CharacterClass> WARRIOR_CLASS = CHARACTER_CLASS_REGISTRY.register("warrior", WarriorCharacterClass::new);

    /**
     * Called from {@link BlutmondRPG#BlutmondRPG()}.
     * This will create the {@link CharacterClass} Registry and register the default classes
     * @param modEventBus BlutmondRPG Mod Event Bus
     */
    public static void register(final IEventBus modEventBus) {
        CHARACTER_CLASS_REGISTRY.makeRegistry("character_classes", RegistryBuilder::new);
        CHARACTER_CLASS_REGISTRY.register(modEventBus);
        LogManager.getLogger(Constants.MOD_ID).debug("Registered Character Classes.");
    }
}
