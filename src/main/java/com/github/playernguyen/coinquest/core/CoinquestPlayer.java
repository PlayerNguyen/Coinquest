package com.github.playernguyen.coinquest.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface CoinquestPlayer extends Comparable<CoinquestPlayer> {

    /**
     * The Minecraft authentication player unique id.
     *
     * @return a unique id of that player.
     */
    UUID getPlayerUUID();

    /**
     * Player current balance to store as double value, or retrieve from incoming
     *  packages via database system.
     *
     * @return a current player balance.
     */
    double getBalance();

    /**
     * Sets new value for balance of player.
     *
     * @param value a balance value of player to set.
     */
    void setBalance(double value);

    /**
     * Using non-primitive object of Long to get last updated
     * because some high-latency reading method is unnecessary to use this method.
     *
     * @return a last updated.
     */
    @Nullable
    Long getLastUpdated();

    /**
     * Set last retrieves data of player.
     *
     * @param lastUpdated last time that retrieves data of player.
     */
    void setLastUpdated(Long lastUpdated);

    @Override
    int compareTo(@NotNull CoinquestPlayer o);


}
