package me.frostwolf74.vindicterraStaffUtils.listeners;

import me.frostwolf74.vindicterraStaffUtils.VindicterraStaffUtils;
import me.frostwolf74.vindicterraStaffUtils.commands.FreezeCommand;
import me.frostwolf74.vindicterraStaffUtils.commands.VanishCommand;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.RayTraceResult;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;

public class ItemInteractionListener implements Listener {
    @EventHandler
    public void onItemInteraction(PlayerInteractEvent e) {
        if(!(e.getAction().isRightClick())) return;

        if(e.getItem() == null){
            return;
        }

        // staff items
        if(Boolean.TRUE.equals(e.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(VindicterraStaffUtils.getPlugin(), "isStaffUtilityItem"), PersistentDataType.BOOLEAN))){
            if(Objects.equals(e.getItem(), new ItemStack(Material.POTION))){
                e.getPlayer().sendMessage("nv interaction: 1"); // TODO debug message
            }
            if(Objects.equals(e.getPlayer().getInventory().getItem(e.getHand()), new ItemStack(Material.POTION))){
                e.getPlayer().sendMessage("nv interaction: 2"); // TODO debug message
            }
            if(Objects.equals(e.getPlayer().getInventory().getItemInMainHand(), new ItemStack(Material.POTION))){
                VanishCommand.vanishPlayer(e.getPlayer());
                e.getPlayer().sendMessage("nv interaction: 3"); // TODO debug message
            }
            else if(Objects.equals(e.getPlayer().getInventory().getItemInMainHand(), new ItemStack(Material.ICE))){
                e.getPlayer().sendMessage("freeze interaction"); // TODO debug message
                if(e.getInteractionPoint() == null){
                    return;
                }

                RayTraceResult trace = new RayTraceResult(e.getInteractionPoint().toVector());
                if(trace.getHitEntity() instanceof Player p){
                    FreezeCommand.freezePlayer(p);
                }
            }
            else if(Objects.equals(e.getPlayer().getInventory().getItemInMainHand(), new ItemStack(Material.ENDER_PEARL))){
                e.getPlayer().sendMessage("rtp interaction"); // TODO debug message
                Collection<?> onlinePlayers = e.getPlayer().getServer().getOnlinePlayers();
                Random rand = new Random();

                e.getPlayer().sendMessage(String.valueOf(rand.nextInt(onlinePlayers.size())));
                e.getPlayer().teleport((Player) onlinePlayers.toArray()[rand.nextInt(onlinePlayers.size())]);
            }

            e.setCancelled(true); // prevent player from using the item
        }



    }
}
