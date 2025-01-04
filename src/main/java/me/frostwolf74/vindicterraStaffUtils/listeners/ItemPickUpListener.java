package me.frostwolf74.vindicterraStaffUtils.listeners;

import io.papermc.paper.event.player.PlayerPickItemEvent;
import me.frostwolf74.vindicterraStaffUtils.VindicterraStaffUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.units.qual.N;

import javax.naming.Name;

public class ItemPickUpListener implements Listener {
    @EventHandler
    public void onItemPickUpEvent(PlayerAttemptPickupItemEvent e){
        // prevent players in staff mode from picking up items
        if(Boolean.TRUE.equals(e.getPlayer().getPersistentDataContainer().get(new NamespacedKey(VindicterraStaffUtils.getPlugin(), "inStaffMode"), PersistentDataType.BOOLEAN))){
            e.setCancelled(true);
        }
    }
}
