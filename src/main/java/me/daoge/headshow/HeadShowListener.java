package me.daoge.headshow;

import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.server.PlayerJoinEvent;
import org.allaymc.api.server.Server;

public class HeadShowListener {

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        var plugin = HeadShow.getInstance();
        Server.getInstance().getScheduler().runLater(plugin, () -> plugin.updatePlayer(event.getPlayer()));
    }
}
