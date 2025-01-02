package me.frostwolf74.suicideVest;

import me.frostwolf74.suicideVest.listeners.PlayerSwapHandListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public final class SuicideVest extends JavaPlugin implements Listener{
    private static SuicideVest plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getServer().getPluginManager().registerEvents(new PlayerSwapHandListener(), this);

        ItemStack suicideVest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemMeta suicideVestMeta = suicideVest.getItemMeta();

        List<Component> lore = new ArrayList<>(); // lore and name with color
        lore.add(0, Component.text("Press F while equipped"));
        lore.add(1, Component.text("to detonate."));
        suicideVestMeta.displayName(Component.text("Suicide Vest", TextColor.color(255, 0, 0), TextDecoration.BOLD));
        suicideVestMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
        suicideVestMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ARMOR_TRIM);
        suicideVestMeta.lore(lore);

        suicideVest.setItemMeta(suicideVestMeta);

        ArmorMeta Ameta = (ArmorMeta) suicideVest.getItemMeta(); // trims
        TrimPattern pattern = TrimPattern.COAST;
        TrimMaterial material = TrimMaterial.GOLD;
        ArmorTrim trim = new ArmorTrim(material, pattern);
        Ameta.setTrim(trim);

        suicideVest.setItemMeta(Ameta);

        LeatherArmorMeta LAmeta = (LeatherArmorMeta) suicideVest.getItemMeta(); // material color
        LAmeta.setColor(Color.RED);

        suicideVest.setItemMeta(LAmeta);

        ItemMeta vestData = suicideVest.getItemMeta();
        vestData.getPersistentDataContainer().set(new NamespacedKey(this, "isSuicideVest"), PersistentDataType.BOOLEAN, true);
        suicideVest.setItemMeta(vestData);

        ShapedRecipe suicideVestRecipe = new ShapedRecipe(new NamespacedKey(this, "suicideVest"), suicideVest);
        suicideVestRecipe.shape("#!#",
                                "!$!",
                                "@%@");
        suicideVestRecipe.setIngredient('!', Material.END_CRYSTAL);
        suicideVestRecipe.setIngredient('#', Material.TNT);
        suicideVestRecipe.setIngredient('$', Material.LEATHER_CHESTPLATE);
        suicideVestRecipe.setIngredient('@', Material.REDSTONE);
        suicideVestRecipe.setIngredient('%', Material.GOLD_INGOT);

        Bukkit.addRecipe(suicideVestRecipe);
    }

    public static SuicideVest getPlugin() {
        return plugin;
    }
}
