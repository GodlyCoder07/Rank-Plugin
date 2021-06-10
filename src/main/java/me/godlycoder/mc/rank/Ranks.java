package me.godlycoder.mc.rank;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Ranks {
    OWNER("Owner", "§6§l", "§6§lOwner", RankCategory.STAFF),
    ADMIN("Admin", "§c§l", "§c§lAdmin", RankCategory.STAFF),
    HELPER("Helper", "§2§l", "§2§lHelper", RankCategory.STAFF),
    ULTIMATE("Ultimate", "§2", "§aUltimate", RankCategory.DONOR),
    GAMER("Gamer", "§a", "§aGamer", RankCategory.DONOR),
    DEFAULT("Default", "§8", "§8None", RankCategory.OTHER);

    public final String displayName, color, prefix;
    public final RankCategory category;

    Ranks(String displayName, String color, String prefix, RankCategory category) {
        this.displayName = displayName;
        this.color = color;
        this.prefix = prefix;
        this.category = category;
    }

    public static Ranks lookup(String s) {
        return Arrays.stream(Ranks.values())
                .filter(rank -> rank.name().equalsIgnoreCase(s) || rank.getDisplayName().equalsIgnoreCase(s))
                .findFirst().orElse(DEFAULT);
    }

    public enum RankCategory {
        STAFF,
        DONOR,
        OTHER
    }
}
