package com.github.playernguyen.coinquest.mocks;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import com.github.playernguyen.coinquest.Coinquest;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.Random;

public abstract class CoinquestMockTest implements Mockable {

    ServerMock mockServer;
    Coinquest plugin;

    /**
     * Test unit initialise
     */
    @BeforeEach
    public void setup() {
        this.mockServer = MockBukkit.mock();
        this.plugin = MockBukkit.load(Coinquest.class);
        this.plugin.getLogger().info("Coinquest test loaded");
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unload();
    }

    @Override
    public Coinquest getPlugin() {
        return plugin;
    }

    @Override
    public ServerMock getMockServer() {
        return mockServer;
    }

    @Override
    public Player createPlayerWithRandomName() throws Exception {
        int i = new Random().nextInt(Integer.MAX_VALUE);

        // Add player into mock server
        Player serverPlayer  = this.mockServer.addPlayer(String.format("Player %s", i));

        // Load into container
        this.getPlugin().getPlayerManager().preparePlayer(serverPlayer.getUniqueId());

        // Inform the server
        this.getPlugin().getLogger().info(
                String.format("Injecting player %s %s",
                        serverPlayer.getName(),
                        serverPlayer.getUniqueId())
        );
        return serverPlayer;
    }
}
