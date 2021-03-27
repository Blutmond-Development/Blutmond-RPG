package de.blutmondgilde.blutmondrpg.race;

import de.blutmondgilde.blutmondrpg.attributes.PlayerAttributeSet;
import de.blutmondgilde.blutmondrpg.util.PlayerSizeHelper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.registries.ForgeRegistryEntry;

@RequiredArgsConstructor
public abstract class Race extends ForgeRegistryEntry<Race> {
    @Getter
    private final TranslationTextComponent name;
    @Getter
    private final PlayerAttributeSet baseStats;


    public EntitySize getPlayerSize(final EntityEvent.Size event) {
        return PlayerSizeHelper.scalePlayerHeightAndWidth(getSizeScaleFactor(), event.getPose());
    }

    public EntitySize getPlayerSize(final Pose pose) {
        return PlayerSizeHelper.scalePlayerHeightAndWidth(getSizeScaleFactor(), pose);
    }

    public float getPlayerEyePos(final EntityEvent.Size event) {
        return getPlayerSize(event).height * 0.85F;
    }

    public AxisAlignedBB getPlayerBoundingBox(final EntityEvent.Size event) {
        final EntitySize entitySize = getPlayerSize(event);
        final float entityWith = entitySize.width / 2.0F;
        final Entity entity = event.getEntity();
        return calculatePlayerBoundingBox(entityWith, entitySize.height, entity);
    }

    public AxisAlignedBB getPlayerBoundingBox(final Entity entity, final Pose pose) {
        final EntitySize entitySize = getPlayerSize(pose);
        final float entityWith = entitySize.width / 2.0F;
        return calculatePlayerBoundingBox(entityWith, entitySize.height, entity);
    }

    private AxisAlignedBB calculatePlayerBoundingBox(final float entityWidth, final float entityHeight, final Entity entity) {
        Vector3d vecGround = new Vector3d(entity.getX() - (double) entityWidth, entity.getY(), entity.getZ() - (double) entityWidth);
        Vector3d vecTop = new Vector3d(entity.getX() + (double) entityWidth, entity.getY() + (double) entityHeight, entity.getZ() + (double) entityWidth);
        return new AxisAlignedBB(vecGround, vecTop);
    }

    /**
     * Factor to resize the {@link net.minecraft.entity.player.PlayerEntity} model & hitbox.
     *
     * @return factor (1.0F = default player size)
     */
    public abstract float getSizeScaleFactor();

    @Override
    public String toString() {
        return "Race{" +
                "name=" + name.getString() +
                '}';
    }
}
