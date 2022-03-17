package com.github.playernguyen.coinquest.configurations.settings;

import com.github.playernguyen.coinquest.configurations.CoinquestConfigurationPattern;

public enum CoinquestSettingPattern implements CoinquestConfigurationPattern {

    STORAGE_TYPE("StorageType", "json", "The storage type"),

    JSON_STORAGE_DIRECTORY("JsonStorageDirectory", "storage"),

    DEFAULT_TOKEN_ON_FIRST_JOIN("DefaultTokenOnFirstJoin", 0D)

    ;

    private final String node;
    private final Object defaultValue;
    private final String comment;

    CoinquestSettingPattern(String node, Object defaultValue, String comment) {
        this.node = node;
        this.defaultValue = defaultValue;
        this.comment = comment;
    }

    CoinquestSettingPattern(String node, Object defaultValue) {
        this.node = node;
        this.defaultValue = defaultValue;
        this.comment = "";
    }

    @Override
    public String getNode() {
        return node;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String getComment() {
        return comment;
    }
}
