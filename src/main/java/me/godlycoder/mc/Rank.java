package me.godlycoder.mc;

import lombok.Getter;
import me.godlycoder.mc.commands.DemoteCommand;
import me.godlycoder.mc.commands.PromoteCommand;
import me.godlycoder.mc.listeners.ChatListener;
import me.godlycoder.mc.listeners.JoinListener;
import me.godlycoder.mc.listeners.QuitListener;
import me.godlycoder.mc.rank.AccountModel;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Rank extends JavaPlugin {
    private final File file = new File(this.getDataFolder(), "accounts.json");
    @Getter
    private final AccountModel accountModel = new AccountModel(file);

    @Override
    public void onEnable() {
        new DemoteCommand(this);
        new PromoteCommand(this);
        new ChatListener(this);
        new JoinListener(this);
        new QuitListener(this);
    }

    @Override
    public void onDisable() {

    }
}
