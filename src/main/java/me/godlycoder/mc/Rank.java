package me.godlycoder.mc;

import lombok.Getter;
import me.godlycoder.mc.rank.AccountModel;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Rank extends JavaPlugin {
    private final File file = new File(this.getDataFolder(), "accounts.json");
    @Getter
    private final AccountModel accountModel = new AccountModel(file);

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
