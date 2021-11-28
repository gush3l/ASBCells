package me.gushel.askyblockcells.Commands;

import me.gushel.askyblockcells.ASkyBlockCells;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ASBCmd implements CommandExecutor {
    @SuppressWarnings( "deprecation" )
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = ASkyBlockCells.getInstance().getConfig();
        Player player = (Player) sender;
        long minsplayed = (player.getStatistic(Statistic.PLAY_ONE_TICK)/20)/60;
        Economy economy = ASkyBlockCells.getInstance().getEconomy();
        int celllevel = config.getInt("cells."+player.getName()+".level");
        if (args.length == 1){
            if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("asbcells.reload")){
                ASkyBlockCells.getInstance().reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("options.messages.reload")));
                return true;
            }
            if (args[0].equalsIgnoreCase("debug")){
                sender.sendMessage("Time Played: "+ minsplayed);
                sender.sendMessage("Money: "+ economy.getBalance(player.getName()));
                sender.sendMessage("Cell Level: "+ celllevel);
                sender.sendMessage("Req. Level 1: MONEY: "+economy.has(player, Double.parseDouble(config.getString("options.level-1.price")))+" TIME: "+ !(Integer.parseInt(Long.toString(minsplayed)) < config.getInt("options.level-1.minutes-played")));
                sender.sendMessage("Req. Level 2: MONEY: "+economy.has(player, Double.parseDouble(config.getString("options.level-2.price")))+" TIME: "+ !(Integer.parseInt(Long.toString(minsplayed)) < config.getInt("options.level-2.minutes-played")));
                return true;
            }
        }
    return true;
    }
}
