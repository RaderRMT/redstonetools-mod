package tools.redstone.redstonetools.features.feedback;

import net.minecraft.server.command.ServerCommandSource;

//#if MC>=11900
import net.minecraft.text.Text;
//#else
//$$ import net.minecraft.text.LiteralText;
//#endif

import javax.inject.Singleton;

@Singleton
public class FeedbackSender extends AbstractFeedbackSender {
    @Override
    public void sendFeedback(ServerCommandSource source, Feedback feedback) {
        if (feedback.getType() == FeedbackType.NONE) {
            return;
        }

        //#if MC>=11900
        source.sendFeedback(Text.literal(feedback.getMessage())
                //#else
                //$$ source.sendFeedback(new LiteralText(feedback.getMessage())
                //#endif
                .formatted(feedback.getFormatting()), false);
    }
}
