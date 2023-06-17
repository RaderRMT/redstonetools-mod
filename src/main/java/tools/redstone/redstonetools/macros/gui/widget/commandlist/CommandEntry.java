package tools.redstone.redstonetools.macros.gui.widget.commandlist;

import tools.redstone.redstonetools.macros.gui.MaroCommandSuggestor;
import tools.redstone.redstonetools.macros.gui.widget.IconButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class CommandEntry extends EntryListWidget.Entry<CommandEntry> {


    protected CommandListWidget owner;

    public final TextFieldWidget command;
    protected final ButtonWidget deleteButton;


    public CommandEntry(MinecraftClient client, CommandListWidget owner, String text) {
        this.owner = owner;

        command = new TextFieldWidget(client.textRenderer, 0, 0, 300, 20, Text.of(""));
        command.setMaxLength(255);
        command.setText(text);

        deleteButton = IconButton.create(IconButton.CROSS_ICON, (button) -> this.owner.removeCommand(this))
                .size(20, 20)
                .build();

        MaroCommandSuggestor commandMaroCommandSuggestor = new MaroCommandSuggestor(client, owner.getParent(), command,client.textRenderer,true,false, 0,0,0);
        commandMaroCommandSuggestor.setWindowActive(false);
        commandMaroCommandSuggestor.refresh();
        commandMaroCommandSuggestor.close();
    }


    @Override
    public void render(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
        //#if MC>=11903
        command.setX(owner.getParent().width / 2 - owner.getWidth() / 2 + 5);
        command.setY(y);
        //#else
        //$$ command.x = owner.getParent().width/2-owner.getWidth()/2+5;
        //$$ command.y = y;
        //#endif
        command.render(matrices,mouseX,mouseY,tickDelta);

        //#if MC>=11903
        deleteButton.setX(command.getX() + command.getWidth() + 5);
        deleteButton.setY(y);
        //#else
        //$$ deleteButton.x = command.x + command.getWidth()+5;
        //$$ deleteButton.y = y;
        //#endif
        deleteButton.render(matrices,mouseX,mouseY,tickDelta);

        if (edit) {
            edit = false;
            owner.getParent().editCommandField(command);
        }
    }

    public void tick() {
        command.tick();
    }
    private boolean edit = false;

    public void setFocused(boolean focused){
        //#if MC>=11904
        command.setFocused(focused);
        //#else
        //$$ command.setTextFieldFocused(focused);
        //#endif
        if (focused){
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
        if (command.mouseClicked(mouseX,mouseY,button)) {
            owner.centerScrollOn(this);
            edit = true;
            return true;
        }
        deleteButton.mouseClicked(mouseX,mouseY,button);

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (command.isFocused()) return command.charTyped(chr,modifiers);

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
        if (command.isFocused()) return command.keyReleased(keyCode, scanCode, modifiers);

        return super.keyReleased(keyCode, scanCode, modifiers);
    }
}
