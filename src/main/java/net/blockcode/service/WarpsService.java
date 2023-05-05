package net.blockcode.service;

import net.blockcode.data.WarpItem;
import net.blockcode.menus.WarpMenu;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class WarpsService {


    private final Set<WarpItem> warpItems;

    public WarpsService(FileConfiguration fileConfiguration) {
        ConfigurationSection configurationSection = fileConfiguration.getConfigurationSection("warps");
        this.warpItems = configurationSection.getKeys(false)
                .stream()
                .map(configurationSection::getConfigurationSection)
                .filter(Objects::nonNull)
                .map(WarpItem::new).collect(Collectors.toSet());
    }

    public Set<WarpItem> getWarpItems() {
        return warpItems;
    }
}