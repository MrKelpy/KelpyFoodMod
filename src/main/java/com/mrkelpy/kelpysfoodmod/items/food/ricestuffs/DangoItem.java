package com.mrkelpy.kelpysfoodmod.items.food.ricestuffs;

import com.mrkelpy.kelpysfoodmod.setup.Registration;
import com.mrkelpy.kelpysfoodmod.utils.ItemUtils;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class implements all the logic and features of the Dango item.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class DangoItem extends Item {

    public static Item.Properties itemProperties = DangoItem.buildProperties();

    public DangoItem() {
        super(itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Item.Properties buildProperties() {

        Item.Properties properties = new Item.Properties();
        properties.food(new FoodProperties.Builder().nutrition(7).saturationMod(0.9F).build());
        properties.tab(Registration.CUSTOM_TAB_FOODS);

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

        if (!world.isClientSide() && livingEntity instanceof ServerPlayer serverplayer)
            ItemUtils.giveItem(new ItemStack(Items.STICK), serverplayer);

        return super.finishUsingItem(itemStack, world, livingEntity);
    }
}
