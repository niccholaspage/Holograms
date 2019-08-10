package com.sainttx.holograms.api;

import org.bukkit.World;
import org.bukkit.entity.Entity;

public interface HologramEntityController {

    /**
     * Returns the current Minecraft version.
     *
     * @return the minecraft version
     */
    MinecraftVersion getMinecraftVersion();

    /**
     * Spawns a new custom armor stand entity in the world.
     *
     * @param world world to spawn in
     * @param x x-coordinate
     * @param y y-coordinate
     * @param z z-coordinate
     * @return the resulting entity that was spawned
     */
    CustomArmorStandEntity spawnArmorStand(World world, double x, double y, double z);

    /**
     * Spawns a new custom item entity in the world.
     *
     * @param world world to spawn in
     * @param x x-coordinate
     * @param y y-coordinate
     * @param z z-coordinate
     * @return the resulting entity that was spawned
     */
    CustomItemEntity spawnItem(World world, double x, double y, double z);

    /**
     * Returns the {@link HologramEntity} of a hologram entity. If the
     * entity is not a Hologram <code>null</code> is returned.
     *
     * @param bukkitEntity Bukkit entity
     * @return the base entity
     */
    HologramEntity getHologramEntity(Entity bukkitEntity);
}
