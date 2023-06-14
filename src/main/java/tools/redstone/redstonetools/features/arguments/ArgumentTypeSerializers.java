package tools.redstone.redstonetools.features.arguments;

import net.minecraft.command.argument.ArgumentTypes;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.util.registry.Registry;
import tools.redstone.redstonetools.features.arguments.serializers.*;

public class ArgumentTypeSerializers {

    public static void register() {
        ArgumentTypes.register(Registry.COMMAND_ARGUMENT_TYPE, "redstonetools:bigint", BigIntegerSerializer.class, ConstantArgumentSerializer.of(() -> BigIntegerSerializer.bigInteger()));
        ArgumentTypes.register(Registry.COMMAND_ARGUMENT_TYPE, "redstonetools:blockcolor", BlockColorSerializer.class, ConstantArgumentSerializer.of(BlockColorSerializer::blockColor));
        ArgumentTypes.register(Registry.COMMAND_ARGUMENT_TYPE, "redstonetools:blockstate", BlockStateArgumentSerializer.class, ConstantArgumentSerializer.of(BlockStateArgumentSerializer::blockState));
        ArgumentTypes.register(Registry.COMMAND_ARGUMENT_TYPE, "redstonetools:bool", BoolSerializer.class, ConstantArgumentSerializer.of(BoolSerializer::bool));
        ArgumentTypes.register(Registry.COMMAND_ARGUMENT_TYPE, "redstonetools:coloredbock", ColoredBlockTypeSerializer.class, ConstantArgumentSerializer.of(ColoredBlockTypeSerializer::coloredBlockType));
        ArgumentTypes.register(Registry.COMMAND_ARGUMENT_TYPE, "redstonetools:direction", DirectionSerializer.class, ConstantArgumentSerializer.of(DirectionSerializer::direction));
        ArgumentTypes.register(Registry.COMMAND_ARGUMENT_TYPE, "redstonetools:double", DoubleSerializer.class, ConstantArgumentSerializer.of(() -> DoubleSerializer.doubleArg()));
        ArgumentTypes.register(Registry.COMMAND_ARGUMENT_TYPE, "redstonetools:float", FloatSerializer.class, ConstantArgumentSerializer.of(() -> FloatSerializer.floatArg()));
        ArgumentTypes.register(Registry.COMMAND_ARGUMENT_TYPE, "redstonetools:int", IntegerSerializer.class, ConstantArgumentSerializer.of(() -> IntegerSerializer.integer()));
        ArgumentTypes.register(Registry.COMMAND_ARGUMENT_TYPE, "redstonetools:long", LongSerializer.class, ConstantArgumentSerializer.of(() -> LongSerializer.longArg()));
        ArgumentTypes.register(Registry.COMMAND_ARGUMENT_TYPE, "redstonetools:macroname", MacroNameSerializer.class, ConstantArgumentSerializer.of(MacroNameSerializer::macroName));
        ArgumentTypes.register(Registry.COMMAND_ARGUMENT_TYPE, "redstonetools:numberbase", NumberBaseSerializer.class, ConstantArgumentSerializer.of(NumberBaseSerializer::numberBase));
        ArgumentTypes.register(Registry.COMMAND_ARGUMENT_TYPE, "redstonetools:signalblock", SignalBlockSerializer.class, ConstantArgumentSerializer.of(SignalBlockSerializer::signalBlock));
        ArgumentTypes.register(Registry.COMMAND_ARGUMENT_TYPE, "redstonetools:string", StringSerializer.class, ConstantArgumentSerializer.of(StringSerializer::string));
    }
}
