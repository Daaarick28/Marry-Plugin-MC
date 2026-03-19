package me.lovesystem.commands;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import me.lovesystem.manager.MarriageManager;

import java.util.UUID;

public class RechazarCommand implements CommandExecutor {

    private final MarriageManager manager;

    public RechazarCommand(MarriageManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        UUID proposerUUID = manager.getProposal(player.getUniqueId());

        if (proposerUUID == null) {
            player.sendMessage("§cNo tienes propuestas.");
            return true;
        }

        manager.removeProposal(player.getUniqueId());
        player.sendMessage("§eHas rechazado la propuesta 💔");

        return true;
    }
}