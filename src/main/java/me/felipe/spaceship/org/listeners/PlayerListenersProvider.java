package me.felipe.spaceship.org.listeners;

import me.felipe.spaceship.org.RegisterProvider;
import me.felipe.spaceship.org.gui.ExplorerGui;
import me.felipe.spaceship.org.spaceship.explorer.Explorer;
import me.felipe.spaceship.org.spaceship.explorer.ExplorerType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListenersProvider implements Listener {

    public PlayerListenersProvider(RegisterProvider registerProvider) {
        Bukkit.getPluginManager().registerEvents(this, registerProvider.getPlugin());
    }

    @EventHandler
    public void onPlaceExplorer(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        if (ExplorerType.isExplorerItem(itemStack)) {
            Block block = e.getBlock();
            ExplorerType explorerType = ExplorerType.getExplorerByItem(itemStack);
            explorerType.setLocation(block.getLocation());
            System.out.println(block.getLocation());
            player.sendMessage("§cVocê colocou uma " + explorerType.getName());
        }
    }

    @EventHandler
    public void onBreakExplorer(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        Location blockLocation = block.getLocation();
        if (!ExplorerType.isExplorerByLocation(blockLocation)) return;
        e.setCancelled(true);
        ExplorerType explorer = ExplorerType.getBlockByLocation(blockLocation);
        if (player.isSneaking()) {
            if (!player.hasPermission("fspaceship.admin"));
            player.sendMessage("§cVocê removeu a " + explorer.getName());
            explorer.remove();
            block.setType(Material.AIR);
        }
    }

    @EventHandler
    public void onInteractExplorer(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Block block = e.getClickedBlock();
        Location blockLocation = block.getLocation();
        Action action = e.getAction();
        if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (!ExplorerType.isExplorerByLocation(blockLocation)) return;
            ExplorerType explorer = ExplorerType.getBlockByLocation(blockLocation);
            new ExplorerGui(player, explorer).open();
        }
    }
}
