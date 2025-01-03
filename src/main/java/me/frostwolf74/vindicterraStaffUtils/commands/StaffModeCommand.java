package me.frostwolf74.vindicterraStaffUtils.commands;

import me.frostwolf74.vindicterraStaffUtils.VindicterraStaffUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class StaffModeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player p){
            if (!(p.hasPermission("VSU.staff"))) {
                return false;
            }

            FileConfiguration config = VindicterraStaffUtils.getPlugin().getConfig();

            if(Boolean.TRUE.equals(p.getPersistentDataContainer().get(new NamespacedKey(VindicterraStaffUtils.getPlugin(), "inStaffMode"), PersistentDataType.BOOLEAN))) {
                // key is disabled first as a failsafe
                p.getPersistentDataContainer().set(new NamespacedKey(VindicterraStaffUtils.getPlugin(), "inStaffMode"), PersistentDataType.BOOLEAN, false);

                p.getInventory().clear();
                ItemStack[] inventory = new ItemStack[41];

                for(int index : config.getIntegerList("savedInventory." + p.getUniqueId() + ".PlayerInventory.ItemStack.")){
                    p.getServer().getLogger().info(String.valueOf(index));
                    inventory[index] = config.getItemStack("savedInventory." + p.getUniqueId() + ".PlayerInventory.ItemStack." +  index);
                }

                p.getServer().getLogger().info(Arrays.toString(inventory)); // TODO debug message
                p.getInventory().setContents(inventory);
            }
            else{ // if player is not in staff mode
                PlayerInventory savedInventory = p.getInventory();
                int i = 0;

                for(ItemStack item : savedInventory.getContents()){
                    config.set("savedInventory." + p.getUniqueId() + ".PlayerInventory.ItemStack." +  i, item);
                    ++i;
                }
                VindicterraStaffUtils.getPlugin().saveConfig();

                // create staff tools

                ItemStack freezeTool = new ItemStack(Material.ICE);
                freezeTool.setItemMeta(applyDisplayName(freezeTool, Component.text("Freeze", TextColor.color(255,215,0), TextDecoration.BOLD)));

                ItemStack invSeeTool = new ItemStack(Material.CHEST);
                invSeeTool.setItemMeta(applyDisplayName(invSeeTool, Component.text("See Inventory", TextColor.color(255,215,0), TextDecoration.BOLD)));

                ItemStack rtpTool = new ItemStack(Material.ENDER_PEARL);
                rtpTool.setItemMeta(applyDisplayName(rtpTool, Component.text("Random Teleport", TextColor.color(255,215,0), TextDecoration.BOLD)));

                ItemStack nvTool = new ItemStack(Material.POTION);
                nvTool.setItemMeta(applyDisplayName(nvTool, Component.text("Night Vision", TextColor.color(255,215,0), TextDecoration.BOLD)));

                ItemStack vanishTool = new ItemStack(Material.ENDER_EYE);
                vanishTool.setItemMeta(applyDisplayName(vanishTool, Component.text("Vanish", TextColor.color(255,215,0), TextDecoration.BOLD)));

                ItemStack onlinePlayersTool = new ItemStack(Material.PLAYER_HEAD);
                onlinePlayersTool.setItemMeta(applyDisplayName(onlinePlayersTool, Component.text("View Online Players", TextColor.color(255,215,0), TextDecoration.BOLD)));

                // swap out player's inventory with the staff mode inventory

                p.getInventory().clear();
                p.getInventory().addItem(freezeTool, invSeeTool, rtpTool, nvTool, vanishTool, onlinePlayersTool);

                p.getPersistentDataContainer().set(new NamespacedKey(VindicterraStaffUtils.getPlugin(), "inStaffMode"), PersistentDataType.BOOLEAN, true);
            }
            return true;
        }
        return false;
    }

    public static ItemMeta applyDisplayName(ItemStack item, Component name) {
        ItemMeta meta = item.getItemMeta();

        meta.displayName(name);
        item.setItemMeta(meta);

        return meta;
    }
}

