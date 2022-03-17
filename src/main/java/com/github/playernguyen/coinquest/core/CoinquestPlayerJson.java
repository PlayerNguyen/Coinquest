package com.github.playernguyen.coinquest.core;

import java.util.UUID;

public class CoinquestPlayerJson extends CoinquestPlayerDefault {

    public CoinquestPlayerJson() {
    }

    public CoinquestPlayerJson(UUID uuid) {
        super(uuid);
    }

    public CoinquestPlayerJson(UUID uuid, double balance) {
        super(uuid, balance);
    }

}
