package com.github.playernguyen.coinquest;

import com.github.playernguyen.coinquest.configurations.settings.CoinquestSetting;
import com.github.playernguyen.coinquest.configurations.settings.CoinquestSettingPattern;
import com.github.playernguyen.coinquest.core.*;
import com.github.playernguyen.coinquest.listener.ListenerManager;
import com.github.playernguyen.coinquest.storages.CoinquestStorageType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.io.IOException;

public class Coinquest extends JavaPlugin {

    private boolean isDevelopmentBuild;
    private CoinquestSetting configurationSetting;
    private Gson gson;
    private CoinquestPlayerManagerExtensible playerManager;
    private ListenerManager listenerManager;

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
            loadGson();
            loadStorage();
            loadListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load all listeners for Coinquest
     */
    private void loadListener() {
        this.listenerManager = new ListenerManager(this);
        this.listenerManager.registerAll();
    }

    /**
     * Load the storage system which depends on the configuration.
     *
     * @throws Exception an exception when load the storage.
     */
    private void loadStorage() throws Exception {

        // Select what type of storage that admin want to use.
        CoinquestStorageType storageType = this
                .getConfigurationSetting()
                .get(CoinquestSettingPattern.STORAGE_TYPE)
                .asStorageType();

        switch (storageType) {
            case JSON: {
                this.playerManager = new CoinquestPlayerManagerJson(this,
                        new CoinquestOnlinePlayerContainer()
                );
                break;
            }
            case MYSQL: {
                throw new UnsupportedOperationException("MySQL is unsupported. Please wait for it.");
            }
            default: {
                throw new UnsupportedOperationException("Invalid storage type.");
            }
        }

        // Prepare all online players
        synchronized (this) {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                this.getPlayerManager().preparePlayer(onlinePlayer.getUniqueId());
            }
        }
    }

    private void loadGson() {
        GsonBuilder builder = new GsonBuilder();
        // Load gson here
        builder.registerTypeAdapter(CoinquestPlayer.class, new CoinquestPlayerJsonAdaptor());

        // builder load
        this.gson = builder.create();
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

    /**
     * Gson driver to perform translate from json object to text and vice versa.
     *
     * @return a gson object.
     */
    public Gson getGson() {
        return gson;
    }

    /**
     * Player manager subclass object corresponding to the storage
     * type that demand player interaction with storage and server.
     *
     * @return a {@link CoinquestPlayerManagerExtensible} object.
     */
    public CoinquestPlayerManagerExtensible getPlayerManager() {
        return playerManager;
    }
}
