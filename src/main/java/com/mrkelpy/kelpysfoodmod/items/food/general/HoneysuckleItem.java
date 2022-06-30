package com.mrkelpy.kelpysfoodmod.items.food.general;

import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemNameBlockItem;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class implements all the logic and features of the Cheese item.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class HoneysuckleItem extends ItemNameBlockItem {

    private static final Properties itemProperties = HoneysuckleItem.buildProperties();

    public HoneysuckleItem() {
        super(Registration.HONEYSUCKLE_PLANT.get(), itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Properties buildProperties() {

        Properties properties = new Properties();
        properties.food(new FoodProperties.Builder().nutrition(2).saturationMod(0.6F).alwaysEat().fast().build());
        properties.tab(Registration.CUSTOM_TAB_FOODS);

        return properties;
    }
}
