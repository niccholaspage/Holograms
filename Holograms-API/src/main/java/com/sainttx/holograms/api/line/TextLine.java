package com.sainttx.holograms.api.line;

/**
 * Hologram line that displays text.
 */
public interface TextLine extends HologramLine {

    /**
     * Returns the text displayed by the line
     *
     * @return the text
     */
    String getText();

    /**
     * Sets the text to be displayed by the line
     *
     * @param text the new text
     */
    void setText(String text);
}
