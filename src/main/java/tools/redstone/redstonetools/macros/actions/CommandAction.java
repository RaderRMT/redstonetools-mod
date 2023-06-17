package tools.redstone.redstonetools.macros.actions;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

//#if MC>=11903
import net.minecraft.text.Text;
//#endif

public class CommandAction extends Action {

    public String command;

    public CommandAction(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        assert player != null;

        //#if MC>=11903
        player.networkHandler.sendCommand(command.startsWith("/") ? command.substring(1) : command);
        //#elseif MC>=11900
        //$$ player.sendCommand(command.startsWith("/") ? command.substring(1) : command);
        //#else
        //$$ player.sendChatMessage(command.startsWith("/") ? command : "/" + command);
        //#endif
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CommandAction commandAction) {
            return command.equals(commandAction.command);
        }

        return super.equals(obj);
    }
}
