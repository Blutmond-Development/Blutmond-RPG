package de.blutmondgilde.blutmondrpg.gui;

import com.feed_the_beast.mods.ftbguilibrary.icon.Color4I;
import com.feed_the_beast.mods.ftbguilibrary.widget.GuiHelper;
import com.feed_the_beast.mods.ftbguilibrary.widget.Panel;
import com.feed_the_beast.mods.ftbguilibrary.widget.Theme;
import com.feed_the_beast.mods.ftbguilibrary.widget.WidgetLayout;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import de.blutmondgilde.blutmondrpg.gui.elements.ButtonTabSelectCharacterClass;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class SidePanel extends Panel {
    protected final SkillTreeScreen skillTreeScreen;

    public SidePanel(Panel panel) {
        super(panel);
        skillTreeScreen = (SkillTreeScreen) panel.getGui();
        setPosAndSize(0, 0, 20, skillTreeScreen.height);
    }

    @Override
    public void addWidgets() {
        if (!skillTreeScreen.hasClassSelected) {
            add(new ButtonTabSelectCharacterClass(this));
        }
    }

    @Override
    public void alignWidgets() {
        setHeight(skillTreeScreen.height - 2);
        align(WidgetLayout.VERTICAL);
    }

    @Override
    public void drawBackground(MatrixStack matrixStack, Theme theme, int x, int y, int w, int h) {
        setPosAndSize(0, 0, 20, skillTreeScreen.height);
        GuiHelper.drawHollowRect(x, y, w, h, Color4I.DARK_GRAY, false);

        RenderSystem.disableTexture();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);

        GuiHelper.addRectToBuffer(buffer, x + 1, y + 1, w - 1, h - 1, Color4I.rgba(33, 33, 33, 150));

        tessellator.draw();
        RenderSystem.enableTexture();


    }
}
