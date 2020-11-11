package zone.rong.lolilib.tfc.mixin.block;

import net.dries007.tfc.api.types.Plant;
import net.dries007.tfc.objects.blocks.plants.BlockPlantTFC;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;
import java.util.Set;

import static net.minecraft.block.Block.spawnAsEntity;

@Mixin(BlockPlantTFC.class)
public abstract class BlockPlantTFCMixin extends Block {

    @Shadow @Final protected Plant plant;

    protected BlockPlantTFCMixin(Material material) {
        super(material);
    }

    /**
     * @author Rongmario
     * @reason Default stick drops
     */
    @Overwrite
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        if (!world.isRemote && !this.plant.getOreDictName().isPresent()) {
            Set<String> tools = stack.getItem().getToolClasses(stack);
            if (tools.contains("knife") || tools.contains("scythe")) {
                if (this.plant.getPlantType() != Plant.PlantType.SHORT_GRASS && this.plant.getPlantType() != Plant.PlantType.TALL_GRASS) {
                    spawnAsEntity(world, pos, new ItemStack(this, 1));
                }
            } else {
                if (this.plant.getPlantType() == Plant.PlantType.STANDARD) {
                    spawnAsEntity(world, pos, new ItemStack(Items.STICK, 1));
                }
            }
        }
        super.harvestBlock(world, player, pos, state, te, stack);
    }

}
