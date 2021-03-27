package de.blutmondgilde.blutmondrpg.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.TranslationTextComponent;
import se.mickelus.mgui.gui.GuiRoot;

public class ChooseRaceGui extends Screen {
    private final GuiRoot root;

    public ChooseRaceGui() {
        super(new TranslationTextComponent("blutmondrpg.gui.chooseracegui.title"));
        this.root = new GuiRoot(Minecraft.getInstance());

    }
}
