package com.github.playernguyen.coinquest.configurations.settings;

import com.github.playernguyen.coinquest.configurations.CoinquestConfigurationPattern;

public enum CoinquestSettingPattern implements CoinquestConfigurationPattern {

    STORAGE_TYPE("STORAGE_TYPE", "json", "The storage type");

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
