package zone.rong.lolilib.util;

import com.elseytd.theaurorian.TADimensions;
import net.dries007.tfc.world.classic.WorldTypeTFC;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldType;

public class Dimensions {

    public static final DimensionType TFC_OVERWORLD_DIMENSION_TYPE = DimensionType.OVERWORLD;
    public static final int TFC_OVERWORLD_ID = TFC_OVERWORLD_DIMENSION_TYPE.getId();
    public static final WorldType TFC_OVERWORLD_WORLD_TYPE = WorldTypeTFC.DEFAULT;

    public static final DimensionType AURORIAN_DIMENSION_TYPE = TADimensions.theauroriandimension;
    public static final int AURORIAN_ID = AURORIAN_DIMENSION_TYPE.getId();

    public static final int THE_BENEATH = 10;

}
