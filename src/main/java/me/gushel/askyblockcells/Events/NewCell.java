package me.gushel.askyblockcells.Events;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.wasteofplastic.askyblock.ASkyBlockAPI;
import com.wasteofplastic.askyblock.events.IslandNewEvent;
import me.gushel.askyblockcells.ASkyBlockCells;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NewCell implements Listener {

    @EventHandler
    public void onIslandNew(IslandNewEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = ASkyBlockCells.getInstance().getConfig();
        Bukkit.getScheduler().runTaskLater(ASkyBlockCells.getInstance(), () -> {
            Location ishome = ASkyBlockAPI.getInstance().getHomeLocation(player.getUniqueId());
            double x = ishome.getBlockX();
            double y = ishome.getBlockY();
            double z = ishome.getBlockZ();
            BlockVector min = new BlockVector(x - 2, y + 3, z);
            BlockVector max = new BlockVector(x + 2, y, z - 4);
            ProtectedRegion region = new ProtectedCuboidRegion(player.getName(), min, max);
            RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
            RegionManager regions = container.get(event.getIslandLocation().getWorld());
            assert regions != null;
            regions.addRegion(region);
            region.getMembers().addPlayer(player.getName());
            region.setFlag(DefaultFlag.BLOCK_BREAK, StateFlag.State.ALLOW);
            region.setFlag(DefaultFlag.BLOCK_PLACE, StateFlag.State.ALLOW);
            region.setFlag(DefaultFlag.USE, StateFlag.State.ALLOW);
            region.setFlag(DefaultFlag.INVINCIBILITY, StateFlag.State.ALLOW);
            try {
                regions.save();
            } catch (StorageException e) {
                e.printStackTrace();
            }
            config.set("cells."+player.getName()+".level",1);
            ASkyBlockCells.getInstance().saveConfig();
            ASkyBlockCells.getInstance().reloadConfig();
        }, 20L);
    }

}
