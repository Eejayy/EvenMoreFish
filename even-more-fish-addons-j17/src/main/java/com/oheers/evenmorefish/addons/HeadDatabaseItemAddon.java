package com.oheers.evenmorefish.addons;


import com.oheers.fish.api.addons.ItemAddon;
import com.oheers.fish.api.plugin.EMFPlugin;
import io.th0rgal.oraxen.api.events.OraxenItemsLoadedEvent;
import me.arcaniax.hdb.api.DatabaseLoadEvent;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class HeadDatabaseItemAddon extends ItemAddon implements Listener {

    private HeadDatabaseAPI api = null;

    @Override
    public String getPrefix() {
        return "headdb";
    }

    @Override
    public String getPluginName() {
        return "HeadDatabase";
    }

    @Override
    public String getAuthor() {
        return "EvenMoreFish";
    }

    @Override
    public ItemStack getItemStack(String id) {
        if (api == null) {
            return null;
        }

        if (!api.isHead(id)) {
            getLogger().warning(() -> String.format("No such head with the id %s", id));
            return null;
        }

        return api.getItemHead(id);
    }

    @EventHandler
    public void onItemsLoad(DatabaseLoadEvent event) {
        getLogger().info("Detected that HeadDatabase has finished loading...");
        getLogger().info("Reloading EMF.");
        this.api = new HeadDatabaseAPI();

        EMFPlugin.getInstance().reload(null);
    }

}
