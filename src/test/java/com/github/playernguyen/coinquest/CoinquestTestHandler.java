package com.github.playernguyen.coinquest;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import com.github.playernguyen.coinquest.configurations.settings.CoinquestSetting;
import org.junit.jupiter.api.*;

import java.io.File;


public class CoinquestTestHandler {

    ServerMock mockServer = new ServerMock();
    Coinquest coinquest;

    /**
     * Test unit initialise
     */
    @BeforeEach
    public void setup() {
        mockServer = MockBukkit.mock();
        coinquest = MockBukkit.load(Coinquest.class);
        coinquest.getLogger().info("Coinquest test loaded");
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unload();
    }

    @Test
    public void loadedMockBukkit() {
        Assertions.assertNotNull(coinquest.getLogger());
        Assertions.assertNotNull(coinquest.getDescription());
    }

    @Test
    public void isDevelopmentBuildInTest() {
        Assertions.assertTrue(coinquest.isDevelopmentBuild());
    }

    @Test
    public void addPlayerThenAddCredits() {
        mockServer.addPlayer("Player".concat(String.valueOf(Math.random() * (100 - 1) + 100)));
    }

    @Test
    public void shouldCreatedSettingConfigurationFile () {
        File tempFile = new File(coinquest
                .getDataFolder(), CoinquestSetting.SETTING_FILE_NAME);
        Assertions.assertTrue(tempFile.exists());
    }
}
