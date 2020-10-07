package de.blutmondgilde.blutmondrpg.gui.elements.templates;


import com.feed_the_beast.mods.ftbguilibrary.icon.Color4I;
import com.feed_the_beast.mods.ftbguilibrary.icon.Icon;
import com.feed_the_beast.mods.ftbguilibrary.widget.Button;
import com.feed_the_beast.mods.ftbguilibrary.widget.GuiHelper;
import com.feed_the_beast.mods.ftbguilibrary.widget.Panel;
import com.feed_the_beast.mods.ftbguilibrary.widget.Theme;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.text.ITextComponent;
import java.util.Optional;

public abstract class ButtonText extends Button {
    private final boolean hasIcon;

    public ButtonText(final Panel panel, final ITextComponent t, Optional<Icon> icon) {
        super(panel, t, icon.orElse(Icon.EMPTY));
        setWidth(150);
        setHeight(20);

        hasIcon = icon.isPresent();
    }

    @Override
    public void draw(MatrixStack matrixStack, Theme theme, int x, int y, int w, int h) {
        super.draw(matrixStack, theme, x, y, w, h);
        theme.drawString(matrixStack, getTitle().getString(), x + 21, y + h / 2 - Minecraft.getInstance().fontRenderer.FONT_HEIGHT / 2);
    }

    @Override
    public void drawIcon(MatrixStack matrixStack, Theme theme, int x, int y, int w, int h) {
        super.drawIcon(matrixStack, theme, x - width / 2 + 10, y, w, h);
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
