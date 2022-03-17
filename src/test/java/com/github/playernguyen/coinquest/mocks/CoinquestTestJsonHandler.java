package com.github.playernguyen.coinquest.mocks;

import com.github.playernguyen.coinquest.configurations.settings.CoinquestSettingPattern;
import com.github.playernguyen.coinquest.core.CoinquestPlayer;
import com.github.playernguyen.coinquest.storages.CoinquestStorablePlayer;
import com.github.playernguyen.coinquest.storages.CoinquestStorablePlayerJson;
import com.github.playernguyen.coinquest.utils.MathTestUtils;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.UUID;

public class CoinquestTestJsonHandler extends CoinquestMockTest {

    @Test
    public void shouldInitializedStorageJson() throws Exception {
        Player player = mockServer.addPlayer();

        UUID uuid = player.getUniqueId();
        CoinquestStorablePlayer storagePlayer =
                new CoinquestStorablePlayerJson(getPlugin(), uuid);

        // File not found
        Assertions.assertFalse(storagePlayer.hasPlayer());
        Assertions.assertNotNull(storagePlayer.getPlayer());

        // Initialized player must be zero
        Assertions.assertEquals(0.0f, storagePlayer.getPlayer().getBalance());

        // and the uuid must be the same
        Assertions.assertEquals(uuid.toString(),
                storagePlayer.getPlayer().getPlayerUUID().toString()
        );

        // Then save a current user
        CoinquestPlayer savePlayer =
                this.getPlugin().getPlayerManager().createPlayer(uuid, 1000);
        savePlayer.setBalance(1000.0f);

        Assertions.assertDoesNotThrow(() -> storagePlayer.update(savePlayer));

        Assertions.assertEquals(1000.0f, storagePlayer.getPlayer().getBalance());
        Assertions.assertEquals(uuid.toString(), storagePlayer.getPlayer().getPlayerUUID().toString());
    }


    @Test
    public void shouldInsertNewPlayerWhenPrepared() throws Exception {
        Player serverPlayer = this.createPlayerWithRandomName();


        this.getPlugin().getLogger().info(this.getPlugin()
                .getPlayerManager()
                .getContainer()
                .getPlayerMap().toString()
        );

        // Contains in container
        Assertions.assertNotNull(this.getPlugin()
                .getPlayerManager()
                .getContainer()
                .getPlayerFromUUID(serverPlayer.getUniqueId())
        );

        Assertions.assertEquals(
                this.getPlugin()
                        .getConfigurationSetting()
                        .get(CoinquestSettingPattern.DEFAULT_TOKEN_ON_FIRST_JOIN).asDouble(),
                this.getPlugin()
                        .getPlayerManager()
                        .getPlayerFromContainer(serverPlayer.getUniqueId())
                        .getBalance()
        );

        Assertions.assertEquals(
                this.getPlugin()
                        .getConfigurationSetting()
                        .get(CoinquestSettingPattern.DEFAULT_TOKEN_ON_FIRST_JOIN).asDouble(),
                this.getPlugin()
                        .getPlayerManager()
                        .getPlayerFromStorage(serverPlayer.getUniqueId())
                        .getBalance()
        );
    }

    @Test
    public void shouldChangeAfterUpdated() throws Exception {
        Player playerWithRandomName = this.createPlayerWithRandomName();
        double updateBalance = MathTestUtils.rand(1D, 10000D);
        this.getPlugin().getPlayerManager()
                .updatePlayer(playerWithRandomName.getUniqueId(), updateBalance);

        // Modified in container
        Assertions.assertEquals(
                this.getPlugin().
                        getPlayerManager()
                        .getPlayerFromContainer(playerWithRandomName.getUniqueId())
                        .getBalance(),
                updateBalance
        );

        // Modified on file
        Assertions.assertEquals(
                this.getPlugin().
                        getPlayerManager()
                        .getPlayerFromStorage(playerWithRandomName.getUniqueId()).getBalance(),
                updateBalance
        );
    }

}
