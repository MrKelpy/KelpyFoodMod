package com.mrkelpy.kelpysfoodmod.items.food.soup;
import com.mrkelpy.kelpysfoodmod.utils.ItemUtils;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class implements all the logic and features of the SweetPorridge item.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SweetPorridge extends Item {

    private static final Properties itemProperties = SweetPorridge.buildProperties();

    public SweetPorridge() {
        super(itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Properties buildProperties() {

        Properties properties = new Properties();
        properties.food(new FoodProperties.Builder().nutrition(8).saturationMod(0.7F).build());
        properties.tab(CreativeModeTab.TAB_FOOD);

        return properties;
    }

    /**
     * Expands upon the behaviour of the finishUsingItem method to give a stick back to the player
     * after finishing eating Sweet Porridge.
     * @param itemStack The ItemStack of the Sweet Porridge.
     * @param world The level where the item was used.
     * @param livingEntity The entity that finished using the item
     * @return [ItemStack]
     */
    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level world, LivingEntity livingEntity) {

        if (!world.isClientSide() && livingEntity instanceof ServerPlayer serverplayer)
            ItemUtils.giveItem(new ItemStack(Items.BOWL), serverplayer);

        return super.finishUsingItem(itemStack, world, livingEntity);
    }
}
