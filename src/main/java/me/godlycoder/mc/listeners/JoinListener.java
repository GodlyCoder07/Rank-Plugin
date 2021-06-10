package me.godlycoder.mc.listeners;

import me.godlycoder.mc.Rank;
import me.godlycoder.mc.rank.Account;
import me.godlycoder.mc.rank.AccountModel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    private final Rank plugin;

    public JoinListener(Rank plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        AccountModel accountModel = plugin.getAccountModel();
        Player player = event.getPlayer();
        Account account;

        if (!accountModel.contains(player.getUniqueId())) {
            account = new Account();
            account.setUuid(player.getUniqueId());
            accountModel.add(account);
        }

        account = accountModel.get(player.getUniqueId());

        player.setDisplayName(account.getRank().getPrefix() + ChatColor.RESET + player.getName());
        event.setJoinMessage(player.getDisplayName() + " §e§lhas joined the game!");
    }
}
