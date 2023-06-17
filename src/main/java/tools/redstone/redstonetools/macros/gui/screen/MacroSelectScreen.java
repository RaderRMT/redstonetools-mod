package tools.redstone.redstonetools.macros.gui.screen;

import tools.redstone.redstonetools.macros.gui.widget.macrolist.MacroEntry;
import tools.redstone.redstonetools.macros.gui.widget.macrolist.MacroListWidget;
import tools.redstone.redstonetools.widgets.Button;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

//#if MC>=11900
import net.minecraft.screen.ScreenTexts;
//#else
//$$ import net.minecraft.client.gui.screen.ScreenTexts;
//#endif

public class MacroSelectScreen extends GameOptionsScreen {


    private MacroListWidget macroList;

    public MacroSelectScreen(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    @Override
    public void init() {
        super.init();

        this.macroList = new MacroListWidget(this, client);
        this.addSelectableChild(this.macroList);

        ButtonWidget newMacroButton = Button.create("Create New...", (button) -> this.client.setScreen(new MacroEditScreen(this, gameOptions, Text.of("New Macro"), macroList)))
                .position(this.width / 2 + 1, this.height - 29)
                .size(150, 20)
                .build();

        ButtonWidget doneButton = Button.create(ScreenTexts.DONE, (button) -> this.client.setScreen(this.parent))
                .position(this.width / 2 - 151, this.height - 29)
                .size(150, 20)
                .build();

        addDrawableChild(newMacroButton);
        addDrawableChild(doneButton);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        //#if MC>=11904
        this.renderBackgroundTexture(matrices);
        //#else
        //$$ this.renderBackgroundTexture(0);
        //#endif

        macroList.render(matrices, mouseX, mouseY, delta);

        //#if MC>=11904
        drawCenteredTextWithShadow(matrices, this.textRenderer, this.title, this.width / 2, 8, 16777215);
        //#else
        //$$ drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 8, 16777215);
        //#endif

        super.render(matrices, mouseX, mouseY, delta);
    }

    public void openEditScreen(MacroEntry entry) {
        client.setScreen(new MacroEditScreen(this, gameOptions, Text.of("Edit Macro"), macroList, entry.macro));
    }

}
