package indy.autographs.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Utils {

    public static Plugin plugin() {
        return Bukkit.getPluginManager().getPlugin("Autographs");
    }

    public static FileConfiguration getConfig() {
        return plugin().getConfig();
    }

    public static String colorFormat(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String getMessage(String path) {
        return colorFormat(getConfig().getString(path));
    }

}
