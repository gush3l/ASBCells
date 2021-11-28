package me.gushel.askyblockcells;

import me.gushel.askyblockcells.Commands.ASBCmd;
import me.gushel.askyblockcells.Commands.UpgradeCelll;
import me.gushel.askyblockcells.Events.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class ASkyBlockCells extends JavaPlugin implements Listener, CommandExecutor {

    private static ASkyBlockCells plugin;
    private Economy econ;
    boolean wg=true,we=true,asb=true,vault=true;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new NewCell(), this);
        getServer().getPluginManager().registerEvents(new DeleteCell(), this);
        getServer().getPluginManager().registerEvents(new PortalEntrance(), this);
        getServer().getPluginManager().registerEvents(new AddMemberCell(), this);
        getServer().getPluginManager().registerEvents(new RemoveMemberCell(), this);
        saveDefaultConfig();
        Config.setup();
        Config.get().options().copyDefaults(true);
        Config.save();
        this.getCommand("cellupgrade").setExecutor(new UpgradeCelll());
        this.getCommand("ASkyBlockCells").setExecutor(new ASBCmd());
        plugin = this;
        if (Bukkit.getPluginManager().getPlugin("WorldGuard") == null) {
            wg = false;
            System.out.println("[ASkyBlockCells] The plugin couldn't find WorldGuard! If you think that this is wrong, contact the developer.");
        }
        else{
            System.out.println("[ASkyBlockCells] WorldGuard version "+Bukkit.getPluginManager().getPlugin("WorldGuard").getDescription().getVersion()+" found!");
        }
        if (Bukkit.getPluginManager().getPlugin("WorldEdit") == null) {
            we = false;
            System.out.println("[ASkyBlockCells] The plugin couldn't find WorldEdit! If you think that this is wrong, contact the developer.");
        }
        else{
            System.out.println("[ASkyBlockCells] WorldEdit version "+Bukkit.getPluginManager().getPlugin("WorldEdit").getDescription().getVersion()+" found!");
        }
        if (Bukkit.getPluginManager().getPlugin("PlayCells") == null) {
            asb = false;
            System.out.println("[ASkyBlockCells] The plugin couldn't find PlayCells! If you think that this is wrong, contact the developer.");
        }
        else{
            System.out.println("[ASkyBlockCells] PlayCells version "+Bukkit.getPluginManager().getPlugin("PlayCells").getDescription().getVersion()+" found!");
        }
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            vault = false;
            System.out.println("[ASkyBlockCells] The plugin couldn't find Vault! If you think that this is wrong, contact the developer.");
        }
        else{
            System.out.println("[ASkyBlockCells] Vault version "+Bukkit.getPluginManager().getPlugin("Vault").getDescription().getVersion()+" found!");
        }
        if (!wg || !we || !asb || !vault){
            System.out.println("[ASkyBlockCells] The plugin couldn't find one or more of the plugins that it depends on! (see above)");
            System.out.println("[ASkyBlockCells] The plugin will now disable! Goodbye cruel world :(");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        else{
            System.out.println("[ASkyBlockCells] The plugin enabled successfully!");
        }
        if (!setupEconomy()) {
            this.getLogger().severe("[ASkyBlockCells] Plugin disabled due to no Vault found!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public Economy getEconomy() {
        return econ;
    }


    public static ASkyBlockCells getInstance(){
        return plugin;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
