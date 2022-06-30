package com.mrkelpy.kelpysfoodmod.items.food.general;

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
 * This class implements all the logic and features of the MeatSkewer item.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MeatSkewerItem extends Item {

    private static final Properties itemProperties = MeatSkewerItem.buildProperties();

    public MeatSkewerItem() {
        super(itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Properties buildProperties() {

        Properties properties = new Properties();
        properties.food(new FoodProperties.Builder().nutrition(10).saturationMod(0.9F).build());
        properties.tab(Registration.CUSTOM_TAB_FOODS);

        return properties;
    }

    /**
     * Expands upon the behaviour of the finishUsingItem method to give a stick back to the player
     * after finishing eating a Meat Skewer.
     * @param itemStack The ItemStack of the MeatSkewer.
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
