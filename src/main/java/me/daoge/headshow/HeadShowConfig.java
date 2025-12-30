package me.daoge.headshow;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class HeadShowConfig extends OkaeriConfig {

    @Comment("Nametag format. Supports PlaceholderAPI variables.")
    @CustomKey("nametag")
    private String nameTag = "{player_name}";

    @Comment("Scoretag format. Supports PlaceholderAPI variables.")
    @CustomKey("scoretag")
    private String scoreTag = "{player_health} §c❤";

    @Comment("Update interval in ticks (20 ticks = 1 second).")
    @CustomKey("update-interval")
    private int updateInterval = 20;
}
