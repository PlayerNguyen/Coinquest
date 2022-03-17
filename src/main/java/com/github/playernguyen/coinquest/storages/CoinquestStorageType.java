package com.github.playernguyen.coinquest.storages;

import java.util.Locale;

public enum CoinquestStorageType {
    JSON("json"),
    MYSQL("mysql");

    private final String localizedName;

    CoinquestStorageType(String localizedName) {
        this.localizedName = localizedName;
    }

    /**
     * A localized name using for configuration object detect.
     *
     * @return a localized char sequence name.
     */
    public String getLocalizedName() {
        return localizedName;
    }

    /**
     * Parse storage type from string.
     *
     * @param object an object to parse
     * @return a storage enumerate object.
     */
    public static CoinquestStorageType parseStorageType(String object) {
        if (object.equals(
                CoinquestStorageType.JSON.getLocalizedName())
        ) {
            return JSON;
        }
        throw new IllegalStateException("Cannot parse a storage type with name " + object);
    }
}
