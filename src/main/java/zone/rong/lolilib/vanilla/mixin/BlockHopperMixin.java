package zone.rong.lolilib.vanilla.mixin;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static zone.rong.lolilib.util.Utils.makeAABB;

@Mixin(BlockHopper.class)
public abstract class BlockHopperMixin extends Block {

    @Unique private static final EnumMap<EnumFacing, List<AxisAlignedBB>> BOUNDS;

    static {
        BOUNDS = Arrays.stream(EnumFacing.VALUES)
                .filter(t -> t != EnumFacing.UP)
                .collect(Collectors.toMap(a -> a, a -> Lists.newArrayList(makeAABB(0, 10, 0, 16, 16, 16), makeAABB(4, 4, 4, 12, 10, 12)), (u, v) -> { throw new IllegalStateException(); }, () -> new EnumMap<>(EnumFacing.class)));

        BOUNDS.get(EnumFacing.DOWN).add(makeAABB(6, 0, 6, 10, 4, 10));
        BOUNDS.get(EnumFacing.NORTH).add(makeAABB(6, 4, 0, 10, 8, 4));
        BOUNDS.get(EnumFacing.SOUTH).add(makeAABB(6, 4, 12, 10, 8, 16));
        BOUNDS.get(EnumFacing.WEST).add(makeAABB(0, 4, 6, 4, 8, 10));
        BOUNDS.get(EnumFacing.EAST).add(makeAABB(12, 4, 6, 16, 8, 10));
    }

    protected BlockHopperMixin(Material material) {
        super(material);
    }

    @Override
    @Deprecated
    @Nullable
    public RayTraceResult collisionRayTrace(IBlockState state, World worldIn, BlockPos pos, Vec3d start, Vec3d end) {
        return BOUNDS.get(state.getValue(BlockHopper.FACING)).stream()
                .map(bb -> rayTrace(pos, start, end, bb))
                .anyMatch(Objects::nonNull)
                ? super.collisionRayTrace(state, worldIn, pos, start, end) : null;
    }

}
