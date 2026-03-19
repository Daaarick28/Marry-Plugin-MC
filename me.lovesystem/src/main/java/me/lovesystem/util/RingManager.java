package me.lovesystem.util;

import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import java.util.Arrays;

public class RingManager {

    public static ItemStack getRing() {
        ItemStack ring = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = ring.getItemMeta();

        meta.setDisplayName("§d§lAnillo de Compromiso 💍");
        meta.setLore(Arrays.asList(
                "§7Haz clic derecho en tu pareja",
                "§7para pedir matrimonio ❤️"
        ));

        ring.setItemMeta(meta);
        return ring;
    }

    public static boolean isRing(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        return item.getItemMeta().getDisplayName()
                .equals("§d§lAnillo de Compromiso 💍");
    }
}