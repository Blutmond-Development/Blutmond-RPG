package de.blutmondgilde.blutmondrpg.gui.elements.templates;

import com.feed_the_beast.mods.ftbguilibrary.icon.Color4I;
import com.feed_the_beast.mods.ftbguilibrary.icon.Icon;
import com.feed_the_beast.mods.ftbguilibrary.widget.Button;
import com.feed_the_beast.mods.ftbguilibrary.widget.GuiHelper;
import com.feed_the_beast.mods.ftbguilibrary.widget.Panel;
import com.feed_the_beast.mods.ftbguilibrary.widget.Theme;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import de.blutmondgilde.blutmondrpg.gui.SkillTreeScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.text.ITextComponent;

public abstract class ButtonTab extends Button {
    public final SkillTreeScreen treeGui;

    public ButtonTab(Panel panel, ITextComponent title, Icon icon) {
        super(panel, title, icon);
        treeGui = (SkillTreeScreen) panel.getGui();
        setSize(20, 18);
    }

    @Override
    public void drawBackground(MatrixStack matrixStack, Theme theme, int x, int y, int w, int h) {
        GuiHelper.drawHollowRect(x, y, w, h, Color4I.rgba(33, 33, 33, 255), false);

        if (isMouseOver) {
            RenderSystem.disableTexture();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();

            buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);

            GuiHelper.addRectToBuffer(buffer, x + 1, y + 1, w - 2, h - 2, Color4I.rgba(110, 110, 110, 130));

            tessellator.draw();
            RenderSystem.enableTexture();
        }
    }
}
