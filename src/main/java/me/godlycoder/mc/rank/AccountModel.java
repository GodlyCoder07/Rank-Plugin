package me.godlycoder.mc.rank;

import me.godlycoder.json.JsonFile;

import java.io.*;
import java.util.UUID;

public class AccountModel extends JsonFile {
    private Accounts accounts;

    private final File file;

    public AccountModel(File file) {
        this.file = file;
        createOrLoad();
    }

    @Override
    public void create() {
        accounts = new Accounts();
        save();
    }

    @Override
    public void load() {
        try {
            FileReader reader = new FileReader(file);
            gson.fromJson(reader, Accounts.class);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        try {
            FileWriter writer = new FileWriter(file);
            gson.toJson(Accounts.class, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createOrLoad() {
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            boolean b = file.getParentFile().mkdir();
        }

        if (!file.exists()) {
            try {
                boolean b = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        load();
    }

    public void add(Account account) {
        load();
        if (!contains(account.getUuid())) {
            accounts.getAccounts().add(account);
        }
        save();
    }

    public Account get(UUID uuid) {
        load();
        return accounts.getAccounts().stream()
                .filter(acc -> acc.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);
    }
    
    public boolean contains(UUID uuid) {
        return get(uuid) != null;
    }
}
