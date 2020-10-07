package de.blutmondgilde.blutmondrpg.gui.elements.templates;

import com.feed_the_beast.mods.ftbguilibrary.widget.Panel;
import com.feed_the_beast.mods.ftbguilibrary.widget.Theme;
import com.mojang.blaze3d.matrix.MatrixStack;
import de.blutmondgilde.blutmondrpg.gui.SkillTreeScreen;

public abstract class TabElement extends Panel {
    private final SkillTreeScreen skillTreeScreen;

    public TabElement(Panel screen) {
        super(screen);
        this.skillTreeScreen = (SkillTreeScreen) screen.getGui();
        setPosAndSize(20, 1, skillTreeScreen.width - 21, skillTreeScreen.height - 2);
    }

    @Override
    public void drawBackground(MatrixStack matrixStack, Theme theme, int x, int y, int w, int h) {
        super.drawBackground(matrixStack, theme, x, y, w, h);
        setPosAndSize(20, 1, skillTreeScreen.width - 21, skillTreeScreen.height - 2);
    }
}
