package com.github.playernguyen.coinquest.listener;

import com.github.playernguyen.coinquest.Coinquest;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.LinkedList;
import java.util.List;

public class ListenerManager  {

    private final List<PlayerListener> listenerList = new LinkedList<>();
    private final Coinquest plugin;

    public ListenerManager(Coinquest plugin) {
        this.plugin = plugin;

        this.listenerList.add(new PlayerListener(plugin));
    }

    /**
     * Register all listener
     */
    public void registerAll() {
        for (Listener listener : this.listenerList) {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        }
    }
}
