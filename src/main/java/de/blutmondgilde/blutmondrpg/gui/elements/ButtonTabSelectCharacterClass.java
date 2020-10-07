package de.blutmondgilde.blutmondrpg.gui.elements;

import com.feed_the_beast.mods.ftbguilibrary.FTBGUILibrary;
import com.feed_the_beast.mods.ftbguilibrary.icon.Icon;
import com.feed_the_beast.mods.ftbguilibrary.utils.MouseButton;
import com.feed_the_beast.mods.ftbguilibrary.widget.Panel;
import de.blutmondgilde.blutmondrpg.gui.SkillTreeScreen;
import de.blutmondgilde.blutmondrpg.gui.elements.templates.ButtonTab;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class ButtonTabSelectCharacterClass extends ButtonTab {
    private final SkillTreeScreen skillTreeScreen;

    public ButtonTabSelectCharacterClass(Panel panel) {
        super(panel,
              new TranslationTextComponent("blutmondrpg.gui.selectcharacterclass"),
              Icon.getIcon(new ResourceLocation(FTBGUILibrary.MOD_ID, "textures/icons/player.png")));
        skillTreeScreen = (SkillTreeScreen) panel.parent.getGui();
    }

    @Override
    public void onClicked(MouseButton mouseButton) {

    }
}
