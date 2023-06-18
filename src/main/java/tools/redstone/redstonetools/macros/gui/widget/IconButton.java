package tools.redstone.redstonetools.macros.gui.widget;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import tools.redstone.redstonetools.abstraction.widgets.Button;

public class IconButton extends Button {

    public static Identifier CROSS_ICON = new Identifier("redstonetools","gui/cross.png");
    public static Identifier PENCIL_ICON = new Identifier("redstonetools","gui/pencil.png");

    private final Identifier texture;

    private IconButton(Identifier texture, Button button) {
        super(button);

        this.texture = texture;
    }

    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.renderButton(matrices, mouseX, mouseY, delta);

        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.enableDepthTest();

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);

        drawTexture(matrices, getX(), getY(), 0, 0, 20, this.height, 20, 20);
    }

    public static ButtonBuilder create(Identifier texture, PressAction onPress) {
        return new IconButtonBuilder(texture, onPress);
    }

    public static class IconButtonBuilder extends ButtonBuilder {

        private final Identifier texture;

        protected IconButtonBuilder(Identifier texture, PressAction onPress) {
            super(Text.of(""), onPress);

            this.texture = texture;
        }

        @Override
        public Button build() {
            Button button = super.build();

            return new IconButton(this.texture, button);
        }
    }
}
