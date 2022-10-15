package indy.autographs.commands;

import indy.autographs.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            List<String> newLore = (List<String>) Utils.getConfig().getList("Autographs.autograph-format");

            if(meta.getLore() != null) {
                lore = meta.getLore();
            }

            for(int i = 0; i < newLore.size(); i++) {
                newLore.set(i, Utils.colorFormat(newLore.get(i)).replace("%player%", player.getName()));
            }

            if (args.length == 0) {
                if (Utils.getConfig().getBoolean("Autographs.allow-no-name-autograph")) {
                    if(!lore.containsAll(newLore)) {
                        for (Object loreLine : newLore) {
                            lore.add(Utils.colorFormat((String) loreLine));
                        }
                    }
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                } else {
                    sender.sendMessage(Utils.getMessage("Messages.missing-arguments"));
                }
            } else {
                if (Utils.getConfig().getBoolean("Autographs.allow-no-name-autograph")) {
                    for (Object loreLine : Utils.getConfig().getList("Autographs.autograph-format")) {
                        lore.add(Utils.colorFormat(((String) loreLine)).replace("%player%", player.getName()));
                    }
                    String name = String.join(" ", args);
                    meta.setLore(lore);
                    meta.setDisplayName(Utils.colorFormat(name));
                    item.setItemMeta(meta);
                }
            }
        } else {
            sender.sendMessage(Utils.getMessage("Messages.non-player-executor"));
        }
        return false;
    }
}
