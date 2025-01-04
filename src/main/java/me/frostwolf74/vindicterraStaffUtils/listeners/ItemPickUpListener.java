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
        e.getPlayer().sendMessage("pick up event recorded: 1");
        if(Boolean.TRUE.equals(e.getPlayer().getPersistentDataContainer().get(new NamespacedKey(VindicterraStaffUtils.getPlugin(), "inStaffMode"), PersistentDataType.BOOLEAN))){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickUpEvent2(PlayerPickItemEvent e){
        e.getPlayer().sendMessage("pick up event recorded: 2");
        if(Boolean.TRUE.equals(e.getPlayer().getPersistentDataContainer().get(new NamespacedKey(VindicterraStaffUtils.getPlugin(), "inStaffMode"), PersistentDataType.BOOLEAN))){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickUpEvent3(PlayerPickupItemEvent e){
        e.getPlayer().sendMessage("pick up event recorded: 3");
        if(Boolean.TRUE.equals(e.getPlayer().getPersistentDataContainer().get(new NamespacedKey(VindicterraStaffUtils.getPlugin(), "inStaffMode"), PersistentDataType.BOOLEAN))){
            e.setCancelled(true);
        }
    }
}
