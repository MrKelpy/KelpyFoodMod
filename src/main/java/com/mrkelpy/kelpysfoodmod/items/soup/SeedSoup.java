package com.mrkelpy.kelpysfoodmod.items.soup;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SeedSoup extends Item {
    /**
     * This class implements all the logic and features of the SeedSoup item.
     */

    private static final Item.Properties itemProperties = SeedSoup.buildProperties();

    public SeedSoup() {
        super(itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Properties buildProperties() {

        Properties properties = new Properties();
        properties.food(new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).build());
        properties.tab(CreativeModeTab.TAB_FOOD);

        return properties;
    }
}
