package me.felipe.spaceship.org.spaceship;

import me.felipe.spaceship.org.RegisterProvider;
import me.felipe.spaceship.org.config.ConfigHandler;
import me.felipe.spaceship.org.config.ConfigType;
import me.felipe.spaceship.org.spaceship.workers.Worker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SpaceShipManager {

    private RegisterProvider registerProvider;
    private Location spaceShipLocation;

    private List<Worker> workersList = new ArrayList<>();

    public SpaceShipManager(RegisterProvider registerProvider) {
        this.registerProvider = registerProvider;
        loadAllSettings();
        loadLocations();
    }

    public void loadLocations() {
        ConfigHandler file = registerProvider.getConfigManager().getFile(ConfigType.LOCATIONS);
        String spLocation = file.get().getString("SpaceShip.Location");
        if (spLocation == null || spLocation.isEmpty()) return;
        spaceShipLocation = unformattedLocation(spLocation);
    }

    public boolean hasLocation() {
        return spaceShipLocation != null;
    }

    public void setLocation(Location location) {
        spaceShipLocation = location;
        ConfigHandler file = registerProvider.getConfigManager().getFile(ConfigType.LOCATIONS);
        file.get().set("SpaceShip.Location", formattedLocation(spaceShipLocation));
        file.save();
    }

    public Location getLocation() {
        return spaceShipLocation;
    }

    public String formattedLocation(Location location) {
        return location.getX() +
                ";" + location.getY() +
                ";" + location.getZ() +
                ";" + location.getYaw() +
                ";" + location.getPitch() +
                ";" + location.getWorld().getName();
    }

    public Location unformattedLocation(String string) {
        String[] parts = string.split(";");
        double x = Double.parseDouble(parts[0]);
        double y = Double.parseDouble(parts[1]);
        double z = Double.parseDouble(parts[2]);
        float yaw = Float.parseFloat(parts[3]);
        float pitch = Float.parseFloat(parts[4]);
        World world = Bukkit.getWorld(parts[5]);
        Location location = new Location(world, x, y, z, yaw, pitch);
        return location;
    }

    public void loadAllSettings() {
        FileConfiguration config = registerProvider.getConfigManager().getFile(ConfigType.CONFIG).get();
        CompletableFuture.runAsync(() -> {
            config.getConfigurationSection("Workers").getKeys(false)
                    .forEach(work -> {
                        workersList.add(new Worker.Builder()
                                .name(config.getString("Workers." + work + ".Name"))
                                .price(config.getDouble("Workers." + work + ".Price"))
                                .build());
                        System.out.println(work);
                    });
        }).thenAccept(unused -> System.out.println("Os workers foram carregados."));
    }
}
