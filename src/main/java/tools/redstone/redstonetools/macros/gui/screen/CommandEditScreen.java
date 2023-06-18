package tools.redstone.redstonetools.macros.gui.screen;

import tools.redstone.redstonetools.abstraction.GuiScreen;
import tools.redstone.redstonetools.abstraction.widgets.TextField;
import tools.redstone.redstonetools.macros.gui.MaroCommandSuggestor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.InputUtil;

public class CommandEditScreen extends GuiScreen {

    private final TextField commandField;
    private final MaroCommandSuggestor commandMaroCommandSuggestor;
    private boolean changed = false;

    public CommandEditScreen(Screen parent, TextField commandField) {
        super(parent, "");

        this.commandField = commandField;
        this.client = MinecraftClient.getInstance();

        int commandFieldY = commandField.getY();

        this.commandMaroCommandSuggestor = new MaroCommandSuggestor(this.client, parent, commandField, this.client.textRenderer, true, false, commandFieldY - 20, 5, -805306368);

        commandField.setChangedListener((s) -> this.changed = true);
        this.commandMaroCommandSuggestor.setWindowActive(true);
        this.commandMaroCommandSuggestor.refresh();
    }

    @Override
    protected void render(int mouseX, int mouseY, float delta) {
        renderDrawable(getParent());

        renderGradientOverScreen(-1072689136, -804253680);

        renderDrawable(this.commandField);
        renderDrawable(this.commandMaroCommandSuggestor);

        if (this.changed) {
            this.commandMaroCommandSuggestor.refresh();
            this.changed = false;
        }
    }

    @Override
    public void tick() {
        super.tick();
        commandField.tick();
    }

    @Override
    public void resize(MinecraftClient client, int width, int height) {
        getParent().resize(client, width, height);
    }

    @Override
    public void close() {
        this.commandField.setFocused(false);
        this.commandField.setChangedListener(null);

        this.commandMaroCommandSuggestor.setWindowActive(false);
        this.commandMaroCommandSuggestor.refresh();
        this.commandMaroCommandSuggestor.close();

        super.close();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!commandField.mouseClicked(mouseX, mouseY, button)) {
            if (!commandMaroCommandSuggestor.mouseClicked(mouseX, mouseY, button)) {
                close();
            } else {
                this.commandField.setFocused(true);
            }
            return false;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        return commandMaroCommandSuggestor.mouseScrolled(amount);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        return commandField.charTyped(chr,modifiers);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == InputUtil.GLFW_KEY_ESCAPE || keyCode == InputUtil.GLFW_KEY_ENTER || keyCode == InputUtil.GLFW_KEY_KP_ENTER) {
            close();
            return true;
        }
        commandMaroCommandSuggestor.keyPressed(keyCode, scanCode, modifiers);

        return commandField.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return commandField.keyReleased(keyCode, scanCode, modifiers);
    }
}
