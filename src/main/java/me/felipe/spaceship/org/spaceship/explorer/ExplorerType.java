package me.felipe.spaceship.org.spaceship.explorer;

import me.felipe.spaceship.org.utils.ItemFactory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Optional;

public enum ExplorerType {

    TEST()
    ORES("§8Exploradora de minérios", Material.COAL_ORE),
    ANIMALS("§5Exploradora de animais", Material.BEACON),
    TECH("§4Exploradora de tecnologias", Material.FURNACE),
    RESOURCES("§bExploradora de recursos", Material.HAY_BLOCK);

    private Explorer explorer;

    ExplorerType(Explorer explorer) {
        this.explorer = explorer;
    }

    public void remove() {
        setLocation(null);
    }

    public static boolean isExplorerItem(ItemStack itemStack) {
        return Arrays.stream(values())
                .anyMatch(explorerType -> explorerType.getExplorerItem().isSimilar(itemStack));
    }

    public static ExplorerType getExplorerByItem(ItemStack itemStack) {
        Optional<ExplorerType> type = Arrays.stream(values())
                .filter(explorerType -> explorerType.getExplorerItem().isSimilar(itemStack))
                .findFirst();
        return type.get();
    }

    public static boolean existType(String value) {
        return Arrays.stream(values())
                .anyMatch(explorerType -> explorerType.name().equals(value));
    }

    public static ExplorerType get(String value) {
        Optional<ExplorerType> type = Arrays.stream(values())
                .filter(explorerType -> explorerType.name().equals(value))
                .findFirst();
        return type.get();
    }

    public static boolean isExplorerByLocation(Location location) {
        return Arrays.stream(values())
                .filter(explorerType -> explorerType.getLocation() != null)
                .anyMatch(explorerType -> explorerType.getLocation().equals(location));
    }

    public static ExplorerType getBlockByLocation(Location location) {
        Optional<ExplorerType> type = Arrays.stream(values())
                .filter(explorerType -> explorerType.getLocation() != null && explorerType.getLocation().equals(location))
                .findFirst();
        return type.get();
    }
}
