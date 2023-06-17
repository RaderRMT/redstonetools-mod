package tools.redstone.redstonetools.utils;

import net.minecraft.util.hit.BlockHitResult;

public class RaycastUtils {
    private RaycastUtils() {
    }

    public static BlockHitResult getBlockHitNeighbor(BlockHitResult hit) {
        var sideOffset = hit.getSide().getUnitVector();

        //#if MC>=11904
        var newBlockPos = hit.getBlockPos().add((int) sideOffset.x(), (int) sideOffset.y(), (int) sideOffset.z());
        //#elseif MC>=11903
        //$$ var newBlockPos = hit.getBlockPos().add(sideOffset.x(), sideOffset.y(), sideOffset.z());
        //#else
        //$$ var newBlockPos = hit.getBlockPos().add(sideOffset.getX(), sideOffset.getY(), sideOffset.getZ());
        //#endif

        return hit.withBlockPos(newBlockPos);
    }
}
