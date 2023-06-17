package tools.redstone.redstonetools.mixin.features.itembind;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tools.redstone.redstonetools.features.commands.ItemBindFeature;
import tools.redstone.redstonetools.features.feedback.Feedback;
import tools.redstone.redstonetools.features.feedback.FeedbackSender;

import static tools.redstone.redstonetools.RedstoneToolsClient.INJECTOR;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {

    @Inject(
            method = "sendCommand(Ljava/lang/String;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    public void injectCommand(String message, CallbackInfoReturnable<Boolean> cir) {
        if (!ItemBindFeature.waitingForCommand) {
            return;
        }

        Feedback addCommandFeedback = ItemBindFeature.addCommand(message);
        if (addCommandFeedback != null) {
            INJECTOR.getInstance(FeedbackSender.class).sendFeedback(((Entity) (Object) this).getCommandSource(), addCommandFeedback);
            cir.setReturnValue(false);
        }
    }
}
