package com.github.playernguyen.coinquest.configurations;

import com.github.playernguyen.coinquest.Coinquest;
import com.google.common.base.Preconditions;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

/**
 * Represents yaml configuration file.
 *
 * @param <T> an enumerator class to access to the configuration section.
 */
public class CoinquestConfigurationYamlFile<T extends CoinquestConfigurationPattern> {

    private final Coinquest plugin;
    private final File file;
    private final YamlConfiguration yamlConfiguration;

    public CoinquestConfigurationYamlFile(@NotNull Coinquest plugin,
                                          @NotNull String fileName,
                                          Class<? extends CoinquestConfigurationPattern> pattern)
            throws IOException, InvalidConfigurationException {

        Preconditions.checkNotNull(plugin);
        Preconditions.checkNotNull(fileName);

        this.plugin = plugin;
        // Generate a file unless exists.
        File parent = plugin.getDataFolder();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Unable to both read and initialize the parent folder");
        }

        this.file = new File(parent, fileName);
        if (!this.file.exists() && !this.file.createNewFile()) {
            throw new IllegalStateException("Unable to both create and initialize the file");
        }
        this.yamlConfiguration = new YamlConfiguration();

        // Load yaml configuration.
        this.yamlConfiguration.load(this.file);

        // Load defaults.
        for (CoinquestConfigurationPattern item : pattern.getEnumConstants()) {
            this.yamlConfiguration.set(item.getNode(), item.getDefaultValue());
        }
        // Log file path if the plugin is development build.
        if (this.plugin.isDevelopmentBuild()) {
            this.plugin.getLogger().info("File path = " + this.file.getAbsolutePath());
        }
    }

    /**
     * Retrieves a value from pattern node.
     *
     * @param pattern a pattern to get node.
     * @return an object persisted on memory, null otherwise.
     */
    public CoinquestConfigurationObject get(@NotNull T pattern) {
        Preconditions.checkNotNull(pattern);
        return new CoinquestConfigurationObject(this.yamlConfiguration.get(pattern.getNode()));
    }

    /**
     * A main file of this configuration object.
     *
     * @return a file of this configuration.
     */
    public File getFile() {
        return file;
    }

    public void set(@NotNull T pattern, @NotNull Object o) throws IOException {
        Preconditions.checkNotNull(pattern);
        Preconditions.checkNotNull(o);

        // Set the new value
        this.yamlConfiguration.set(pattern.getNode(), o);
        this.yamlConfiguration.save(this.file);
        // Save and load
    }
}
