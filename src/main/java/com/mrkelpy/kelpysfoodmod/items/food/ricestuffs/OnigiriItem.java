package com.mrkelpy.kelpysfoodmod.items.food.ricestuffs;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class implements all the logic and features of the WhiteMochi item.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class OnigiriItem extends Item {

    public static final Properties itemProperties = OnigiriItem.buildProperties();

    public OnigiriItem() {
        super(itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Properties buildProperties() {

        Properties properties = new Properties();
        properties.food(new FoodProperties.Builder().nutrition(7).saturationMod(0.8F).build());
        properties.tab(CreativeModeTab.TAB_FOOD);

        return properties;
    }
}
