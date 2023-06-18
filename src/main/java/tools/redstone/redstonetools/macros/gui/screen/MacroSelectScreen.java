package tools.redstone.redstonetools.macros.gui.screen;

import tools.redstone.redstonetools.abstraction.GuiScreen;
import tools.redstone.redstonetools.macros.gui.widget.macrolist.MacroEntry;
import tools.redstone.redstonetools.macros.gui.widget.macrolist.MacroListWidget;
import tools.redstone.redstonetools.abstraction.widgets.Button;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

//#if MC>=11900
import net.minecraft.screen.ScreenTexts;
//#else
//$$ import net.minecraft.client.gui.screen.ScreenTexts;
//#endif

public class MacroSelectScreen extends GuiScreen {

    private MacroListWidget macroList;

    public MacroSelectScreen(Screen parent, String title) {
        super(parent, title);
    }

    @Override
    public void init() {
        this.macroList = new MacroListWidget(this, client);

        ButtonWidget newMacroButton = Button.create("Create New...", (button) -> this.client.setScreen(new MacroEditScreen(this, Text.of("New Macro"), macroList)))
                .position(this.width / 2 + 1, this.height - 29)
                .size(150, 20)
                .build();

        ButtonWidget doneButton = Button.create(ScreenTexts.DONE, (button) -> close())
                .position(this.width / 2 - 151, this.height - 29)
                .size(150, 20)
                .build();

        addSelectableChild(this.macroList);
        addDrawableChild(newMacroButton);
        addDrawableChild(doneButton);
    }

    @Override
    protected void render(int mouseX, int mouseY, float delta) {
        renderBackground();

        renderDrawable(this.macroList);

        drawCenteredText(this.title, this.width / 2, 8, 16777215);
    }

    public void openEditScreen(MacroEntry entry) {
        this.client.setScreen(new MacroEditScreen(this, Text.of("Edit Macro"), this.macroList, entry.macro));
    }
}
