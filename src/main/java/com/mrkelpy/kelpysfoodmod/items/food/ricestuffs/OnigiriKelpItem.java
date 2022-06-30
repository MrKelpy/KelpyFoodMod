package com.mrkelpy.kelpysfoodmod.items.food.ricestuffs;

import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class implements all the logic and features of the WhiteMochi item.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class OnigiriKelpItem extends Item {

    public static final Properties itemProperties = OnigiriKelpItem.buildProperties();

    public OnigiriKelpItem() {
        super(itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Properties buildProperties() {

        Properties properties = new Properties();
        properties.food(new FoodProperties.Builder().nutrition(8).saturationMod(0.8F).build());
        properties.tab(Registration.CUSTOM_TAB_FOODS);

        return properties;
    }
}
