package me.lovesystem.commands;

import me.lovesystem.manager.MarriageManager;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.UUID;

public class AceptarCommand implements CommandExecutor {

    private final MarriageManager manager;

    public AceptarCommand(MarriageManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player p1 = (Player) sender;

        // Verificar si tiene propuesta
        UUID proposerUUID = manager.getProposal(p1.getUniqueId());

        if (proposerUUID == null) {
            p1.sendMessage("§cNo tienes ninguna propuesta pendiente.");
            return true;
        }

        Player p2 = Bukkit.getPlayer(proposerUUID);

        if (p2 == null) {
            p1.sendMessage("§cEl jugador que te propuso no está conectado.");
            return true;
        }

        // 💍 Quitar anillo
        ItemStack item = p1.getInventory().getItemInMainHand();
        if (item != null) {
            if (item.getAmount() > 1) {
                item.setAmount(item.getAmount() - 1);
            } else {
                p1.getInventory().setItemInMainHand(null);
            }
        }

        // ❤️ Casarlos
        manager.marry(p1.getUniqueId(), p2.getUniqueId());
        manager.removeProposal(p1.getUniqueId());

        // 🎆 Fuegos + ❤️ corazones
        for (Player p : new Player[]{p1, p2}) {

            Firework fw = (Firework) p.getWorld().spawn(p.getLocation(), Firework.class);
            FireworkMeta meta = fw.getFireworkMeta();

            meta.addEffect(FireworkEffect.builder()
                    .withColor(Color.FUCHSIA)
                    .withColor(Color.RED)
                    .with(FireworkEffect.Type.BALL_LARGE)
                    .trail(true)
                    .flicker(true)
                    .build());

            meta.setPower(1);
            fw.setFireworkMeta(meta);

            p.getWorld().spawnParticle(
                    Particle.HEART,
                    p.getLocation().add(0, 2, 0),
                    25,
                    0.5, 0.5, 0.5,
                    0.1
            );
        }

        // 📢 Anuncio global
        Bukkit.broadcastMessage("§d💍 " + p1.getName() + " y " + p2.getName() + " ahora están casados! ❤️");

        return true;
    }
}