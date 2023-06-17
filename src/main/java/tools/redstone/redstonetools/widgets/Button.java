package tools.redstone.redstonetools.widgets;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Button extends ButtonWidget {

    protected Button(
            int x, int y,
            int width, int height,
            Text message,
            PressAction onPress
    ) {
        super(
                x, y,
                width, height,
                message,
                onPress
                //#if MC>=11903
                , DEFAULT_NARRATION_SUPPLIER
                //#endif
        );
    }

    protected Button(Button button) {
        this(
                button.getX(), button.getY(),
                button.getWidth(), button.getHeight(),
                button.getMessage(),
                button.onPress
        );
    }

    public String getText() {
        return getMessage().getString();
    }

    public void setText(String text) {
        setText(Text.of(text));
    }

    public void setText(Text text) {
        setMessage(text);
    }

    public void setText(String text, Formatting... formatting) {
        setMessage(Text.of(text).copy().formatted(formatting));
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

    public static ButtonBuilder create(String text, PressAction onPress) {
        return create(Text.of(text), onPress);
    }

    public static ButtonBuilder create(Text text, PressAction onPress) {
        return new ButtonBuilder(text, onPress);
    }

    public static class ButtonBuilder {

        protected final Text text;
        protected final PressAction onPress;

        protected int x = 0;
        protected int y = 0;
        protected int width = 150;
        protected int height = 20;

        protected ButtonBuilder(Text text, PressAction onPress) {
            this.text = text;
            this.onPress = onPress;
        }

        public ButtonBuilder position(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public ButtonBuilder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public ButtonBuilder dimensions(int x, int y, int width, int height) {
            this.position(x, y);
            this.size(width, height);
            return this;
        }

        public Button build() {
            return new Button(
                    this.x, this.y,
                    this.width, this.height,
                    this.text,
                    this.onPress
            );
        }
    }
}
