package com.foxdev.visplugin;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class VisPlugin extends JavaPlugin implements Listener {


    private FishingManager fishingManager;

    @Override
    public void onEnable() {
        fishingManager = new FishingManager();
        getServer().getPluginManager().registerEvents(this, this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            Fish fish = fishingManager.generateFish();
            int fishValue = fishingManager.calculateFishValue(fish);
            ItemStack fishItem = new ItemStack(Material.RAW_FISH, 1);
            ItemMeta fishItemMeta = fishItem.getItemMeta();
            fishItemMeta.setDisplayName("Gevangen vis");
            fishItemMeta.setLore(Arrays.asList(String.format("Gewicht: %.1f kg", fish.getWeight()), String.format("Waarde: €%d;", fishValue)));
            fishItem.setItemMeta(fishItemMeta);
            event.getCaught().setItemStack(fishItem);

            if(fishingManager.tryGenerateCrateKey()){
                //Geef speler een viscrate key!
            }
        }
    }
}
