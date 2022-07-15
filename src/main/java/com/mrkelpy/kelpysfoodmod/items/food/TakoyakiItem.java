package com.mrkelpy.kelpysfoodmod.items.food;

import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class implements all the logic and features of the Takoyaki item.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TakoyakiItem extends Item {

    private static final Properties itemProperties = TakoyakiItem.buildProperties();

    public TakoyakiItem() {
        super(itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Properties buildProperties() {

        Properties properties = new Properties();
        properties.food(new FoodProperties.Builder().nutrition(6).saturationMod(7.0F).build());
        properties.tab(Registration.CUSTOM_TAB_FOODS);

        return properties;
    }

}
