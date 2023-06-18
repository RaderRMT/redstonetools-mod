package tools.redstone.redstonetools.abstraction;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public abstract class GuiScreen extends Screen {

    private final Screen parent;

    private MatrixStack matrices;
    private int mouseX;
    private int mouseY;
    private float delta;

    protected GuiScreen(String title) {
        this(null, Text.of(title));
    }

    protected GuiScreen(Text title) {
        this(null, title);
    }

    protected GuiScreen(Screen parent, String title) {
        this(parent, Text.of(title));
    }

    protected GuiScreen(Screen parent, Text title) {
        super(title);

        this.parent = parent;
    }

    protected abstract void render(int mouseX, int mouseY, float delta);

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        set(matrices, mouseX, mouseY, delta);

        render(getMouseX(), getMouseY(), getDelta());
    }

    protected void renderSuper() {
        super.render(getMatrices(), getMouseX(), getMouseY(), getDelta());
    }

    protected void renderDrawable(Drawable drawable) {
        drawable.render(
                getMatrices(),
                getMouseX(),
                getMouseY(),
                getDelta()
        );
    }

    protected void renderBackground() {
        //#if MC>=11904
        this.renderBackgroundTexture(this.matrices);
        //#else
        //$$ this.renderBackgroundTexture(0);
        //#endif
    }

    protected void renderGradientOverScreen(int startColor, int endColor) {
        fillGradient(
                getMatrices(),
                0, 0,
                this.width, this.height,
                startColor, endColor
        );
    }

    protected void drawCenteredText(String text, int centerX, int y, int color) {
        drawCenteredText(
                Text.of(text),
                centerX,
                y,
                color
        );
    }

    protected void drawCenteredText(Text text, int centerX, int y, int color) {
        //#if MC>=11904
        drawCenteredTextWithShadow(this.matrices, this.textRenderer, text, centerX, y, color);
        //#else
        //$$ drawCenteredText(this.matrices, this.textRenderer, text, centerX, y, color);
        //#endif
    }

    protected Screen getParent() {
        return this.parent;
    }

    public MatrixStack getMatrices() {
        return this.matrices;
    }

    public void setMatrices(MatrixStack matrices) {
        this.matrices = matrices;
    }

    public int getMouseX() {
        return this.mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return this.mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public float getDelta() {
        return this.delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }

    private void set(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.matrices = matrices;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.delta = delta;
    }

    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }
}
