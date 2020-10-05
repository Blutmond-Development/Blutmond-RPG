package de.blutmondgilde.blutmondrpg.characterclass;

import com.feed_the_beast.mods.ftbguilibrary.icon.Icon;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistryEntry;
import java.util.Optional;

public abstract class CharacterClass extends ForgeRegistryEntry<CharacterClass> implements ICharacterClass {
    final Icon icon;
    final Optional<CharacterClass> parent;

    public CharacterClass() {
        this(null);
    }

    public CharacterClass(Icon icon) {
        this.icon   = icon;
        this.parent = Optional.empty();
    }

    public CharacterClass(Icon icon, Optional<CharacterClass> parent) {
        this.icon   = icon;
        this.parent = parent;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent(getRegistryName().getNamespace() + "." + getRegistryName().getPath() + ".name");
    }

    public Icon getIcon() {
        return icon;
    }

    public Optional<CharacterClass> getParent() {
        return parent;
    }
}
