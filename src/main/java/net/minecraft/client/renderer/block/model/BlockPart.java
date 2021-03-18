package net.minecraft.client.renderer.block.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.vector.Vector3f;

@SideOnly(Side.CLIENT)
public class BlockPart {
    public final Vector3f positionFrom;
    public final Vector3f positionTo;
    public final Map<EnumFacing, BlockPartFace> mapFaces;
    public final BlockPartRotation partRotation;
    public final boolean shade;

    public BlockPart(Vector3f positionFromIn, Vector3f positionToIn, Map<EnumFacing, BlockPartFace> mapFacesIn, @Nullable BlockPartRotation partRotationIn, boolean shadeIn) {
        this.positionFrom = positionFromIn;
        this.positionTo = positionToIn;
        this.mapFaces = new EnumMap<>(mapFacesIn);
        this.partRotation = partRotationIn;
        this.shade = shadeIn;
        this.setDefaultUvs();
    }

    private void setDefaultUvs() {
        for (Entry<EnumFacing, BlockPartFace> entry : this.mapFaces.entrySet()) {
            entry.getValue().blockFaceUV.setUvs(this.getFaceUvs(entry.getKey()));
        }
    }

    private float[] getFaceUvs(EnumFacing facing) {
        switch (facing) {
            case DOWN:
                return new float[] { this.positionFrom.x, 16.0F - this.positionTo.z, this.positionTo.x, 16.0F - this.positionFrom.z };
            case UP:
                return new float[] { this.positionFrom.x, this.positionFrom.z, this.positionTo.x, this.positionTo.z };
            case NORTH:
            default:
                return new float[] { 16.0F - this.positionTo.x, 16.0F - this.positionTo.y, 16.0F - this.positionFrom.x, 16.0F - this.positionFrom.y };
            case SOUTH:
                return new float[] { this.positionFrom.x, 16.0F - this.positionTo.y, this.positionTo.x, 16.0F - this.positionFrom.y };
            case WEST:
                return new float[] { this.positionFrom.z, 16.0F - this.positionTo.y, this.positionTo.z, 16.0F - this.positionFrom.y };
            case EAST:
                return new float[] { 16.0F - this.positionTo.z, 16.0F - this.positionTo.y, 16.0F - this.positionFrom.z, 16.0F - this.positionFrom.y };
        }
    }
}