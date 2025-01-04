package me.frostwolf74.vindicterraStaffUtils;

import me.frostwolf74.vindicterraStaffUtils.commands.*;
import me.frostwolf74.vindicterraStaffUtils.listeners.ItemDropListener;
import me.frostwolf74.vindicterraStaffUtils.listeners.ItemInteractionEvent;
import me.frostwolf74.vindicterraStaffUtils.listeners.MenuClickEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class VindicterraStaffUtils extends JavaPlugin {
    private static VindicterraStaffUtils plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getCommand("staff").setExecutor(new StaffModeCommand());
        getCommand("staffchat").setExecutor(new StaffChatCommand());
        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("inventorysee").setExecutor(new InventorySeeCommand());
        getCommand("vanish").setExecutor(new VanishCommand());

        getServer().getPluginManager().registerEvents(new ItemInteractionEvent(), this);
        getServer().getPluginManager().registerEvents(new MenuClickEventListener(), this);
        getServer().getPluginManager().registerEvents(new ItemDropListener(), this);
    }

    public static VindicterraStaffUtils getPlugin() {
        return plugin;
    }
}