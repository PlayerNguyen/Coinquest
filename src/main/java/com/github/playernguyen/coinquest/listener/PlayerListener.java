package com.github.playernguyen.coinquest.listener;

import com.github.playernguyen.coinquest.Coinquest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener extends CoinquestListener {

    private final Coinquest plugin;

    public PlayerListener(Coinquest plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        try {
            this.plugin.getPlayerManager().preparePlayer(player.getUniqueId());
            this.plugin.getLogger().info("Preparing player");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
