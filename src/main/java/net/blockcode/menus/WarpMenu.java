package net.blockcode.menus;

import me.cocos.menu.Menu;
import me.cocos.menu.commands.Command;
import me.cocos.menu.helpers.ChatHelper;
import me.cocos.menu.helpers.GuiHelper;
import net.blockcode.data.WarpItem;
import net.blockcode.service.WarpsService;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Command(name = "warp", aliases = {"warpy", "warps"})
public final class WarpMenu extends Menu {

    private final WarpsService warpsService;

    private final String permission;

    private final String success;

    public WarpMenu(WarpsService warpsService, FileConfiguration config) {
        super(ChatHelper.coloredText(config.getString("gui.title")), config.getInt("gui.size"));
        this.warpsService = warpsService;
        this.permission = config.getString("messages.permission");
        this.success = config.getString("messages.success");
        GuiHelper.border(this.getInventory(), new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        update();
    }

    @Override
    public void update() {
        setOnInventoryClick(((inventoryClickEvent, player) -> inventoryClickEvent.setCancelled(true)));
        for (WarpItem warpItem : warpsService.getWarpItems()) {
            this.setItem(warpItem.getSlot(), warpItem.getItemStack(), inventoryClickEvent -> {
                HumanEntity player = inventoryClickEvent.getWhoClicked();
                if (warpItem.getPermission() != null && player.hasPermission(warpItem.getPermission())) {
                    player.teleport(new Location(Bukkit.getWorld(warpItem.getWorld()), warpItem.getX(), warpItem.getY(), warpItem.getZ()));
                    player.sendMessage(ChatHelper.coloredText(success));
                } else {
                    inventoryClickEvent.setCancelled(true);
                    player.closeInventory();
                    player.sendMessage(ChatHelper.coloredText(permission));
                }
            });
        }
    }
}
