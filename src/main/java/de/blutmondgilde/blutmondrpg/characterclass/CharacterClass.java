package de.blutmondgilde.blutmondrpg.characterclass;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class CharacterClass extends ForgeRegistryEntry<CharacterClass> implements ICharacterClass {
    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent(getRegistryName().getNamespace() + "." + getRegistryName().getPath() + ".name");
    }
}
