package me.godlycoder.mc.listeners;

import me.godlycoder.mc.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    public ChatListener(Rank rank) {
        Bukkit.getPluginManager().registerEvents(this, rank);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);

        Player player = event.getPlayer();
        for (Player players : player.getWorld().getPlayers()) {
            players.sendMessage(player.getDisplayName() + " §8> §f" + event.getMessage());
        }
    }
}
