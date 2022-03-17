package com.github.playernguyen.coinquest.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

public abstract class CoinquestPlayerDefault implements CoinquestPlayer {

    private UUID uuid;
    private double balance;

    public CoinquestPlayerDefault() {
    }

    public CoinquestPlayerDefault(UUID uuid) {
        this.uuid = uuid;
        this.balance = 0;
    }

    public CoinquestPlayerDefault(UUID uuid, double balance) {
        this.uuid = uuid;
        this.balance = balance;
    }

    @Override
    public UUID getPlayerUUID() {
        return this.uuid;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void setBalance(double value) {
        this.balance = value;
    }

    public void setUUID (UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public int compareTo(@NotNull CoinquestPlayer o) {
        return this.getPlayerUUID().toString().compareTo(o.getPlayerUUID().toString());
    }

    @Override
    public @Nullable Long getLastUpdated() {
        return null;
    }

    @Override
    public void setLastUpdated(Long lastUpdated) {
        throw new UnsupportedOperationException("Cannot set last update on default player type.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinquestPlayerDefault that = (CoinquestPlayerDefault) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "CoinquestPlayerDefault{" +
                "uuid=" + uuid +
                ", balance=" + balance +
                '}';
    }
}
