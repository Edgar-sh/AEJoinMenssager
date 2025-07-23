package com.anishoeffects.plugins.aEJoinMenssager;

import com.anishoeffects.plugins.aEJoinMenssager.listener.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;


public final class AEJoinMenssager extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin started successfully!");
        getServer().getPluginManager().registerEvents(new JoinListener(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin disabled successfully!");
    }
}
