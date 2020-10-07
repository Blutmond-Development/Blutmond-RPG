package de.blutmondgilde.blutmondrpg.gui;

import com.feed_the_beast.mods.ftbguilibrary.icon.Color4I;
import com.feed_the_beast.mods.ftbguilibrary.widget.GuiBase;
import com.feed_the_beast.mods.ftbguilibrary.widget.GuiHelper;
import com.feed_the_beast.mods.ftbguilibrary.widget.Theme;
import com.mojang.blaze3d.matrix.MatrixStack;
import de.blutmondgilde.blutmondrpg.capabilities.characterclass.CharacterClassCapabilityProvider;
import de.blutmondgilde.blutmondrpg.capabilities.characterclass.ICharacterClassCapability;
import de.blutmondgilde.blutmondrpg.characterclass.CharacterClasses;
import de.blutmondgilde.blutmondrpg.gui.elements.ChooseClassTab;
import de.blutmondgilde.blutmondrpg.gui.elements.templates.TabElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;

public class SkillTreeScreen extends GuiBase {
    public boolean hasClassSelected;
    public final SidePanel sidePanel;
    public TabElement currentTab;
    private ICharacterClassCapability characterClass;


    public SkillTreeScreen() {
        final ClientPlayerEntity playerEntity = Minecraft.getInstance().player;
        this.characterClass = playerEntity
                .getCapability(CharacterClassCapabilityProvider.CAPABILITY)
                .orElseThrow(() -> new IllegalStateException("Capability should always be attached"));

        hasClassSelected = !this.characterClass.getClassType().toString().equals(CharacterClasses.DEFAULT_CLASS.getId().toString());

        sidePanel  = new SidePanel(this);
        currentTab = new ChooseClassTab(this);

    }

    @Override
    public void clearWidgets() {
        hasClassSelected = !this.characterClass.getClassType().toString().equals(CharacterClasses.DEFAULT_CLASS.getId().toString());
        super.clearWidgets();
    }

    @Override
    public void addWidgets() {
        add(sidePanel);
        add(currentTab);
    }

    @Override
    public boolean drawDefaultBackground(MatrixStack matrixStack) {
        return false;
    }

    @Override
    public void drawBackground(MatrixStack matrixStack, Theme theme, int x, int y, int w, int h) {
        setPosAndSize(0, 0, this.getScreen().getScaledWidth(), this.getScreen().getScaledHeight());

        for (int yPos = 0; yPos < this.height; yPos += 128) {
            for (int xPos = 0; xPos < this.width; xPos += 128) {
                Theme.BACKGROUND_SQUARES.withColor(Color4I.rgba(255, 255, 255, 150)).draw(xPos, yPos, 128, 128);
            }
        }

        GuiHelper.drawHollowRect(x, y, this.width, this.height, Color4I.DARK_GRAY, false);
    }
}
