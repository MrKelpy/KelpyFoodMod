package com.mrkelpy.kelpysfoodmod.items;
import com.mrkelpy.kelpysfoodmod.KelpysFoodMod;
import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.core.jmx.Server;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class Dango extends Item {
    /**
     * This class implements all the logic and features of the Dango item.
     */

    public static Item.Properties itemProperties = Dango.buildProperties();

    public Dango() {
        super(itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Item.Properties buildProperties() {

        Item.Properties properties = new Item.Properties();
        properties.food(new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).alwaysEat().fast().build());
        properties.tab(CreativeModeTab.TAB_FOOD);

        return properties;
    }

    /**
     * Expands upon the behaviour of the finishUsingItem method to give a stick back to the player
     * after finishing eating Dango.
     * @param itemStack The ItemStack of the Dango.
     * @param world The level where the item was used.
     * @param livingEntity The entity that finished using the item
     * @return [ItemStack]
     */
    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level world, LivingEntity livingEntity) {

        if (!world.isClientSide() && livingEntity instanceof ServerPlayer serverplayer) {

            // Try to give a stick to the player
            ItemStack stickStack = new ItemStack(Items.STICK);
            if (serverplayer.getInventory().add(stickStack) && itemStack.isEmpty()) {

                // If the player's inventory is full, drop the stick on the ground
                serverplayer.drop(stickStack, false);
            }
        }
        return super.finishUsingItem(itemStack, world, livingEntity);
    }
}
