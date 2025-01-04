package me.frostwolf74.vindicterraStaffUtils.listeners;

import io.papermc.paper.event.player.PlayerPickItemEvent;
import me.frostwolf74.vindicterraStaffUtils.VindicterraStaffUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;

public class ItemPickUpListener implements Listener {
    @EventHandler
    public void onItemPickUpEvent(PlayerPickItemEvent e){
        if(Boolean.TRUE.equals(e.getPlayer().getPersistentDataContainer().get(new NamespacedKey(VindicterraStaffUtils.getPlugin(), "inStaffMode"), PersistentDataType.BOOLEAN))){
            e.setCancelled(true);

        }
    }
}
