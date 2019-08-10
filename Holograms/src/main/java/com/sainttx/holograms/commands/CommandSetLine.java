package com.sainttx.holograms.commands;

import com.sainttx.holograms.HologramImpl;
import com.sainttx.holograms.api.HologramPlugin;
import com.sainttx.holograms.api.line.HologramLine;
import com.sainttx.holograms.api.line.TextLine;
import com.sainttx.holograms.util.TextUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandSetLine implements CommandExecutor {

    private HologramPlugin plugin;

    public CommandSetLine(HologramPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 4) {
            sender.sendMessage(ChatColor.RED + "Usage: /hologram setline <name> <index> <text>");
        } else {
            String hologramName = args[1];
            HologramImpl hologram = plugin.getHologramManager().getHologram(hologramName);

            if (hologram == null) {
                sender.sendMessage(ChatColor.RED + "Couldn't find a hologram with name \"" + hologramName + "\".");
            } else {
                int index;
                try {
                    index = Integer.parseInt(args[2]);
                } catch (NumberFormatException ex) {
                    sender.sendMessage(ChatColor.RED + "Please enter a valid integer as your index.");
                    return true;
                }

                if (index < 0 || index >= hologram.getLines().size()) {
                    sender.sendMessage(ChatColor.RED + "Invalid index, must be between 0 and " + (hologram.getLines().size() - 1) + ".");
                } else {
                    HologramLine line = hologram.getLine(index);

                    if (!(line instanceof TextLine)) {
                        sender.sendMessage(ChatColor.RED + "Line " + index + " of that Hologram is not a text line and cannot be modified.");
                    } else {
                        String text = TextUtil.implode(3, args);
                        ((TextLine) line).setText(TextUtil.color(text));
                        line.getHologram().setDirty(true);
                        plugin.getHologramManager().saveHologram(hologram);
                        sender.sendMessage(TextUtil.color("&7Set the text at position &f" + index + " &7of hologram &f\""
                                + hologram.getId() + "\""));
                    }
                }
            }
        }

        return true;
    }
}