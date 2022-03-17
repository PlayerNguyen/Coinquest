package com.github.playernguyen.coinquest.core;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.UUID;

/**
 * An adaptor to convert from json to object and vice versa for {@link CoinquestPlayer} interface.
 */
public class CoinquestPlayerJsonAdaptor extends TypeAdapter<CoinquestPlayer> {

    @Override
    public void write(JsonWriter out, CoinquestPlayer value) throws IOException {
        out.beginObject();
        out.name("uuid").value(value.getPlayerUUID().toString());
        out.name("balance").value(value.getBalance());
        out.endObject();
    }

    @Override
    public CoinquestPlayer read(JsonReader in) throws IOException {
        CoinquestPlayerJson player = new CoinquestPlayerJson();
        in.beginObject();
        // Get uuid and balance
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "uuid": {
                    player.setUUID(UUID.fromString(in.nextString()));
                    break;
                }
                case "balance": {
                    player.setBalance(in.nextDouble());
                    break;
                }
                default: {
                    throw new IOException("Unexpected object name.");
                }
            }
        }
        in.endObject();
        // Then retrieves
        return player;
    }
}
