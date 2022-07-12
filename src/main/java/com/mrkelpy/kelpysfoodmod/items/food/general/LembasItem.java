package com.mrkelpy.kelpysfoodmod.items.food.general;

import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class implements all the logic and features of the Cheese item.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class LembasItem extends Item {

    private static final Properties itemProperties = LembasItem.buildProperties();

    public LembasItem() {
        super(itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Properties buildProperties() {

        Properties properties = new Properties();
        properties.food(new FoodProperties.Builder().nutrition(10).saturationMod(2.0F).build());
        properties.tab(Registration.CUSTOM_TAB_FOODS);

        return properties;
    }

}
