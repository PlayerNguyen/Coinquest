package com.github.playernguyen.coinquest.core;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public abstract class CoinquestPlayerUpdatable implements CoinquestPlayer {

    private UUID uuid;
    private double balance;

    public CoinquestPlayerUpdatable() {
    }

    public CoinquestPlayerUpdatable(UUID uuid) {
        this.uuid = uuid;
        this.balance = 0;
    }

    public CoinquestPlayerUpdatable(UUID uuid, double balance) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinquestPlayerUpdatable that = (CoinquestPlayerUpdatable) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

}
