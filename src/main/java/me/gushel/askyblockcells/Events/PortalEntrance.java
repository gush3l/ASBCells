package me.gushel.askyblockcells.Events;

import me.gushel.askyblockcells.ASkyBlockCells;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PortalEntrance implements Listener {

    @EventHandler
    public void onPortalEntrance(PlayerMoveEvent event){
        Player player = event.getPlayer();
        FileConfiguration config = ASkyBlockCells.getInstance().getConfig();
        int xc=player.getLocation().getBlockX();
        int yc=player.getLocation().getBlockY();
        int zc=player.getLocation().getBlockZ();
        int xt=event.getTo().getBlockX();
        int yt=event.getTo().getBlockY();
        int zt=event.getTo().getBlockZ();
        Material blockc = player.getWorld().getBlockAt(xc,yc,zc).getType();
        Material blockf = player.getWorld().getBlockAt(xt,yt,zt).getType();
        if (blockf == Material.END_GATEWAY && blockc != blockf){
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 2*20, 10));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),config.getString("options.portal-enter-command").replace("%player%",player.getName()));
        }
    }

}
