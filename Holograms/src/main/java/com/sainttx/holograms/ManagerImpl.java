package com.sainttx.holograms;

import com.sainttx.holograms.api.HologramManager;
import com.sainttx.holograms.api.exception.HologramEntitySpawnException;
import com.sainttx.holograms.api.line.HologramLine;
import com.sainttx.holograms.api.line.UpdatingHologramLine;
import com.sainttx.holograms.util.LocationUtil;
import org.bukkit.Location;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class ManagerImpl implements HologramManager {

    private HologramPlugin plugin;
    private Configuration persistingHolograms;
    private Map<String, HologramImpl> activeHolograms = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private Set<UpdatingHologramLine> trackedUpdatingLines = new HashSet<>();

    ManagerImpl(HologramPlugin plugin) {
        this.plugin = plugin;
        persistingHolograms = new Configuration(plugin, "holograms.yml");
    }

    /**
     * Loads all saved Holograms
     */
    void load() {
        if (persistingHolograms == null) {
            persistingHolograms = new Configuration(plugin, "holograms.yml");
        }

        // Load all the holograms
        if (persistingHolograms.isConfigurationSection("holograms")) {
            loadHolograms:
            for (String hologramName : persistingHolograms.getConfigurationSection("holograms").getKeys(false)) {
                List<String> uncoloredLines = persistingHolograms.getStringList("holograms." + hologramName + ".lines");
                Location location = LocationUtil.stringAsLocation(persistingHolograms.getString("holograms." + hologramName + ".location"));

                if (location == null) {
                    plugin.getLogger().warning("Hologram \"" + hologramName + "\" has an invalid location");
                    continue;
                }

                // Create the Hologram
                HologramImpl hologram = new HologramImpl(hologramName, location, true);
                // Add the lines
                for (String string : uncoloredLines) {
                    HologramLine line = plugin.parseLine(hologram, string);
                    try {
                        hologram.addLine(line);
                    } catch (HologramEntitySpawnException e) {
                        plugin.getLogger().log(Level.WARNING, "Failed to spawn Hologram \"" + hologramName + "\"", e);
                        continue loadHolograms;
                    }
                }
                addActiveHologram(hologram);
                plugin.getLogger().info("Loaded hologram with \"" + hologram.getId() + "\" with " + hologram.getLines().size() + " lines");
            }
        } else {
            plugin.getLogger().warning("holograms.yml file had no 'holograms' section defined, no holograms loaded");
        }
    }

    @Override
    public void saveHologram(HologramImpl hologram) {
        String hologramName = hologram.getId();
        Collection<HologramLine> holoLines = hologram.getLines();
        List<String> uncoloredLines = holoLines.stream()
                .map(HologramLine::getRaw)
                .collect(Collectors.toList());
        hologram.setDirty(false);
        persistingHolograms.set("holograms." + hologramName + ".location", LocationUtil.locationAsString(hologram.getLocation()));
        persistingHolograms.set("holograms." + hologramName + ".lines", uncoloredLines);
        persistingHolograms.saveConfiguration();
    }

    @Override
    public void deleteHologram(HologramImpl hologram) {
        hologram.despawn();
        removeActiveHologram(hologram);
        persistingHolograms.set("holograms." + hologram.getId(), null);
        persistingHolograms.saveConfiguration();
    }

    @Override
    public HologramImpl getHologram(String name) {
        return activeHolograms.get(name);
    }

    @Override
    public Map<String, HologramImpl> getActiveHolograms() {
        return activeHolograms;
    }

    @Override
    public void addActiveHologram(HologramImpl hologram) {
        activeHolograms.put(hologram.getId(), hologram);
    }

    @Override
    public void removeActiveHologram(HologramImpl hologram) {
        activeHolograms.remove(hologram.getId());
    }

    @Override
    public void trackLine(UpdatingHologramLine line) {
        trackedUpdatingLines.add(line);
    }

    @Override
    public boolean untrackLine(UpdatingHologramLine line) {
        return trackedUpdatingLines.remove(line);
    }

    @Override
    public Collection<? extends UpdatingHologramLine> getTrackedLines() {
        return trackedUpdatingLines;
    }

    @Override
    public void clear() {
        getActiveHolograms().values().forEach(HologramImpl::despawn);
        getActiveHolograms().clear();
    }
}
