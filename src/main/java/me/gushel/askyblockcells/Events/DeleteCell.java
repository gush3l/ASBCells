package me.gushel.askyblockcells.Events;

import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.wasteofplastic.askyblock.ASkyBlockAPI;
import com.wasteofplastic.askyblock.events.IslandPreDeleteEvent;
import me.gushel.askyblockcells.ASkyBlockCells;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DeleteCell implements Listener {

    @EventHandler
    public void onIslandDelete(IslandPreDeleteEvent event) {
        Player player = Bukkit.getPlayer(event.getIsland().getOwner());
        RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
        RegionManager regions = container.get(ASkyBlockAPI.getInstance().getIslandWorld());
        assert regions != null;
        regions.removeRegion(player.getName());
        try {
            regions.save();
        } catch (StorageException e) {
            e.printStackTrace();
        }
        FileConfiguration config = ASkyBlockCells.getInstance().getConfig();
        config.set("cells."+player.getName()+".level",0);
        ASkyBlockCells.getInstance().saveConfig();
        ASkyBlockCells.getInstance().reloadConfig();
    }
}
