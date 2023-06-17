package tools.redstone.redstonetools.mixin.macros;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tools.redstone.redstonetools.macros.gui.screen.MacroSelectScreen;
import tools.redstone.redstonetools.widgets.Button;

@Mixin(ControlsOptionsScreen.class)
public abstract class AddMacroButtonMixin extends GameOptionsScreen {

    public AddMacroButtonMixin(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    @Inject(
            method = "init",
            at = @At("TAIL")
    )
    public void init(CallbackInfo ci) {
        ButtonWidget macroButton = Button.create("Macros...", this::openMacroSelectScreen)
                //#if MC>=11903
                .position(this.width / 2 - 155, this.height / 6 + 60)
                //#else
                //$$ .position(this.width / 2 + 5, this.height / 6 + 36)
                //#endif
                .size(150, 20)
                .build();

        this.addDrawableChild(macroButton);
    }

    //#if MC>=11903
    @ModifyArg(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/option/ControlsOptionsScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;",
                    ordinal = 6
            )
    )
    public Element moveDoneButton(Element element) {
        ButtonWidget button = (ButtonWidget) element;
        button.setY(button.getY() + 24);

        return button;
    }
    //#endif

    private void openMacroSelectScreen(ButtonWidget button) {
        this.client.setScreen(new MacroSelectScreen(this,super.gameOptions,Text.of("Macros")));
    }
}
