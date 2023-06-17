package tools.redstone.redstonetools.utils;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

//#if MC>=11903
import net.minecraft.registry.Registries;
//#else
//$$ import net.minecraft.util.registry.Registry;
//#endif

public enum ColoredBlockType {
    // TODO: Merge some things with the ColoredBlock class so we dont have to repeat the formats and stuff
    WOOL("wool", "minecraft:%s_wool"),
    GLASS("glass", "minecraft:%s_stained_glass"),
    CONCRETE("concrete", "minecraft:%s_concrete"),
    TERRACOTTA("terracotta", "minecraft:%s_terracotta");

    private final String displayName;
    private final String blockIdFormat;

    ColoredBlockType(String displayName, String blockIdFormat) {
        this.displayName = displayName;
        this.blockIdFormat = blockIdFormat;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public ColoredBlock withColor(BlockColor color) {
        return ColoredBlock.fromBlock(toBlock())
                .withColor(color);
    }

    public String toBlockId() {
        return String.format(blockIdFormat, BlockColor.WHITE);
    }

    public Block toBlock() {
        //#if MC>=11903
        return Registries.BLOCK.get(Identifier.tryParse(toBlockId()));
        //#else
        //$$ return Registry.BLOCK.get(Identifier.tryParse(toBlockId()));
        //#endif
    }
}
