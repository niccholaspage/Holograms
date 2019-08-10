package com.sainttx.holograms.api;

import org.bukkit.inventory.ItemStack;

public interface CustomItemEntity extends HologramEntity {

    /**
     * Sets the item for this entity to display.
     *
     * @param item the new item
     */
    void setItemStack(ItemStack item);

    /**
     * Returns the current displayed item by this entity.
     *
     * @return the current item
     */
    ItemStack getItemStack();
}
