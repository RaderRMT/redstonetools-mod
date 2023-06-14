package tools.redstone.redstonetools.features;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

//#if MC>=11900
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
//#else
//$$ import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
//#endif

public abstract class AbstractFeature {
    private final Feature feature;

    {
        feature = getClass().getAnnotation(Feature.class);

        if (feature == null) {
            throw new IllegalStateException("Feature " + getClass() + " is not annotated with @Feature");
        }
    }

    public String getName() {
        return feature.name();
    }

    public String getDescription() {
        return feature.description();
    }

    public String getCommand() {
        return feature.command();
    }

    /**
     * Register this feature.
     */
    public void register() {
        CommandRegistrationCallback.EVENT.register(this::registerCommands);
    }

    //#if MC>=11900
    protected abstract void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment);
    //#else
    //$$ protected abstract void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated);
    //#endif
}
