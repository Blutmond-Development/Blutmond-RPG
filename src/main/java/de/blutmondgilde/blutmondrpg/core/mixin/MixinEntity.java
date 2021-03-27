package de.blutmondgilde.blutmondrpg.core.mixin;

import de.blutmondgilde.blutmondrpg.util.CapabilityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow
    public abstract EntityType<?> getType();

    @Shadow
    public World level;

    @Shadow
    public abstract int getId();

    @Inject(method = "getBoundingBoxForPose", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/Entity;getDimensions(Lnet/minecraft/entity/Pose;)Lnet/minecraft/entity/EntitySize;"), cancellable = true)
    private void getBoundingBoxForPose(Pose pose, CallbackInfoReturnable<AxisAlignedBB> cir) {
        if (!this.getType().equals(EntityType.PLAYER)) return;
        Entity entity = level.getEntity(getId());
        if (entity == null) return;
        entity.getCapability(CapabilityHelper.RACE_CAPABILITY).ifPresent(iRaceProvider -> {
            final AxisAlignedBB axisAlignedBB = iRaceProvider.getRace().getPlayerBoundingBox(entity, pose);
            cir.setReturnValue(axisAlignedBB);
        });
    }
}
