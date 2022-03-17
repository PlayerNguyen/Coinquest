package com.github.playernguyen.coinquest.core;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In order to store player information before request in some large high-latency database system,
 * this class was established. It implements by ConcurrentHashMap datatype for the convenience of search,
 * access, and remove whenever players join, leave, and modify in realtime.
 * <br/>
 * However, the memory of the server will reduce a little because of Map property.
 */
public class CoinquestOnlinePlayerContainer implements Iterable<Map.Entry<UUID, CoinquestPlayer>> {

    private final ConcurrentHashMap<UUID, CoinquestPlayer> playerMap =
            new ConcurrentHashMap<>();

    public ConcurrentHashMap<UUID, CoinquestPlayer> getPlayerMap() {
        return playerMap;
    }

    /**
     * Check whether it has unique id of the player in map.
     *
     * @param uuid a Minecraft player unique id.
     * @return true whether it exists, false otherwise.
     */
    public boolean hasPlayer(UUID uuid) {
        return playerMap.containsKey(uuid);
    }

    /**
     * Retrieves a player from unique id of the player;
     *
     * @param uuid a Minecraft player unique id.
     * @return a Coinquest player object.
     */
    public CoinquestPlayer getPlayerFromUUID(UUID uuid) {
        return playerMap.get(uuid);
    }

    /**
     * Add new player into a map.
     *
     * @param player a player to put into a map.
     */
    public void addPlayer(@NotNull CoinquestPlayer player) {
        Preconditions.checkNotNull(player, "The player must not be null");
        // Whether it existed in map.
        if (this.hasPlayer(player.getPlayerUUID())) {
            throw new IllegalStateException(String.format("The player with uuid %s is existed",
                    player.getPlayerUUID()));
        }
        // Append into map
        this.playerMap.put(player.getPlayerUUID(), player);
    }

    public void setBalanceOfPlayer(@NotNull UUID uuid, double balance) {
        Preconditions.checkNotNull(uuid, "The player unique id must not be null");

        // Get it
        this.playerMap.get(uuid).setBalance(balance);
    }

    /**
     * Retrieves the iterator of entrySet of the map.
     *
     * @return an iterator of the entrySet of the map.
     */
    @NotNull
    @Override
    public Iterator<Map.Entry<UUID, CoinquestPlayer>> iterator() {
        return playerMap.entrySet().iterator();
    }
}
