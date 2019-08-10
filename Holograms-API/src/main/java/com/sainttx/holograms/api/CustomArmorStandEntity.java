package com.sainttx.holograms.api;

public interface CustomArmorStandEntity extends HologramEntity {

    /**
     * Sets the display name for this entity.
     *
     * @param text the new text
     */
    void setName(String text);

    /**
     * Returns the current display name for this entity.
     *
     * @return the current text.
     */
    String getName();
}
