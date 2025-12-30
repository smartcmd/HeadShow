package me.daoge.headshow;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.player.Player;
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.server.Server;
import org.allaymc.papi.PlaceholderAPI;

public class HeadShow extends Plugin {

    @Getter
    private static HeadShow instance;

    {
        instance = this;
    }

    @Getter
    private HeadShowConfig config;

    private PlaceholderAPI papi;

    @Override
    public void onLoad() {
        this.pluginLogger.info("HeadShow loaded!");
    }

    @Override
    public void onEnable() {
        this.pluginLogger.info("HeadShow enabled!");
        loadConfig();
        this.papi = PlaceholderAPI.getAPI();
        Server.getInstance().getEventBus().registerListener(new HeadShowListener());

        updateOnlinePlayers();
        scheduleUpdater();
    }

    @Override
    public void onDisable() {
        this.pluginLogger.info("HeadShow disabled!");
        if (this.config != null) {
            this.config.save();
        }
    }

    public void updateOnlinePlayers() {
        Server.getInstance().getPlayerManager().getPlayers().values().forEach(this::updatePlayer);
    }

    public void updatePlayer(Player player) {
        var entity = player.getControlledEntity();
        if (entity != null) {
            updatePlayer(entity);
        }
    }

    public void updatePlayer(EntityPlayer player) {
        var nameTag = resolvePlaceholders(player, this.config.nameTag());
        var scoreTag = resolvePlaceholders(player, this.config.scoreTag());
        player.setNameTag(normalizeTag(nameTag));
        player.setScoreTag(normalizeTag(scoreTag));
    }

    private void loadConfig() {
        this.config = ConfigManager.create(
                HeadShowConfig.class,
                it -> {
                    it.withConfigurer(new YamlSnakeYamlConfigurer());
                    it.withBindFile(this.pluginContainer.dataFolder().resolve("config.yml"));
                    it.withRemoveOrphans(true);
                    it.saveDefaults();
                    it.load(true);
                }
        );
    }

    private void scheduleUpdater() {
        var interval = this.config.updateInterval();
        if (interval <= 0) {
            this.pluginLogger.warn("Invalid update-interval {}, fallback to 20 ticks.", interval);
            interval = 20;
        }
        Server.getInstance().getScheduler().scheduleRepeating(this, () -> {
            updateOnlinePlayers();
            return true;
        }, interval);
    }

    private String resolvePlaceholders(EntityPlayer player, String input) {
        if (input == null) {
            return null;
        }
        if (this.papi == null) {
            return input;
        }
        return this.papi.setPlaceholders(player, input);
    }

    private String normalizeTag(String value) {
        return value.isBlank() ? null : value;
    }
}
