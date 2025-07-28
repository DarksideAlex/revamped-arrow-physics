package de.alex.arrowphysics.mixin;

import de.alex.arrowphysics.config.ArrowPhysicsConfig;
import de.alex.arrowphysics.config.ConfigManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PersistentProjectileEntity.class)
public abstract class ArrowEntityMixin {
	@Unique
	private Vec3d lastVelocity;

	@Inject(method = "tick", at = @At("HEAD"))
	private void storeLastVelocity(CallbackInfo ci) {
		lastVelocity = ((PersistentProjectileEntity)(Object)this).getVelocity();
	}

	@Inject(method = "tick", at = @At("TAIL"))
	private void blendVelocity(CallbackInfo ci) {
		PersistentProjectileEntity arrow = (PersistentProjectileEntity)(Object)this;
		Vec3d current = arrow.getVelocity();
		double easedStrength = 1 - Math.pow(1 - ConfigManager.CONFIG.effectStrength / 2.0, 3.2);
		easedStrength = MathHelper.clamp(easedStrength, 0.0, 1.0);

		double p = easedStrength, c = 1 - easedStrength;
		Vec3d blended = new Vec3d(
				lastVelocity.x * p + current.x * c,
				lastVelocity.y * p + current.y * c,
				lastVelocity.z * p + current.z * c
		);
		arrow.setVelocity(blended);
	}
}