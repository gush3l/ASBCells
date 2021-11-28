package me.gushel.askyblockcells.Events;

import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.wasteofplastic.askyblock.ASkyBlockAPI;
import com.wasteofplastic.askyblock.events.IslandLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

public class RemoveMemberCell implements Listener {

    @EventHandler
    public void onAddMember(IslandLeaveEvent event) {
        Player player = Bukkit.getPlayer(event.getIslandOwner());
        Player member = Bukkit.getPlayer(event.getPlayer());
        RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
        RegionManager regions = container.get(ASkyBlockAPI.getInstance().getIslandWorld());
        assert regions != null;
        Objects.requireNonNull(regions.getRegion(player.getName())).getMembers().removePlayer(member.getName());
    }

}
