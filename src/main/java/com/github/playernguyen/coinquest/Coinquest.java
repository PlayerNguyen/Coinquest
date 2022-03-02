package com.github.playernguyen.coinquest;

import com.github.playernguyen.coinquest.configurations.settings.CoinquestSetting;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.io.IOException;

public class Coinquest extends JavaPlugin {

    private boolean isDevelopmentBuild;
    private CoinquestSetting configurationSetting;

    public Coinquest() {
        super();

        this.isDevelopmentBuild = false;
    }

    protected Coinquest(JavaPluginLoader loader,
                        PluginDescriptionFile description,
                        File dataFolder,
                        File file) {
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        // Define a logic here

        try {
            initDebugger();
            logStatus();
            loadConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    /**
     * Initialize the debugger
     */
    public void initDebugger() {
        // Get the version name
        if (this.getDescription().getVersion().contains("alpha")
                || this.getDescription().getVersion().contains("beta")
                || this.getDescription().getVersion().contains("dev")) {
            this.isDevelopmentBuild = true;
        }
    }

    private void logStatus() {
        this.getLogger().info("Coinquest version " + this.getDescription().getVersion());
    }

    /**
     * Check whether the plugin is debugging or not.
     *
     * @return true whether plugin name contains alpha, beta, or dev.
     */
    public boolean isDevelopmentBuild() {
        return isDevelopmentBuild;
    }

    /**
     * Load the configurations
     */
    private void loadConfiguration() throws IOException, InvalidConfigurationException {
        this.getLogger().info("Loading settings bundle...");
        this.configurationSetting = new CoinquestSetting(this);
    }

    /**
     * Setting configuration object to access setting configuration file.
     *
     * @return a {@link CoinquestSetting} object.
     */
    public CoinquestSetting getConfigurationSetting() {
        return configurationSetting;
    }
}
