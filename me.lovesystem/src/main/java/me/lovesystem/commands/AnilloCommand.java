package me.lovesystem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.lovesystem.util.RingManager;

public class AnilloCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        player.getInventory().addItem(RingManager.getRing());
        player.sendMessage("§aHas recibido un anillo de compromiso 💍");

        return true;
    }
}