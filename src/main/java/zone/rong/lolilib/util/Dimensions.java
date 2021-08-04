package zone.rong.lolilib.util;

import com.elseytd.theaurorian.TADimensions;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class Dimensions {

    public static final DimensionType AURORIAN_DIMENSION_TYPE = TADimensions.theauroriandimension;
    public static final int AURORIAN_ID = AURORIAN_DIMENSION_TYPE.getId();

    public static final int THE_BENEATH = 10;

    public static boolean isOverworld(World world) {
        return world.provider.getDimensionType() == DimensionType.OVERWORLD;
    }

    public static boolean isNether(World world) {
        return world.provider.getDimensionType() == DimensionType.NETHER;
    }

    public static boolean isEnd(World world) {
        return world.provider.getDimensionType() == DimensionType.THE_END;
    }

}
