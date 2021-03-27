package de.blutmondgilde.blutmondrpg.race;

import de.blutmondgilde.blutmondrpg.BlutmondRPG;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(BlutmondRPG.MODID)
public class Races {
    @ObjectHolder("none")
    public static Race NONE = null;
    @ObjectHolder("human")
    public static Race HUMAN = null;
    @ObjectHolder("asura")
    public static Race ASURA = null;
    @ObjectHolder("charr")
    public static Race CHARR = null;
    @ObjectHolder("norn")
    public static Race NORN = null;
    @ObjectHolder("sylvari")
    public static Race SYLVARI = null;
}
