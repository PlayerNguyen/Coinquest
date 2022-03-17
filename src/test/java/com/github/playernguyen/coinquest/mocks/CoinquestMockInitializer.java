package com.github.playernguyen.coinquest.mocks;

import com.github.playernguyen.coinquest.configurations.settings.CoinquestSetting;
import com.github.playernguyen.coinquest.configurations.settings.CoinquestSettingPattern;
import com.github.playernguyen.coinquest.core.CoinquestPlayer;
import com.github.playernguyen.coinquest.storages.CoinquestStorablePlayer;
import com.github.playernguyen.coinquest.storages.CoinquestStorablePlayerJson;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.UUID;


public class CoinquestMockInitializer extends CoinquestMockTest {

    @Test
    public void loadedMockBukkit() {
        Assertions.assertNotNull(getPlugin().getLogger());
        Assertions.assertNotNull(getPlugin().getDescription());
    }

    @Test
    public void isDevelopmentBuildInTest() {
        Assertions.assertTrue(getPlugin().isDevelopmentBuild());
    }

    @Test
    public void shouldCreatedSettingConfigurationFile() {
        File tempFile = new File(getPlugin()
                .getDataFolder(), CoinquestSetting.SETTING_FILE_NAME);
        Assertions.assertTrue(tempFile.exists());

        // Check default value and initialized value must be the same references
        for (CoinquestSettingPattern pattern : CoinquestSettingPattern.values()) {
            Assertions.assertEquals(getPlugin().getConfigurationSetting().get(pattern).asObject(),
                    pattern.getDefaultValue());
        }
    }

}
