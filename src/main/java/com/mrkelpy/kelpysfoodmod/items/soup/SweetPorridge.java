package com.mrkelpy.kelpysfoodmod.items.soup;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

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
        properties.food(new FoodProperties.Builder().nutrition(8).saturationMod(0.9F).build());
        properties.tab(CreativeModeTab.TAB_FOOD);

        return properties;
    }
}
