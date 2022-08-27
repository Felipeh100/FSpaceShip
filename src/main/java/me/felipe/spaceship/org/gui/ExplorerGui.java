package me.felipe.spaceship.org.gui;

import me.felipe.spaceship.org.gui.utils.Gui;
import me.felipe.spaceship.org.spaceship.explorer.ExplorerType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ExplorerGui extends Gui {

    private ExplorerType explorerType;

    public ExplorerGui(Player player, ExplorerType explorerType) {
        super(player);
        this.explorerType = explorerType;
    }

    @Override
    public String name() {
        return explorerType.getName();
    }

    @Override
    public int slots() {
        return 27;
    }

    @Override
    public void event(InventoryClickEvent e) {

    }

    @Override
    public void items() {

    }
}
