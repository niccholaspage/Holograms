package com.sainttx.holograms.api;

import com.sainttx.holograms.api.line.HologramLine;
import java.util.List;
import org.bukkit.Location;

public interface Hologram {

    /**
     * Returns the unique ID id of this Hologram.
     *
     * @return the holograms id
     */
    String getId();

    /**
     * Returns the location of this Hologram.
     *
     * @return the holograms location
     */
    Location getLocation();

    /**
     * Returns the lines contained by this Hologram.
     *
     * @return all lines in the hologram
     */
    List<HologramLine> getLines();

    /**
     * Adds a new {@link HologramLine} to this Hologram.
     *
     * @param line the line
     */
    void addLine(HologramLine line);

    /**
     * Inserts a new {@link HologramLine} to this Hologram at a specific index.
     *
     * @param line the line
     * @param index the index to add the line at
     */
    void addLine(HologramLine line, int index);

    /**
     * Removes a {@link HologramLine} from this Hologram and de-spawns it.
     *
     * @param line the line
     * @throws IllegalArgumentException if the line is not part of this hologram
     */
    void removeLine(HologramLine line);

    /**
     * Returns a {@link HologramLine} at a specific index.
     *
     * @param index the index
     */
    HologramLine getLine(int index);

    /**
     * De-spawns all of the lines in this Hologram.
     */
     void despawn();

    /**
     * Spawns all of the lines in this Hologram.
     */
     void spawn();

    /**
     * Teleports this Hologram to a new {@link Location}.
     *
     * @param location the location
     */
     void teleport(Location location);
}
