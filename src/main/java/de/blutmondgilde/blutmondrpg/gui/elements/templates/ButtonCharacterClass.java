package de.blutmondgilde.blutmondrpg.gui.elements.templates;

import com.feed_the_beast.mods.ftbguilibrary.icon.Icon;
import com.feed_the_beast.mods.ftbguilibrary.utils.MouseButton;
import com.feed_the_beast.mods.ftbguilibrary.widget.Panel;
import de.blutmondgilde.blutmondrpg.characterclass.CharacterClass;
import de.blutmondgilde.blutmondrpg.gui.SkillTreeScreen;
import net.minecraft.client.Minecraft;
import java.util.Optional;

public class ButtonCharacterClass extends ButtonText {
    private final CharacterClass characterClass;
    private final SkillTreeScreen skillTreeScreen;

    public ButtonCharacterClass(final Panel panel, final CharacterClass characterClass, final Icon classIcon) {
        super(panel, characterClass.getDisplayName(), Optional.ofNullable(classIcon));
        this.characterClass  = characterClass;
        this.skillTreeScreen = (SkillTreeScreen) panel.getGui();
    }

    @Override
    public void onClicked(MouseButton mouseButton) {
        Minecraft.getInstance().player.sendChatMessage("/brpg class set " + '"' + this.characterClass.getRegistryName() + '"');
        skillTreeScreen.refreshWidgets();
    }
}
