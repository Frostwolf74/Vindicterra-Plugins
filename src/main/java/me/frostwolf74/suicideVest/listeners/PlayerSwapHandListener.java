package me.frostwolf74.suicideVest.listeners;

import me.frostwolf74.suicideVest.SuicideVest;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class PlayerSwapHandListener implements Listener {

    @EventHandler
    public void onPlayerSwapHandEvent(PlayerSwapHandItemsEvent e){
        ItemStack itemSlot = e.getPlayer().getInventory().getChestplate();
        FileConfiguration config = SuicideVest.getPlugin().getConfig();

        if(itemSlot == null) {
            return;
        }
        if(Boolean.TRUE.equals(itemSlot.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(SuicideVest.getPlugin(), "isSuicideVest"), PersistentDataType.BOOLEAN))
                && (isPlayerCooldownTimeUp(e.getPlayer(), config))) {
            for(int j = 0; j < 6; ++j){ // 6 x 3 = 3 end crystals
                e.getPlayer().getLocation().getWorld().createExplosion(e.getPlayer().getLocation(), 3, false, true);
            }

            config.set("suicideVestCooldown." + e.getPlayer().getUniqueId() + ".detonationTime", System.currentTimeMillis());
            SuicideVest.getPlugin().saveConfig();
        }
    }

    public boolean isPlayerCooldownTimeUp(Player p, FileConfiguration config){
        double timeElapsed = System.currentTimeMillis() - (config.getDouble("suicideVestCooldown." + p.getUniqueId() + ".detonationTime"));
        if(config.get("suicideVestCooldown." + p.getUniqueId() + ".detonationTime") == null){
            return true; // pass check if configuration doesn't exist yet to allow it to be written
        }
        else if (timeElapsed > (1000 * 43200) || p.isOp()){ // 12 hrs, op bypass
            return true;
        }
        else{
            p.sendMessage(Component.text("You must wait another " + String.format("%.2f", ((1000 * 43200) - timeElapsed) / (1000 * 60 * 60)) + " hours before using this again!", TextColor.color(255, 0, 0), TextDecoration.BOLD));
            return false;
        }
    }
}
