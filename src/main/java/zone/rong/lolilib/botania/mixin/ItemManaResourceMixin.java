package zone.rong.lolilib.botania.mixin;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vazkii.botania.common.item.material.ItemManaResource;
import zone.rong.lolilib.botania.EntityManaPearl;

@Mixin(ItemManaResource.class)
public abstract class ItemManaResourceMixin extends Item {

    @Inject(method = "onItemRightClick", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/EntityLivingBase;getHeldItem(Lnet/minecraft/util/EnumHand;)Lnet/minecraft/item/ItemStack;"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void throwManaPearl(World world, EntityPlayer player, EnumHand hand, CallbackInfoReturnable<ActionResult<ItemStack>> cir, ItemStack stack) {
        if (stack.getItemDamage() == 1) {
            RayTraceResult result = this.rayTrace(world, player, false);
            if (result.typeOfHit == RayTraceResult.Type.BLOCK && world.getBlockState(result.getBlockPos()).getBlock() == Blocks.END_PORTAL_FRAME) {
                cir.setReturnValue(ActionResult.newResult(EnumActionResult.PASS, stack));
            } else {
                player.setActiveHand(hand);
                if (!world.isRemote) {
                    BlockPos playerPos = player.getPosition();
                    BlockPos blockpos = ((WorldServer) world).getChunkProvider().getNearestStructurePos(world, "Stronghold", playerPos, false);
                    if (blockpos != null) {
                        EntityManaPearl pearl = new EntityManaPearl(world, player.posX, player.posY + (double) (player.height / 2.0F), player.posZ);
                        pearl.moveTowards(blockpos);
                        world.spawnEntity(pearl);
                        world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDEREYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                        world.playEvent(null, 1003, playerPos, 0);
                        if (!player.capabilities.isCreativeMode) {
                            stack.shrink(1);
                        }
                        player.addStat(StatList.getObjectUseStats(this));
                        cir.setReturnValue(ActionResult.newResult(EnumActionResult.SUCCESS, stack));
                    }
                }
            }
        }
    }

}
