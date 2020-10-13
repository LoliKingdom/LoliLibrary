package zone.rong.lolilib.vanilla.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin extends Entity {

    public EntityLivingBaseMixin(World worldIn) {
        super(worldIn);
    }

    @ModifyConstant(method = "onEntityUpdate", constant = @Constant(intValue = 300))
    private int modifyForNewBreathingMechanic(int original) {
        int air = this.getAir();
        return air >= 300 ? 300 : replenishAirSupply(air);
    }

    private int replenishAirSupply(int air) {
        int i = EnchantmentHelper.getRespirationModifier((EntityLivingBase) (Object) this);
        return i > 0 && this.rand.nextInt(i + 1) > 0 ? air + 2 + i : air + 2;
    }

}
