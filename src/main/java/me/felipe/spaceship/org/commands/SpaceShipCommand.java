package me.felipe.spaceship.org.commands;

import me.felipe.spaceship.org.RegisterProvider;
import me.felipe.spaceship.org.spaceship.SpaceShipManager;
import me.felipe.spaceship.org.spaceship.explorer.Explorer;
import me.felipe.spaceship.org.spaceship.explorer.ExplorerType;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpaceShipCommand implements CommandExecutor {

    private SpaceShipManager spaceShipManager;

    public SpaceShipCommand(RegisterProvider registerProvider) {
        this.spaceShipManager = registerProvider.getSpaceShipManager();
        registerProvider.getPlugin().getCommand("spaceship").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) return true;

        Player player = (Player) commandSender;

        if (strings.length == 0) {
            if (!spaceShipManager.hasLocation()) {
                player.sendMessage("§cO spawn da nave ainda não foi setado.");
                return true;
            }
            player.teleport(spaceShipManager.getLocation());
            player.sendMessage("§eVocê foi teleportado para a nave! ");
            return true;
        }

        String argument = strings[0].toLowerCase();
        if (!(argument.equals("help") || argument.equals("setspawn") || argument.equals("explorer"))) {
            player.sendMessage("§cArgumento incorreto, utilize: '/sp help'. ");
            return true;
        }
        if (argument.equals("help")) {
            player.sendMessage("");
            player.sendMessage(" §c§lREDE WARLOCK §8- §7NAVE ");
            player.sendMessage("");
            player.sendMessage(" §e/nave §8- §7para teleportar até a nave. ");
            if (player.hasPermission("fspaceship.admin")) {
                player.sendMessage("");
                player.sendMessage(" §e/sp setspawn §8- §7para setar o spawn da nave. ");
            }
            player.sendMessage("");
            return true;

        } else if (argument.equals("setspawn")) {
            if (!player.hasPermission("fspaceship.admin")) {
                player.sendMessage("§cVocê não possui permissão. ");
                return true;
            }
            Location location = player.getLocation();
            spaceShipManager.setLocation(location);
            player.sendMessage("§aO spawn da nave foi setado com sucesso. ");
        }else if (argument.equals("explorer")) {
            if (!player.hasPermission("fspaceship.admin")) {
                player.sendMessage("§cVocê não possui permissão. ");
                return true;
            }
            if (strings.length < 2) {
                player.sendMessage("§cUtilize: '/sp explorer (tipo)'.");
                return true;
            }
            if (!ExplorerType.existType(strings[1].toUpperCase())) {
                player.sendMessage("§cEsse tipo de exploradora não existe.");
                return true;
            }
            ExplorerType explorerType = ExplorerType.get(strings[1].toUpperCase());
            player.getInventory().addItem(explorerType.getExplorerItem());
        }
        return false;
    }
}
