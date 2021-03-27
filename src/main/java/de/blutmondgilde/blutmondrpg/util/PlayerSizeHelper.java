package de.blutmondgilde.blutmondrpg.util;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;

import java.util.Map;

public class PlayerSizeHelper {
    private static final EntitySize DEFAULT_STANDING_SIZE = EntitySize.scalable(0.6F, 1.8F);
    private static final EntitySize DEFAULT_SLEEPING_SIZE = EntitySize.fixed(0.2F, 0.2F);
    private static final Map<Pose, EntitySize> SIZE_BY_POSE = ImmutableMap.<Pose, EntitySize>builder().put(Pose.STANDING, DEFAULT_STANDING_SIZE).put(Pose.SLEEPING, DEFAULT_SLEEPING_SIZE).put(Pose.FALL_FLYING, EntitySize.scalable(0.6F, 0.6F)).put(Pose.SWIMMING, EntitySize.scalable(0.6F, 0.6F)).put(Pose.SPIN_ATTACK, EntitySize.scalable(0.6F, 0.6F)).put(Pose.CROUCHING, EntitySize.scalable(0.6F, 1.5F)).put(Pose.DYING, EntitySize.fixed(0.2F, 0.2F)).build();

    public static EntitySize scalePlayerHeightAndWidth(final float scale, final Pose pose) {
        return SIZE_BY_POSE.getOrDefault(pose, DEFAULT_STANDING_SIZE).scale(scale);
    }
}
