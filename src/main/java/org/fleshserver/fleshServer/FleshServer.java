package org.fleshserver.fleshServer;

import co.aikar.commands.PaperCommandManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.fleshserver.fleshServer.main.command.DebugCommands;

import org.fleshserver.fleshServer.main.events.MobEvents;
import org.fleshserver.fleshServer.main.events.PotionEffects;
import org.fleshserver.fleshServer.main.events.UniversalEvents;

public final class FleshServer extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("evil john");
        intAll();
        intCommands();
    }

    @Override
    public void onDisable() {
        getLogger().info("evil john");
    }
    private void registerListeners(Listener... listeners){
        for(Listener listener : listeners){
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
    public void intCommands(){
        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new DebugCommands());
    }
    public void intAll(){
        registerListeners(
                new PotionEffects(),
                new UniversalEvents(),
                new MobEvents()
        );
    }
}
