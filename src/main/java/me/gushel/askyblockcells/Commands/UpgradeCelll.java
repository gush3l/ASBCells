package me.gushel.askyblockcells.Commands;

import com.sk89q.worldedit.*;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.wasteofplastic.askyblock.ASkyBlockAPI;
import me.gushel.askyblockcells.ASkyBlockCells;
import me.gushel.askyblockcells.Cuboid;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import redempt.crunch.Crunch;

import java.util.Objects;

public class UpgradeCelll implements CommandExecutor {
    @SuppressWarnings( "deprecation" )
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("[ASkyBlockCells] This command can be send only in game!");
            return true;
        }
        FileConfiguration config = ASkyBlockCells.getInstance().getConfig();
        Player player = (Player) sender;
        Economy economy = ASkyBlockCells.getInstance().getEconomy();
        Location ishome = ASkyBlockAPI.getInstance().getHomeLocation(player.getUniqueId());
        World isworld = Bukkit.getWorld(config.getString("options.cell-world"));
        int celllevel = config.getInt("cells."+player.getName()+".level");
        long minsplayed = (player.getStatistic(Statistic.PLAY_ONE_TICK)/20)/60;
        double x = ishome.getBlockX();
        double y = ishome.getBlockY();
        double z = ishome.getBlockZ();
        double lvl1wallsloc1x = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-1.coords.walls.loc1.x").replace("%x%",String.valueOf(x)))));
        double lvl1wallsloc1y = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-1.coords.walls.loc1.y").replace("%y%",String.valueOf(y)))));
        double lvl1wallsloc1z = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-1.coords.walls.loc1.z").replace("%z%",String.valueOf(z)))));
        double lvl1regionloc1x = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-1.coords.region.loc1.x").replace("%x%",String.valueOf(x)))));
        double lvl1regionloc1y = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-1.coords.region.loc1.y").replace("%y%",String.valueOf(y)))));
        double lvl1regionloc1z = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-1.coords.region.loc1.z").replace("%z%",String.valueOf(z)))));
        double lvl1wallsloc2x = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-1.coords.walls.loc2.x").replace("%x%",String.valueOf(x)))));
        double lvl1wallsloc2y = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-1.coords.walls.loc2.y").replace("%y%",String.valueOf(y)))));
        double lvl1wallsloc2z = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-1.coords.walls.loc2.z").replace("%z%",String.valueOf(z)))));
        double lvl1regionloc2x = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-1.coords.region.loc2.x").replace("%x%",String.valueOf(x)))));
        double lvl1regionloc2y = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-1.coords.region.loc2.y").replace("%y%",String.valueOf(y)))));
        double lvl1regionloc2z = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-1.coords.region.loc2.z").replace("%z%",String.valueOf(z)))));
        double lvl2wallsloc1x = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-2.coords.walls.loc1.x").replace("%x%",String.valueOf(x)))));
        double lvl2wallsloc1y = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-2.coords.walls.loc1.y").replace("%y%",String.valueOf(y)))));
        double lvl2wallsloc1z = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-2.coords.walls.loc1.z").replace("%z%",String.valueOf(z)))));
        double lvl2regionloc1x = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-2.coords.region.loc1.x").replace("%x%",String.valueOf(x)))));
        double lvl2regionloc1y = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-2.coords.region.loc1.y").replace("%y%",String.valueOf(y)))));
        double lvl2regionloc1z = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-2.coords.region.loc1.z").replace("%z%",String.valueOf(z)))));
        double lvl2wallsloc2x = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-2.coords.walls.loc2.x").replace("%x%",String.valueOf(x)))));
        double lvl2wallsloc2y = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-2.coords.walls.loc2.y").replace("%y%",String.valueOf(y)))));
        double lvl2wallsloc2z = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-2.coords.walls.loc2.z").replace("%z%",String.valueOf(z)))));
        double lvl2regionloc2x = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-2.coords.region.loc2.x").replace("%x%",String.valueOf(x)))));
        double lvl2regionloc2y = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-2.coords.region.loc2.y").replace("%y%",String.valueOf(y)))));
        double lvl2regionloc2z = Double.parseDouble(String.valueOf(Crunch.compileExpression(config.getString("options.level-2.coords.region.loc2.z").replace("%z%",String.valueOf(z)))));
        if (celllevel == 0){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("options.messages.no-cell")));
            return true;
        }
        if (celllevel == 1){
            if (minsplayed < config.getInt("options.level-1.minutes-played")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("options.messages.not-minutes-level-1")
                        .replace("%minutes%",String.valueOf(config.getInt("options.level-1.minutes-played")))
                        .replace("%played%",String.valueOf(minsplayed))));
                return true;
            }
            if (!economy.has(player, Double.parseDouble(config.getString("options.level-1.price")))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("options.messages.not-money-level-1")
                        .replace("%money-needed%",String.valueOf(config.getInt("options.level-1.price")))
                        .replace("%money%",String.valueOf(economy.getBalance(player.getName())))));
                return true;
            }
            Location loc1 = new Location(isworld,lvl1wallsloc1x,lvl1wallsloc1y,lvl1wallsloc1z);
            Location loc2 = new Location(isworld,lvl1wallsloc2x,lvl1wallsloc2y,lvl1wallsloc2z);
            BlockVector min = new BlockVector(lvl1regionloc1x, lvl1regionloc1y, lvl1regionloc1z);
            BlockVector max = new BlockVector(lvl1regionloc2x, lvl1regionloc2y, lvl1regionloc2z);
            ProtectedRegion region = new ProtectedCuboidRegion(player.getName(), min, max);
            RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
            RegionManager regions = container.get(isworld);
            assert regions != null;
            DefaultDomain members = Objects.requireNonNull(regions.getRegion(player.getName())).getMembers();
            regions.removeRegion(player.getName());
            regions.addRegion(region);
            region.getMembers().addAll(members);
            region.setFlag(DefaultFlag.BLOCK_BREAK, StateFlag.State.ALLOW);
            region.setFlag(DefaultFlag.BLOCK_PLACE, StateFlag.State.ALLOW);
            region.setFlag(DefaultFlag.USE, StateFlag.State.ALLOW);
            region.setFlag(DefaultFlag.INVINCIBILITY, StateFlag.State.ALLOW);
            try {
                regions.save();
            } catch (StorageException e) {
                e.printStackTrace();
            }
            Cuboid cuboid = new Cuboid(loc1,loc2);
            for (Block block : cuboid){
                for (String blockcfg : config.getStringList("options.level-1.wall-materials")) {
                    Material blockmat = Material.matchMaterial(blockcfg);
                    if (block.getType() == blockmat) {
                        isworld.spawnParticle(Particle.BLOCK_CRACK,block.getLocation(),10, 1, 0.1, 0.1, 0.1,block.getState().getData());
                        isworld.playSound(block.getLocation(),Sound.BLOCK_STONE_BREAK,1,1);
                        block.setType(Material.AIR);
                    }
                }
            }
            config.set("cells."+player.getName()+".level",2);
            economy.withdrawPlayer(player.getName(), config.getDouble("options.level-1.price"));
            ASkyBlockCells.getInstance().saveConfig();
            ASkyBlockCells.getInstance().reloadConfig();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("options.messages.cell-upgraded-level-1")));
            return true;
        }
        if (celllevel == 2){
            if (minsplayed < config.getInt("options.level-2.minutes-played")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("options.messages.not-minutes-level-2")
                        .replace("%minutes%",String.valueOf(config.getInt("options.level-2.minutes-played")))
                        .replace("%played%",String.valueOf(minsplayed))));
                return true;
            }
            if (!economy.has(player, Double.parseDouble(config.getString("options.level-2.price")))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("options.messages.not-money-level-2")
                        .replace("%money-needed%",String.valueOf(config.getInt("options.level-2.price")))
                        .replace("%money%",String.valueOf(economy.getBalance(player.getName())))));
                return true;
            }
            Location loc1 = new Location(isworld,lvl2wallsloc1x,lvl2wallsloc1y,lvl2wallsloc1z);
            Location loc2 = new Location(isworld,lvl2wallsloc2x,lvl2wallsloc2y,lvl2wallsloc2z);
            BlockVector min = new BlockVector(lvl2regionloc1x, lvl2regionloc1y, lvl2regionloc1z);
            BlockVector max = new BlockVector(lvl2regionloc2x, lvl2regionloc2y, lvl2regionloc2z);
            ProtectedRegion region = new ProtectedCuboidRegion(player.getName(), min, max);
            RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
            RegionManager regions = container.get(isworld);
            assert regions != null;
            DefaultDomain members = Objects.requireNonNull(regions.getRegion(player.getName())).getMembers();
            regions.removeRegion(player.getName());
            regions.addRegion(region);
            region.getMembers().addAll(members);
            region.setFlag(DefaultFlag.BLOCK_BREAK, StateFlag.State.ALLOW);
            region.setFlag(DefaultFlag.BLOCK_PLACE, StateFlag.State.ALLOW);
            region.setFlag(DefaultFlag.USE, StateFlag.State.ALLOW);
            region.setFlag(DefaultFlag.INVINCIBILITY, StateFlag.State.ALLOW);
            try {
                regions.save();
            } catch (StorageException e) {
                e.printStackTrace();
            }
            Cuboid cuboid = new Cuboid(loc1,loc2);
            for (Block block : cuboid){
                for (String blockcfg : config.getStringList("options.level-2.wall-materials")) {
                    Material blockmat = Material.matchMaterial(blockcfg);
                    if (block.getType() == blockmat) {
                        isworld.spawnParticle(Particle.BLOCK_CRACK,block.getLocation(),10, 1, 0.1, 0.1, 0.1,block.getState().getData());
                        isworld.playSound(block.getLocation(),Sound.BLOCK_STONE_BREAK,1,1);
                        block.setType(Material.AIR);
                    }
                }
            }
            config.set("cells."+player.getName()+".level",3);
            economy.withdrawPlayer(player.getName(), config.getDouble("options.level-2.price"));
            ASkyBlockCells.getInstance().saveConfig();
            ASkyBlockCells.getInstance().reloadConfig();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("options.messages.cell-upgraded-level-2")));
            return true;
        }
        if (celllevel == 3){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("options.messages.cell-max-level")));
            return true;
        }
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("options.messages.invalid-cell")));
        return true;
    }
}
