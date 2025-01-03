package me.frostwolf74.vindicterraStaffUtils.commands;

import me.frostwolf74.vindicterraStaffUtils.VindicterraStaffUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player p){ // TODO implement supervanish later
            if(strings.length == 0){
                for(Player onlinePlayer : Bukkit.getOnlinePlayers()){
                    if(onlinePlayer.equals(p)){
                        continue;
                    }

                    onlinePlayer.hidePlayer(VindicterraStaffUtils.getPlugin(), p);
                }
            }
            else{
                for(Player onlinePlayer : Bukkit.getOnlinePlayers()){
                    if(onlinePlayer.name().toString().equals(strings[0])){
                        continue;
                    }

                    onlinePlayer.hidePlayer(VindicterraStaffUtils.getPlugin(), p);
                }
            }
            return true;
        }

        return false;
    }
}
