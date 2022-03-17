package com.github.playernguyen.coinquest.storages;

import com.github.playernguyen.coinquest.Coinquest;
import com.github.playernguyen.coinquest.configurations.settings.CoinquestSettingPattern;
import com.github.playernguyen.coinquest.core.CoinquestPlayer;
import com.github.playernguyen.coinquest.core.CoinquestPlayerJson;
import com.google.gson.stream.JsonReader;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.UUID;

public class CoinquestStorablePlayerJson implements CoinquestStorablePlayer {

    private static final String FILE_PATH_EXTENSION = ".json";

    private final Coinquest plugin;
    private final UUID uuid;
    private final File file;

    public CoinquestStorablePlayerJson(Coinquest plugin, UUID uuid) {
        this.plugin = plugin;
        this.uuid = uuid;

        String storageDirectory = plugin
                .getConfigurationSetting()
                .get(CoinquestSettingPattern.JSON_STORAGE_DIRECTORY)
                .asString();

        // Init data file first
        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists() && !dataFolder.mkdirs()) {
            throw new IllegalStateException("Cannot initialize plugin data directory.");
        }

        File parentDirectory = new File(dataFolder, storageDirectory);
        if (!parentDirectory.exists() && !parentDirectory.mkdirs()) {
            throw new IllegalStateException("Cannot initialize plugin storage directory.");
        }

        this.file = new File(parentDirectory, uuid.toString().concat(FILE_PATH_EXTENSION));

        if (plugin.isDevelopmentBuild()) {
            plugin.getLogger().info(String.format("JsonStoragePlayer %s",
                    this.file.getAbsolutePath())
            );
        }
    }

    @Override @NotNull
    public CoinquestPlayer getPlayer() throws FileNotFoundException {
        // Create new player from empty
        if (!hasPlayer()) {
            return new CoinquestPlayerJson(this.uuid);
        }
        // Retrieves from json
        JsonReader reader = new JsonReader(new FileReader(this.file));
        return this.plugin.getGson().fromJson(reader, CoinquestPlayer.class);
    }

    @Override
    public void update(CoinquestPlayer player) throws IOException {
        // If the file is not existed, create a new one and modify it
        this.createFile();

        // Write out as json and then write onto file
        try (FileWriter writer = new FileWriter(this.file)) {
            String rawJson = plugin.getGson().toJson(player);
            if (plugin.isDevelopmentBuild()) {
                this.plugin.getLogger().info("json file body: " + rawJson);
            }
            writer.write(rawJson);
        }
    }

    /**
     * Create a current file.
     *
     * @throws IOException cannot create file
     */
    private void createFile() throws IOException {
        if (!hasPlayer() && !this.file.createNewFile()) {
            throw new IllegalStateException("Cannot create a json file to write out.");
        }
    }

    /**
     * Check whether contains the json file in storage directory or not.
     *
     * @return true whether "{uuid}.json" file is existed, false otherwise.
     */
    @Override
    public boolean hasPlayer() {
        return this.file.exists();
    }

}
