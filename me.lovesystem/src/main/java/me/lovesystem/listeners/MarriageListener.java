package me.lovesystem.listeners;

import org.bukkit.event.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.entity.Player;
import me.lovesystem.manager.MarriageManager;
import me.lovesystem.util.RingManager;

public class MarriageListener implements Listener {

    private final MarriageManager manager;

    public MarriageListener(MarriageManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {

        if (!(e.getRightClicked() instanceof Player)) return;

        Player player = e.getPlayer();
        Player target = (Player) e.getRightClicked();

        if (!RingManager.isRing(player.getInventory().getItemInMainHand())) return;

        if (manager.isMarried(player.getUniqueId())) {
            player.sendMessage("§cYa estás casado 😳");
            return;
        }

        manager.addProposal(target.getUniqueId(), player.getUniqueId());

        player.sendMessage("§aLe has pedido matrimonio a " + target.getName() + " 💍");
        target.sendMessage("§d" + player.getName() + " quiere casarse contigo 💍");
        target.sendMessage("§a/aceptar §7o §c/rechazar");
    }
}