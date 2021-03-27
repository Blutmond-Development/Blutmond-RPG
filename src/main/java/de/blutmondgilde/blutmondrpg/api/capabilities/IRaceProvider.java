package de.blutmondgilde.blutmondrpg.api.capabilities;

import de.blutmondgilde.blutmondrpg.race.Race;
import net.minecraft.entity.Pose;

public interface IRaceProvider extends ISyncableEntityCapability {
    Race getRace();

    void setRace(Race race);

    void setLastPose(Pose pose);

    Pose getLastPose();
}
