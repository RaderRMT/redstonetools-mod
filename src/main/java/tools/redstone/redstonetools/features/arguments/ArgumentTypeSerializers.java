package tools.redstone.redstonetools.features.arguments;

import net.minecraft.command.argument.ArgumentTypes;
import net.minecraft.command.argument.serialize.ArgumentSerializer;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import tools.redstone.redstonetools.features.arguments.serializers.*;

//#if MC>=11903
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
//#else
//$$ import net.minecraft.util.registry.Registry;
//#endif

public class ArgumentTypeSerializers {

    public static void register() {
        //#if MC>=11903
        Registry<ArgumentSerializer<?, ?>> commandArgumentType = Registries.COMMAND_ARGUMENT_TYPE;
        //#else
        //$$ Registry<ArgumentSerializer<?, ?>> commandArgumentType = Registry.COMMAND_ARGUMENT_TYPE;
        //#endif

        ArgumentTypes.register(commandArgumentType, "redstonetools:bigint", BigIntegerSerializer.class, ConstantArgumentSerializer.of(() -> BigIntegerSerializer.bigInteger()));
        ArgumentTypes.register(commandArgumentType, "redstonetools:blockcolor", BlockColorSerializer.class, ConstantArgumentSerializer.of(BlockColorSerializer::blockColor));
        ArgumentTypes.register(commandArgumentType, "redstonetools:blockstate", BlockStateArgumentSerializer.class, ConstantArgumentSerializer.of(BlockStateArgumentSerializer::blockState));
        ArgumentTypes.register(commandArgumentType, "redstonetools:bool", BoolSerializer.class, ConstantArgumentSerializer.of(BoolSerializer::bool));
        ArgumentTypes.register(commandArgumentType, "redstonetools:coloredbock", ColoredBlockTypeSerializer.class, ConstantArgumentSerializer.of(ColoredBlockTypeSerializer::coloredBlockType));
        ArgumentTypes.register(commandArgumentType, "redstonetools:direction", DirectionSerializer.class, ConstantArgumentSerializer.of(DirectionSerializer::direction));
        ArgumentTypes.register(commandArgumentType, "redstonetools:double", DoubleSerializer.class, ConstantArgumentSerializer.of(() -> DoubleSerializer.doubleArg()));
        ArgumentTypes.register(commandArgumentType, "redstonetools:float", FloatSerializer.class, ConstantArgumentSerializer.of(() -> FloatSerializer.floatArg()));
        ArgumentTypes.register(commandArgumentType, "redstonetools:int", IntegerSerializer.class, ConstantArgumentSerializer.of(() -> IntegerSerializer.integer()));
        ArgumentTypes.register(commandArgumentType, "redstonetools:long", LongSerializer.class, ConstantArgumentSerializer.of(() -> LongSerializer.longArg()));
        ArgumentTypes.register(commandArgumentType, "redstonetools:macroname", MacroNameSerializer.class, ConstantArgumentSerializer.of(MacroNameSerializer::macroName));
        ArgumentTypes.register(commandArgumentType, "redstonetools:numberbase", NumberBaseSerializer.class, ConstantArgumentSerializer.of(NumberBaseSerializer::numberBase));
        ArgumentTypes.register(commandArgumentType, "redstonetools:signalblock", SignalBlockSerializer.class, ConstantArgumentSerializer.of(SignalBlockSerializer::signalBlock));
        ArgumentTypes.register(commandArgumentType, "redstonetools:string", StringSerializer.class, ConstantArgumentSerializer.of(StringSerializer::string));
    }
}
