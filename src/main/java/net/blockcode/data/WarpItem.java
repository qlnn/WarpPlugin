package net.blockcode.data;

import me.cocos.menu.builder.impl.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public final class WarpItem {

    private final ItemStack itemStack;
    private final int slot;

    private final String world;

    private final double x;
    private final double y;
    private final double z;

    private final String permission;

    public WarpItem(ConfigurationSection configurationSection) {
        this.itemStack = ItemBuilder.from(Material.valueOf(configurationSection.getString("material")))
                .withItemName(configurationSection.getString("title"))
                .withLore(configurationSection.getStringList("lore")).build();
        this.slot = configurationSection.getInt("slot");
        this.world = configurationSection.getString("world");
        this.x = configurationSection.getDouble("x");
        this.y = configurationSection.getDouble("y");
        this.z = configurationSection.getDouble("z");
        this.permission = configurationSection.getString("permission");
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public String getPermission() {
        return permission;
    }

    public int getSlot() {
        return slot;
    }

    public String getWorld() {
        return world;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
