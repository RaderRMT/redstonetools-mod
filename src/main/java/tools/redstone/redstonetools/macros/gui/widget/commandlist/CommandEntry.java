package tools.redstone.redstonetools.macros.gui.widget.commandlist;

import tools.redstone.redstonetools.abstraction.widgets.Button;
import tools.redstone.redstonetools.abstraction.widgets.TextField;
import tools.redstone.redstonetools.macros.gui.MaroCommandSuggestor;
import tools.redstone.redstonetools.macros.gui.widget.IconButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.util.math.MatrixStack;

public class CommandEntry extends EntryListWidget.Entry<CommandEntry> {

    protected CommandListWidget owner;

    public final TextField command;
    protected final Button deleteButton;

    public CommandEntry(MinecraftClient client, CommandListWidget owner, String text) {
        this.owner = owner;

        this.command = new TextField(0, 0, 300, 20, "");
        this.command.setMaxLength(255);
        this.command.setText(text);

        deleteButton = IconButton.create(IconButton.CROSS_ICON, (button) -> this.owner.removeCommand(this))
                .size(20, 20)
                .build();

        MaroCommandSuggestor commandMaroCommandSuggestor = new MaroCommandSuggestor(client, owner.getParent(), command, client.textRenderer, true, false, 0, 0, 0);
        commandMaroCommandSuggestor.setWindowActive(false);
        commandMaroCommandSuggestor.refresh();
        commandMaroCommandSuggestor.close();
    }

    @Override
    public void render(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
        command.setX(owner.getParent().width / 2 - owner.getWidth() / 2 + 5);
        command.setY(y);
        command.render(matrices, mouseX, mouseY, tickDelta);

        deleteButton.setX(command.getX() + command.getWidth() + 5);
        deleteButton.setY(y);
        deleteButton.render(matrices, mouseX, mouseY, tickDelta);

        if (edit) {
            edit = false;
            owner.getParent().editCommandField(command);
        }
    }

    public void tick() {
        command.tick();
    }
    private boolean edit = false;

    public void setFocused(boolean focused) {
        command.setFocused(focused);

        if (focused) {
            owner.centerScrollOn(this);
            edit = true;
        }

        //#if MC<=11903
        //$$ owner.focusOn(this);
        //#endif
    }

    protected String getText() {
        return command.getText();
    }

    public void setOwner(CommandListWidget owner) {
        this.owner = owner;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (command.mouseClicked(mouseX, mouseY, button)) {
            owner.centerScrollOn(this);
            edit = true;
            return true;
        }

        deleteButton.mouseClicked(mouseX, mouseY, button);

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (command.isFocused()) {
            return command.charTyped(chr,modifiers);
        }

        return super.charTyped(chr, modifiers);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (command.isFocused()) {
            return command.keyPressed(keyCode, scanCode, modifiers);
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        if (command.isFocused()) {
            return command.keyReleased(keyCode, scanCode, modifiers);
        }

        return super.keyReleased(keyCode, scanCode, modifiers);
    }
}
