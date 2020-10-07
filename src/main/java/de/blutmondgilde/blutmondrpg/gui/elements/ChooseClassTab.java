package de.blutmondgilde.blutmondrpg.gui.elements;

import com.feed_the_beast.mods.ftbguilibrary.widget.Panel;
import com.feed_the_beast.mods.ftbguilibrary.widget.Theme;
import com.mojang.blaze3d.matrix.MatrixStack;
import de.blutmondgilde.blutmondrpg.characterclass.CharacterClass;
import de.blutmondgilde.blutmondrpg.characterclass.CharacterClasses;
import de.blutmondgilde.blutmondrpg.gui.SkillTreeScreen;
import de.blutmondgilde.blutmondrpg.gui.elements.templates.ButtonCharacterClass;
import de.blutmondgilde.blutmondrpg.gui.elements.templates.TabElement;
import de.blutmondgilde.blutmondrpg.gui.widgetlayouts.FillVertical;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ChooseClassTab extends TabElement {
    private final SkillTreeScreen skillTreeScreen;

    public ChooseClassTab(Panel screen) {
        super(screen);
        this.skillTreeScreen = (SkillTreeScreen) screen.getGui();
    }

    @Override
    public void addWidgets() {
        GameRegistry.findRegistry(CharacterClass.class)// finds registry
                .getEntries() // get all registered classes
                .stream() // convert map into stream
                .filter(entry -> !entry.getValue().getParent().isPresent()) // remove classes which require parents
                .filter(entry -> !entry.getValue().equals(CharacterClasses.DEFAULT_CLASS.get()))
                .forEach(entry -> add(new ButtonCharacterClass(this, entry.getValue(), entry.getValue().getIcon()))); // add button for class
    }

    @Override
    public void alignWidgets() {
        align(new FillVertical(5, 8, 10, 10, 10));
    }

    @Override
    public void draw(MatrixStack matrixStack, Theme theme, int x, int y, int w, int h) {
        super.draw(matrixStack, theme, x, y, w, h);
        alignWidgets();
    }
}
