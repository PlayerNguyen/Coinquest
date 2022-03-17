package com.github.playernguyen.coinquest.mocks;

import be.seeseemelk.mockbukkit.ServerMock;
import com.github.playernguyen.coinquest.Coinquest;
import org.bukkit.entity.Player;

public interface Mockable {

    /**
     * A mock server for mock test class.
     *
     * @return a mock server
     */
    ServerMock getMockServer();

    /**
     * A coinquest plugin instance after loaded.
     *
     * @return a coinquest plugin instance.
     */
    Coinquest getPlugin();

    /**
     * Creates and adds new player with randomize name pattern (Player x) where x
     * from range 1 to MAX_INTEGER.
     * <br/>
     * <p>
     * int i = new Random().nextInt(Integer.MAX_VALUE);
     * Player serverPlayer = mockServer.addPlayer(String.format("Player %s", i));
     *
     * @return new player with randomize name pattern (Player x) where x
     * from range 1 to MAX_INTEGER
     */
    Player createPlayerWithRandomName() throws Exception;

}
