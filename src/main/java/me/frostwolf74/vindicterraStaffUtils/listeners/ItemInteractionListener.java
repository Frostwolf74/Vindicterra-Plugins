package me.frostwolf74.vindicterraStaffUtils.listeners;

import me.frostwolf74.vindicterraStaffUtils.VindicterraStaffUtils;
import net.kyori.adventure.text.Component;
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
        VindicterraStaffUtils.getPlugin().getServer().getLogger().info("player item interaction event recorded");
        VindicterraStaffUtils.getPlugin().getServer().sendMessage(Component.text("player item interaction event recorded"));

        e.getPlayer().sendMessage("");
        if(!(e.getAction().isRightClick())) return;

        if(e.getItem().getItemMeta() == null){
            return;
        }

        // staff items
        if(Boolean.TRUE.equals(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(VindicterraStaffUtils.getPlugin(), "isStaffUtilityItem"), PersistentDataType.BOOLEAN))){
            e.getPlayer().sendMessage("effect applied");
            PotionEffect invisPot = new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, false, false);
            e.getPlayer().addPotionEffect(invisPot);
        }

    }
}
