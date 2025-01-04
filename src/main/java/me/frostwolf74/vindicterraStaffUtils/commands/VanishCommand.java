package me.frostwolf74.vindicterraStaffUtils.commands;

import me.frostwolf74.vindicterraStaffUtils.VindicterraStaffUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.units.qual.N;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player p){ // TODO implement supervanish later
            if(!(p.hasPermission("VSU.freeze"))) return true;

            if(Boolean.TRUE.equals(p.getPersistentDataContainer().get(new NamespacedKey(VindicterraStaffUtils.getPlugin(), "isVanished"), PersistentDataType.BOOLEAN))){
                p.getPersistentDataContainer().set(new NamespacedKey(VindicterraStaffUtils.getPlugin(), "isVanished"), PersistentDataType.BOOLEAN, false);

                if(strings.length == 0){ // vanishes self
                    vanishPlayer(p);
                }
                else{ // vanishes selected player
                    for(Player onlinePlayer : Bukkit.getOnlinePlayers()){
                        if(onlinePlayer.name().toString().equals(strings[0])){
                            continue;
                        }

                        onlinePlayer.hidePlayer(VindicterraStaffUtils.getPlugin(), p);
                    }
                }
            }
            else{
                if(strings.length == 0){ // vanishes self
                    vanishPlayer(p);
                }
                else{ // vanishes selected player
                    if(p.getServer().getPlayer(strings[0]) == null){
                        p.sendMessage(Component.text("Player not found", TextColor.color(255, 0 , 0)));
                        return true;
                    }

                    for(Player onlinePlayer : Bukkit.getOnlinePlayers()){
                        if(onlinePlayer.name().toString().equals(strings[0])){
                            continue;
                        }

                        onlinePlayer.hidePlayer(VindicterraStaffUtils.getPlugin(), p.getServer().getPlayer(strings[0]));
                    }
                }

                p.getPersistentDataContainer().set(new NamespacedKey(VindicterraStaffUtils.getPlugin(), "isVanished"), PersistentDataType.BOOLEAN, true);
            }

            return true;
        }

        return false;
    }

    public static void vanishPlayer(Player p){
        for(Player onlinePlayer : Bukkit.getOnlinePlayers()){
            if(onlinePlayer.equals(p)){
                continue;
            }

            onlinePlayer.hidePlayer(VindicterraStaffUtils.getPlugin(), p);
        }
    }
}
