package me.frostwolf74.vindicterraStaffUtils.listeners;

import com.destroystokyo.paper.MaterialSetTag;
import me.frostwolf74.vindicterraStaffUtils.VindicterraStaffUtils;
import me.frostwolf74.vindicterraStaffUtils.commands.FreezeCommand;
import me.frostwolf74.vindicterraStaffUtils.commands.VanishCommand;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;
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

        if(Boolean.TRUE.equals(e.getPlayer().getPersistentDataContainer().get(new NamespacedKey(VindicterraStaffUtils.getPlugin(), "inStaffMode"), PersistentDataType.BOOLEAN))){
            if(Objects.equals(e.getPlayer().getInventory().getItemInMainHand().getType(), Material.POTION)){ // NV

            }
            else if(Objects.equals(e.getPlayer().getInventory().getItemInMainHand().getType(), Material.ICE)){ // freeze
                if(e.getInteractionPoint() == null){
                    return;
                }

                RayTraceResult trace = new RayTraceResult(e.getInteractionPoint().toVector());
                if(trace.getHitEntity() instanceof Player p){
                    FreezeCommand.freezePlayer(p);
                }
                else if(trace.getHitEntity() != null){
                    e.getPlayer().sendMessage("Trace complete, no entity found");
                }
                else if(trace.getHitEntity() instanceof Entity){
                    e.getPlayer().sendMessage("Trace complete, entity found, no player found");
                }
            }
            else if(Objects.equals(e.getPlayer().getInventory().getItemInMainHand().getType(), Material.ENDER_PEARL)){ // RTP
                Collection<?> onlinePlayers = e.getPlayer().getServer().getOnlinePlayers();
                Random rand = new Random();
                rand.setSeed(System.currentTimeMillis());

                e.getPlayer().sendMessage(String.valueOf(rand.nextInt(onlinePlayers.size())));
                e.getPlayer().teleport((Player) onlinePlayers.toArray()[rand.nextInt(onlinePlayers.size())]);
            }
            else if(Objects.equals(e.getPlayer().getInventory().getItemInMainHand().getType(), Material.ENDER_EYE)){
                VanishCommand.vanishPlayer(e.getPlayer());
            }
            else if(Objects.equals(e.getPlayer().getInventory().getItemInMainHand().getType(), Material.PLAYER_HEAD)){ // online players
                Inventory onlinePlayersInventory = Bukkit.createInventory(null, 9 * 6, "Online Players");

                e.getPlayer().getServer().getOnlinePlayers().forEach(onlinePlayer -> {
                            ItemStack playersHead = new ItemStack(Material.PLAYER_HEAD, 1);
                            SkullMeta playersHeadMeta = (SkullMeta) playersHead.getItemMeta();

                            playersHeadMeta.setOwningPlayer(onlinePlayer);
                            playersHead.setItemMeta(playersHeadMeta);

                            onlinePlayersInventory.addItem(playersHead);
                        }
                );

                e.getPlayer().openInventory(onlinePlayersInventory);
            }

            e.setCancelled(true); // prevent player from using the item
        }



    }
}
