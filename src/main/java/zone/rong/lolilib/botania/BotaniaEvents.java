package zone.rong.lolilib.botania;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.common.item.material.ItemManaResource;
import zone.rong.lolilib.twilightforest.BlockTFPortalFrame;
import zone.rong.lolilib.util.Utils;

@Mod.EventBusSubscriber
public class BotaniaEvents {

    @SubscribeEvent
    public static void handleManaPearlThrows(PlayerInteractEvent.RightClickItem event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof ItemManaResource && stack.getItemDamage() == 1) {
            World world = event.getWorld();
            EntityPlayer player = event.getEntityPlayer();
            RayTraceResult result = Utils.rayTrace(world, player, false);
            if (result == null || result.typeOfHit == RayTraceResult.Type.BLOCK && world.getBlockState(result.getBlockPos()).getBlock() == BlockTFPortalFrame.INSTANCE) {
                return;
            }
            player.setActiveHand(event.getHand());
            player.swingArm(event.getHand());
            if (!world.isRemote) {
                BlockPos playerPos = player.getPosition();
                BlockPos blockpos = ((WorldServer) world).getChunkProvider().getNearestStructurePos(world, "Stronghold", playerPos, false);
                if (blockpos != null) {
                    EntityManaPearl pearl = new EntityManaPearl(world, player.posX, player.posY + (double) (player.height / 2.0F), player.posZ);
                    pearl.moveTowards(blockpos);
                    world.spawnEntity(pearl);
                    world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDEREYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));
                    world.playEvent(null, 1003, playerPos, 0);
                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                    player.addStat(StatList.getObjectUseStats(stack.getItem()));
                    event.setCancellationResult(EnumActionResult.SUCCESS);
                }
            }
        }
    }

}
