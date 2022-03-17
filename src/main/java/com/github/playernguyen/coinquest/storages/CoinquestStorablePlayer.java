package com.github.playernguyen.coinquest.storages;

import com.github.playernguyen.coinquest.core.CoinquestPlayer;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents default-style for persistence of players.
 */
public interface CoinquestStorablePlayer {

    /**
     * Retrieves a player from storage location (database or memory).
     *
     * @return a player parsed from storage location.
     */
    CoinquestPlayer getPlayer() throws Exception;

    /**
     * Represents update method, set a new persists value.
     */
    void update(CoinquestPlayer player) throws Exception;

    /**
     * Returns true whether the player information exists in storage location, false otherwise.
     *
     * @return true whether the player information exists in storage location, false otherwise.
     */
    boolean hasPlayer();

}
