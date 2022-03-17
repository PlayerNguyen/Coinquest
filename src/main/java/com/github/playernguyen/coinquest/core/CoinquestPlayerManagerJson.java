package com.github.playernguyen.coinquest.core;

import com.github.playernguyen.coinquest.Coinquest;
import com.github.playernguyen.coinquest.configurations.settings.CoinquestSettingPattern;
import com.github.playernguyen.coinquest.storages.CoinquestStorablePlayerJson;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class CoinquestPlayerManagerJson
        implements CoinquestPlayerManagerExtensible {

    private final CoinquestOnlinePlayerContainer container;
    private final Coinquest plugin;

    public CoinquestPlayerManagerJson(Coinquest plugin,
                                      CoinquestOnlinePlayerContainer container) {
        this.plugin = plugin;
        this.container = container;
    }

    @Override
    public CoinquestOnlinePlayerContainer getContainer() {
        return this.container;
    }

    @Override
    public CoinquestPlayer createPlayer(UUID uuid, double balance) {
        return new CoinquestPlayerJson(uuid, balance);
    }

    @Override
    public CoinquestPlayer getPlayerFromContainer(@NotNull UUID uuid) {
        return this.container.getPlayerFromUUID(uuid);
    }

    @Override
    public CoinquestPlayer getPlayerFromStorage(UUID uuid)
            throws FileNotFoundException {
        CoinquestStorablePlayerJson storagePlayer =
                new CoinquestStorablePlayerJson(plugin, uuid);
        return storagePlayer.getPlayer();
    }

    @Override
    public void preparePlayer(UUID uuid) throws FileNotFoundException {
        CoinquestStorablePlayerJson storagePlayer =
                new CoinquestStorablePlayerJson(plugin, uuid);

        CoinquestPlayer player = (storagePlayer.hasPlayer())
                ? storagePlayer.getPlayer()
                : createPlayer(uuid,
                this.plugin
                        .getConfigurationSetting()
                        .get(CoinquestSettingPattern.DEFAULT_TOKEN_ON_FIRST_JOIN)
                        .asDouble()
        );

        // Put into container
        this.container.addPlayer(player);
    }

    @Override
    public void updatePlayer(CoinquestPlayer player) throws IOException {
        // Bukkit.getOfflinePlayer(player.getPlayerUUID()).hasPlayedBefore();
        // - Check on higher class level

        // Update the storage first
        CoinquestStorablePlayerJson playerInJson
                = new CoinquestStorablePlayerJson(plugin, player.getPlayerUUID());
        playerInJson.update(player);
        // Update online container
        // Online means existed inside container
        if (this.container.hasPlayer(player.getPlayerUUID())) {
            this.container.setBalanceOfPlayer(player.getPlayerUUID(), player.getBalance());
        }
    }

    @Override
    public void updatePlayer(UUID uuid, double balance) throws Exception {
        this.updatePlayer(new CoinquestPlayerJson(uuid, balance));
    }
}
