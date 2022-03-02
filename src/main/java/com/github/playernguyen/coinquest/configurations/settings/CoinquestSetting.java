package com.github.playernguyen.coinquest.configurations.settings;

import com.github.playernguyen.coinquest.Coinquest;
import com.github.playernguyen.coinquest.configurations.CoinquestConfigurationYamlFile;
import org.bukkit.configuration.InvalidConfigurationException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Represents setting configuration file to access.
 */
public class CoinquestSetting extends CoinquestConfigurationYamlFile<CoinquestSettingPattern> {
    /**
     * The name of this file
     */
    public static final String SETTING_FILE_NAME = "settings.yml";

    /**
     * Construction
     *
     * @param plugin a coinquest plugin to inject
     * @throws IOException                   unable to generate data folder or file
     * @throws InvalidConfigurationException attempting to load an invalid {@link org.bukkit.configuration.Configuration}
     */
    public CoinquestSetting(@NotNull Coinquest plugin
    ) throws IOException, InvalidConfigurationException {
        super(plugin, SETTING_FILE_NAME, CoinquestSettingPattern.class);
    }
}
