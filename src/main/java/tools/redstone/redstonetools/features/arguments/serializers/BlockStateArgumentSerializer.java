package tools.redstone.redstonetools.features.arguments.serializers;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.command.argument.BlockStateArgument;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.state.property.Property;

//#if MC>=11903
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.server.command.CommandManager;
import net.minecraft.registry.Registries;
//#else
//$$ import net.minecraft.util.registry.Registry;
//#endif

//#if MC>=11900
//$$ import net.minecraft.command.CommandRegistryAccess;
//$$ import net.minecraft.util.registry.DynamicRegistryManager;
//#endif

public class BlockStateArgumentSerializer extends BrigadierSerializer<BlockStateArgument, String> {

    private static final BlockStateArgumentSerializer INSTANCE = new BlockStateArgumentSerializer();

    private BlockStateArgumentSerializer() {
        //#if MC>=19903
        super(BlockStateArgument.class, BlockStateArgumentType.blockState(CommandManager.createRegistryAccess(BuiltinRegistries.createWrapperLookup())));
        //#elseif MC>=11900
        //$$ super(BlockStateArgument.class, BlockStateArgumentType.blockState(new CommandRegistryAccess(DynamicRegistryManager.BUILTIN.get())));
        //#else
        //$$ super(BlockStateArgument.class, BlockStateArgumentType.blockState());
        //#endif
    }

    public static BlockStateArgumentSerializer blockState() {
        return INSTANCE;
    }

    @Override
    public BlockStateArgument deserialize(String serialized) {
        try {
            return deserialize(new StringReader(serialized));
        } catch (CommandSyntaxException e) {
            throw new IllegalStateException("Syntax Exception: " + e.getMessage());
        }
    }

    @Override
    public String serialize(BlockStateArgument value) {
        BlockState state = value.getBlockState();
        Block block = state.getBlock();

        //#if MC>=11903
        StringBuilder builder = new StringBuilder().append(Registries.BLOCK.getId(block));
        //#else
        //$$ StringBuilder builder = new StringBuilder().append(Registry.BLOCK.getId(block));
        //#endif
        if (state.getProperties().size() == 0) {
            return builder.toString();
        }

        builder.append('[');
        boolean first = true;
        for (Property<?> prop : state.getProperties()) {
            if (first) {
                first = false;
            } else {
                builder.append(',');
            }

            builder.append(prop.getName())
                    .append('=')
                    .append(state.get(prop));
        }

        builder.append(']');
        return builder.toString();
    }
}
