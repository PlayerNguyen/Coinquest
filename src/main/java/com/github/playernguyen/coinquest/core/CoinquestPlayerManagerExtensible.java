package com.github.playernguyen.coinquest.core;

import java.util.UUID;

public interface CoinquestPlayerManagerExtensible {

    /**
     * Container that contains online player current token.
     *
     * @return an online container object.
     */
    CoinquestOnlinePlayerContainer getContainer();

    /**
     * Create a player corresponding to the configured storage type.
     * For example, {@link CoinquestPlayerJson}, ...
     *
     * @param uuid    a unique id of player.
     * @param balance a balance to set to player.
     * @return a new instance of player as object (references).
     */
    CoinquestPlayer createPlayer(UUID uuid, double balance);

    /**
     * Get a player from the current online container as the previous updated.
     *
     * @param uuid a unique id of player to get.
     * @return the last updated player from container, unless it exists return null.
     */
    CoinquestPlayer getPlayerFromContainer(UUID uuid);

    /**
     * Get a player directly from storage (JSON file or database).
     * For some low-latency system, please make sure that you check for
     * last update before request.
     *
     * @param uuid a unique id of player to get.
     * @return the player from
     * @throws Exception any exception
     */
    CoinquestPlayer getPlayerFromStorage(UUID uuid) throws Exception;

    /**
     * Gets the player from storage with bared unique id and put it into
     * an online current container.
     *
     * @param uuid a unique id of that player.
     * @throws Exception any exception.
     */
    void preparePlayer(UUID uuid) throws Exception;

    /**
     * Updates player balance by replacing new player instance.
     *
     * @param player a new player instance which will replace the older player.
     * @throws Exception an exception that cannot modify user in storage or container.
     * @deprecated this method cost more time complexity by getting and
     * replacing a hash map value.
     */
    void updatePlayer(CoinquestPlayer player) throws Exception;

    /**
     * Accesses and sets a balance of player inside container and storage file.
     *
     * @param uuid    a unique id of player to modify.
     * @param balance a balance to set.
     * @throws Exception an exception that throw when cannot modify or access storage system.
     */
    void updatePlayer(UUID uuid, double balance) throws Exception;

}
