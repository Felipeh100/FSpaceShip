package me.felipe.spaceship.org;

import org.bukkit.plugin.java.JavaPlugin;

public class FSpaceShip extends JavaPlugin {

    public void onEnable() {
        register();
    }

    public void onDisable() {
    }

    public void register() {
        new RegisterProvider(this);
    }
}
