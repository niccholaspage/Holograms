package com.sainttx.holograms.nms.v1_14_R1;

import com.sainttx.holograms.api.HologramEntityController;
import com.sainttx.holograms.api.HologramPlugin;
import com.sainttx.holograms.api.MinecraftVersion;
import com.sainttx.holograms.api.entity.HologramEntity;
import com.sainttx.holograms.api.entity.ItemHolder;
import com.sainttx.holograms.api.entity.Nameable;
import com.sainttx.holograms.api.line.HologramLine;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.minecraft.server.v1_14_R1.Entity;
import net.minecraft.server.v1_14_R1.WorldServer;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.logging.Level;

public class HologramEntityControllerImpl implements HologramEntityController {

    private static final Method registerEntityMethod;
    static {
        try {
            registerEntityMethod = WorldServer.class.getDeclaredMethod("registerEntity", Entity.class);
            registerEntityMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private HologramPlugin plugin;

    public HologramEntityControllerImpl(HologramPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public MinecraftVersion getMinecraftVersion() {
        return MinecraftVersion.V1_14_R1;
    }

    @Override
    public Nameable spawnNameable(HologramLine line, Location location) {
        WorldServer nmsWorld = ((CraftWorld) location.getWorld()).getHandle();
        EntityNameable armorStand = new EntityNameable(nmsWorld, line);
        armorStand.setPosition(location.getX(), location.getY(), location.getZ());
        if (!addEntityToWorld(nmsWorld, armorStand)) {
            plugin.getLogger().log(Level.WARNING, "Failed to spawn hologram entity in world " + location.getWorld().getName()
                    + " at x:" + location.getX() + " y:" + location.getY() + " z:" + location.getZ());
            armorStand.remove();
            return null;
        }

        return armorStand;
    }

    @Override
    public ItemHolder spawnItemHolder(HologramLine line, Location location) {
        WorldServer nmsWorld = ((CraftWorld) location.getWorld()).getHandle();
        EntityItemHolder item = new EntityItemHolder(nmsWorld, line);
        item.setPosition(location.getX(), location.getY(), location.getZ());
        if (!addEntityToWorld(nmsWorld, item)) {
            plugin.getLogger().log(Level.WARNING, "Failed to spawn item entity in world " + location.getWorld().getName()
                    + " at x:" + location.getX() + " y:" + location.getY() + " z:" + location.getZ());
            item.remove();
            return null;
        }
        return item;
    }

    private boolean addEntityToWorld(WorldServer nmsWorld, Entity nmsEntity) {
        int x = (int) Math.floor(nmsEntity.locX / 16.0D);
        int z = (int) Math.floor(nmsEntity.locZ / 16.0D);

        if (!nmsWorld.isChunkLoaded(x, z)) {
            nmsEntity.dead = true;
            return false;
        }

        net.minecraft.server.v1_14_R1.Chunk chunk = nmsWorld.getChunkAt(x, z);
        chunk.a(nmsEntity);
        try {
            registerEntityMethod.invoke(nmsWorld, nmsEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public HologramEntity getHologramEntity(org.bukkit.entity.Entity bukkitEntity) {
        Entity nmsEntity = ((CraftEntity) bukkitEntity).getHandle();
        return nmsEntity instanceof HologramEntity ? (HologramEntity) nmsEntity : null;
    }
}
