package me.lovesystem;

import org.bukkit.plugin.java.JavaPlugin;
import me.lovesystem.manager.MarriageManager;
import me.lovesystem.commands.*;
import me.lovesystem.listeners.MarriageListener;

public class LoveSystem extends JavaPlugin {

    private MarriageManager marriageManager;

    @Override
    public void onEnable() {

        marriageManager = new MarriageManager();

        getCommand("anillo").setExecutor(new AnilloCommand());
        getCommand("aceptar").setExecutor(new AceptarCommand(marriageManager));
        getCommand("rechazar").setExecutor(new RechazarCommand(marriageManager));
        getCommand("divorcio").setExecutor(new DivorcioCommand(marriageManager));

        getServer().getPluginManager().registerEvents(new MarriageListener(marriageManager), this);

        getLogger().info("LoveSystem activado 💍");
    }
}