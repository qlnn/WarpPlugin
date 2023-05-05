package net.blockcode;

import net.blockcode.menus.WarpMenu;
import net.blockcode.service.WarpsService;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        WarpsService warpsService = new WarpsService(this.getConfig());
        new WarpMenu(warpsService, this.getConfig());
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}