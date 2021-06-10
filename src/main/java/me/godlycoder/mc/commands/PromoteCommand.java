package me.godlycoder.mc.commands;

import me.godlycoder.mc.Rank;
import me.godlycoder.mc.rank.Account;
import me.godlycoder.mc.rank.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PromoteCommand implements CommandExecutor {
    private final Rank plugin;

    public PromoteCommand(Rank plugin) {
        this.plugin = plugin;
        Bukkit.getPluginCommand("promote").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        Player target = Bukkit.getPlayerExact(args[0]);
        Ranks ranks = Ranks.lookup(args[0]);

        if (args.length < 2) {
            sender.sendMessage("Promote: Usage > Wrong Usage. Usage: /promote <player> <rank>");
        }

        if (args[0].length() < 3) {
            sender.sendMessage("Command: Argument Length > Name should be greater than or equal to 3");
        } else if (args[0].length() > 16) {
            sender.sendMessage("Command: Argument Length > Name should be less than or equal to 16");
        } else if (target == null) {
            sender.sendMessage(String.format("Promote: Unknown > %s does not exist.", args[0]));
        }

        if (target != null) {
            Account account = plugin.getAccountModel().get(target.getUniqueId());
            plugin.getAccountModel().load();
            account.setRank(ranks);
            plugin.getAccountModel().save();
            target.sendMessage(String.format("§a§lPromote: §r§6Promoted §8> §b%s, you have been promoted. Your rank is now %s", player.getName(), ranks.getPrefix()));
            player.sendMessage(String.format("§a§lPromote: §r§6Promoted §8> §b%s has been promoted to %s", target.getName(), ranks.getPrefix()));
        }
        return true;
    }
}
