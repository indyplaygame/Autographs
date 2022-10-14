package indy.autographs.main;

import indy.autographs.commands.Commands;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        getCommand("autograph").setExecutor(new Commands());
        saveConfig();
        reloadConfig();
    }

    @Override
    public void onDisable() {
    }
}
