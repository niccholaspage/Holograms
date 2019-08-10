package com.sainttx.holograms.commands;

import com.sainttx.holograms.HologramImpl;
import com.sainttx.holograms.api.HologramPlugin;
import com.sainttx.holograms.util.TextUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMoveHere implements CommandExecutor {

    private HologramPlugin plugin;

    public CommandMoveHere(HologramPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can move holograms.");
        } else if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /hologram movehere <name>");
        } else {
            String hologramName = args[1];
            HologramImpl hologram = plugin.getHologramManager().getHologram(hologramName);

            if (hologram == null) {
                sender.sendMessage(ChatColor.RED + "Couldn't find a hologram with name \"" + hologramName + "\".");
            } else {
                Player player = (Player) sender;
                hologram.teleport(player.getLocation());
                plugin.getHologramManager().saveHologram(hologram);
                // hologram.refreshAll();
                sender.sendMessage(TextUtil.color("&7Teleported hologram &f\"" + hologramName + "\" &7to your location."));
            }
        }

        return true;
    }
}