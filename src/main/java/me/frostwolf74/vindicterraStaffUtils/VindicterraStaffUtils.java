package me.frostwolf74.vindicterraStaffUtils;

import me.frostwolf74.vindicterraStaffUtils.commands.*;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;


public final class VindicterraStaffUtils extends JavaPlugin {
    private static VindicterraStaffUtils plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getCommand("staff").setExecutor(new StaffModeCommand());
        getCommand("staffchat").setExecutor(new StaffChatCommand());
        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("inventorysee").setExecutor(new InventorySeeCommand());
    }

    public static VindicterraStaffUtils getPlugin() {
        return plugin;
    }
}
