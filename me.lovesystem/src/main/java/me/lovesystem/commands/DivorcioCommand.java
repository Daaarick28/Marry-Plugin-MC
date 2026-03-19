package me.lovesystem.commands;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import me.lovesystem.manager.MarriageManager;

public class DivorcioCommand implements CommandExecutor {

    private final MarriageManager manager;

    public DivorcioCommand(MarriageManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (!manager.isMarried(player.getUniqueId())) {
            player.sendMessage("§cNo estás casado.");
            return true;
        }

        manager.divorce(player.getUniqueId());
        player.sendMessage("§cTe has divorciado 💔");

        return true;
    }
}