package tools.redstone.redstonetools.macros.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;

//#if MC>=11901
import net.minecraft.client.gui.screen.ChatInputSuggestor;
//#else
//$$ import net.minecraft.client.gui.screen.CommandSuggestor;
//#endif

import java.util.HashMap;

//#if MC>=11901
public class MaroCommandSuggestor extends ChatInputSuggestor implements Drawable {
//#else
//$$ public class MaroCommandSuggestor extends CommandSuggestor implements Drawable {
//#endif

    private static final HashMap<MaroCommandSuggestor,Integer> yMap =  new HashMap<>();

    public MaroCommandSuggestor(MinecraftClient client, Screen owner, TextFieldWidget textField, TextRenderer textRenderer, boolean slashOptional, boolean suggestingWhenEmpty, int y, int maxSuggestionSize, int color) {
        super(client, owner, textField, textRenderer, slashOptional, suggestingWhenEmpty, 0, maxSuggestionSize, false, color);

        yMap.put(this, y);
    }

    public void close() {
        yMap.remove(this);
    }

    public static boolean instance(Object object) {
        return object instanceof MaroCommandSuggestor;
    }

    public static int getY(Object object) {
        return yMap.get(object);
    }

    @Override
    public void refresh() {
        if (MinecraftClient.getInstance().player == null) {
            return;
        }

        super.refresh();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY);
    }
}
