package me.felipe.spaceship.org;

import lombok.Getter;
import me.felipe.spaceship.org.commands.SpaceShipCommand;
import me.felipe.spaceship.org.config.ConfigManager;
import me.felipe.spaceship.org.gui.listener.GuiListeners;
import me.felipe.spaceship.org.listeners.PlayerListenersProvider;
import me.felipe.spaceship.org.spaceship.SpaceShipManager;

public class RegisterProvider {

    @Getter
    private FSpaceShip plugin;
    @Getter
    private ConfigManager configManager;
    @Getter
    private SpaceShipManager spaceShipManager;

    public RegisterProvider(FSpaceShip plugin) {
        this.plugin = plugin;
        this.configManager = new ConfigManager();
        this.configManager.loadFiles(this);
        this.spaceShipManager = new SpaceShipManager(this);
        // LISTENERS
        new GuiListeners(this);
        new PlayerListenersProvider(this);
        // COMMANDS
        new SpaceShipCommand(this);
    }
}
