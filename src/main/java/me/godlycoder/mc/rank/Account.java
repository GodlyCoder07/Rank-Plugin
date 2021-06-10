package me.godlycoder.mc.rank;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter @Getter
public class Account {
    private UUID uuid;
    private Ranks rank = Ranks.DEFAULT;
}
