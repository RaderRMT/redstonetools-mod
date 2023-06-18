package tools.redstone.redstonetools.abstraction.widgets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class TextField extends TextFieldWidget {

    public TextField(int x, int y, int width, int height, String text) {
        this(x, y, width, height, Text.of(text));
    }

    public TextField(int x, int y, int width, int height, Text text) {
        super(
                MinecraftClient.getInstance().textRenderer,
                x, y,
                width, height,
                text
        );
    }

    public int getX() {
        //#if MC>=11903
        return super.getX();
        //#else
        //$$ return this.x;
        //#endif
    }

    public void setX(int x) {
        //#if MC>=11903
        super.setX(x);
        //#else
        //$$ this.x = x;
        //#endif
    }

    public int getY() {
        //#if MC>=11903
        return super.getY();
        //#else
        //$$ return this.y;
        //#endif
    }

    public void setY(int y) {
        //#if MC>=11903
        super.setY(y);
        //#else
        //$$ this.y = y;
        //#endif
    }

    public void setFocused(boolean focused) {
        super.setFocused(focused);
    }
}
