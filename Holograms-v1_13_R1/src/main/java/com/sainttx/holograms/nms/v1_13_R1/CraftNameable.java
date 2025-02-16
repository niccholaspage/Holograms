package com.sainttx.holograms.nms.v1_13_R1;

import org.bukkit.EntityEffect;
import org.bukkit.craftbukkit.v1_13_R1.CraftServer;
import org.bukkit.craftbukkit.v1_13_R1.entity.CraftArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.Collection;

public class CraftNameable extends CraftArmorStand {

    public CraftNameable(CraftServer server, EntityNameable entity) {
        super(server, entity);
    }

    @Override
    public void remove() {
    }

    @Override
    public void setArms(boolean arms) {
    }

    @Override
    public void setBasePlate(boolean basePlate) {
    }

    @Override
    public void setBodyPose(EulerAngle pose) {
    }

    @Override
    public void setBoots(ItemStack item) {
    }

    @Override
    public void setChestplate(ItemStack item) {
    }

    @Override
    public void setGravity(boolean gravity) {
    }

    @Override
    public void setHeadPose(EulerAngle pose) {
    }

    @Override
    public void setHelmet(ItemStack item) {
    }

    @Override
    public void setItemInHand(ItemStack item) {
    }

    @Override
    public void setLeftArmPose(EulerAngle pose) {
    }

    @Override
    public void setLeftLegPose(EulerAngle pose) {
    }

    @Override
    public void setLeggings(ItemStack item) {
    }

    @Override
    public void setRightArmPose(EulerAngle pose) {
    }

    @Override
    public void setRightLegPose(EulerAngle pose) {
    }

    @Override
    public void setSmall(boolean small) {
    }

    @Override
    public void setVisible(boolean visible) {
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect) {
        return false;
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect, boolean param) {
        return false;
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> effects) {
        return false;
    }

    @Override
    public void setRemoveWhenFarAway(boolean remove) {
    }

    @Override
    public void setVelocity(Vector vel) {
    }

    @Override
    public void setFireTicks(int ticks) {
    }

    @Override
    public boolean setPassenger(Entity entity) {
        return false;
    }

    @Override
    public boolean eject() {
        return false;
    }

    @Override
    public boolean leaveVehicle() {
        return false;
    }

    @Override
    public void playEffect(EntityEffect effect) {
    }

    @Override
    public void setCustomName(String name) {
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
    }

}