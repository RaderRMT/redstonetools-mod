package tools.redstone.redstonetools.features.commands;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import tools.redstone.redstonetools.features.AbstractFeature;
import tools.redstone.redstonetools.features.Feature;
import tools.redstone.redstonetools.features.arguments.Argument;
import tools.redstone.redstonetools.features.feedback.AbstractFeedbackSender;
import tools.redstone.redstonetools.features.feedback.Feedback;
import tools.redstone.redstonetools.utils.CommandUtils;
import tools.redstone.redstonetools.utils.ReflectionUtils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;

//#if MC>=11900
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
//#endif

import java.util.ArrayList;
import java.util.List;

import static tools.redstone.redstonetools.RedstoneToolsClient.INJECTOR;

public abstract class CommandFeature extends AbstractFeature {

    private static final List<KeyBinding> keyBindings = new ArrayList<>();

    @Override
    public void register() {
        super.register();

        boolean containsRequiredArguments = ReflectionUtils.getArguments(getClass())
                .stream()
                .anyMatch(a -> !a.isOptional());

        if (containsRequiredArguments) {
            return;
        }

        Feature info = ReflectionUtils.getFeatureInfo(getClass());
        KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                info.name(),
                InputUtil.Type.KEYSYM,
                -1,
                "Redstone Tools"
        ));

        keyBindings.add(keyBinding);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                assert client.player != null;
                //#if MC>=11900
                client.player.sendCommand(info.command());
                //#else
                //$$ client.player.sendChatMessage("/" + info.command());
                //#endif
            }
        });
    }

    @Override
    //#if MC>=11900
    protected void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        //#else
        //$$ protected void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        //#endif
        Feature info = ReflectionUtils.getFeatureInfo(getClass());
        List<Argument<?>> arguments = ReflectionUtils.getArguments(getClass());

        CommandUtils.register(
                info.command(),
                arguments,
                context -> {
                    for (Argument<?> argument : arguments) {
                        argument.setValue(context);
                    }

                    Feedback feedback = execute(context.getSource());

                    INJECTOR.getInstance(AbstractFeedbackSender.class)
                            .sendFeedback(context.getSource(), feedback);

                    return feedback.getType().getCode();
                },
                dispatcher
                //#if MC<11900
                //$$ ,
                //$$ dedicated
                //#endif
        );
    }

    protected abstract Feedback execute(ServerCommandSource source) throws CommandSyntaxException;
}
