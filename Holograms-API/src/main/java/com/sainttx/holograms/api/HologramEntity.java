package com.sainttx.holograms.api;

import com.sainttx.holograms.api.line.HologramLine;
import org.bukkit.entity.Entity;

public interface HologramEntity {

    /**
     * Returns the parenting {@link HologramLine} of this base.
     *
     * @return the base line
     */
    HologramLine getHologramLine();

    /**
     * Returns the current mount that this entity is sitting on.
     *
     * @return the mount
     */
    HologramEntity getMount();

    /**
     * Mounts this item to a new entity. Passing in a null value
     * will remove any mount that this entity is currently sitting on.
     *
     * @param entity new mount
     */
    void setMount(HologramEntity entity);

    /**
     * Permanently removes this entity.
     */
    void remove();

    /**
     * Gets the Bukkit entity for this hologram line.
     *
     * @return the entity
     */
    Entity getBukkitEntity();

    /**
     * Sets the position of the entity.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param z z-coordinate
     */
    void setPosition(double x, double y, double z);
}
