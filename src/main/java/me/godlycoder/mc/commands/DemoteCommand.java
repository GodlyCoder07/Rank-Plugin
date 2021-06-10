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

public class DemoteCommand implements CommandExecutor {
    private final Rank plugin;

    public DemoteCommand(Rank plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        Player target = Bukkit.getPlayerExact(args[0]);

        if (args[0].length() < 3) {
            sender.sendMessage("§a§lCommand: §r§6Argument Length §8> §cName should be greater than or equal to 3.");
        } else if (args[0].length() > 16) {
            sender.sendMessage("§a§lCommand: §r§6Argument Length §8> §cName should be less than or equal to 16.");
        } else if (target == null) {
            sender.sendMessage(String.format("Demote: Unknown > %s does not exist.", args[0]));
        }

        if (target != null) {
            Account account = plugin.getAccountModel().get(target.getUniqueId());
            plugin.getAccountModel().load();
            account.setRank(Ranks.DEFAULT);
            plugin.getAccountModel().save();
            target.sendMessage(String.format("§a§lDemote: §r§6Demoted §8> §c%s, you have been demoted. Your rank is now %s", player.getName(), Ranks.DEFAULT.getPrefix()));
            player.sendMessage(String.format("§a§lDemote: §r§6Demoted §8> §c%s has been demoted successfully", target.getName()));
        }
        return true;
    }
}
