package com.mrkelpy.kelpysfoodmod.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * This class implements useful functions for usage when dealing with Items.
 */
public class ItemUtils {

    /**
     * Tries to give the player a certain ItemStack, and if it fails, drops it on the ground.
     * @param itemStack The ItemStack to give to the player.
     * @param player The player object to give the ItemStack to.
     * @return void
     */
    public static void giveItem(ItemStack itemStack, Player player) {

        if (!player.getInventory().add(itemStack))
            player.drop(itemStack, false);
    }

}

