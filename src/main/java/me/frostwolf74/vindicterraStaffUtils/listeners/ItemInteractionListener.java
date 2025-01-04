package me.frostwolf74.vindicterraStaffUtils.listeners;

import me.frostwolf74.vindicterraStaffUtils.VindicterraStaffUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemInteractionListener implements Listener {
    @EventHandler
    public void onItemInteraction(PlayerInteractEvent e) {
        if(e instanceof Player p){
            if(!(e.getAction().isRightClick())) return;

            p.sendMessage("player item interaction event recorded");
            if(e.getItem().getItemMeta() == null){
                return;
            }

            // staff items
            if(Boolean.TRUE.equals(p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(VindicterraStaffUtils.getPlugin(), "isStaffUtilityItem"), PersistentDataType.BOOLEAN))){
                p.sendMessage("effect applied");
                PotionEffect invisPot = new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, false, false);
                p.addPotionEffect(invisPot);
            }
        }
    }
}