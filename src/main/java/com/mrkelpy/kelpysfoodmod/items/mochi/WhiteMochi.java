package com.mrkelpy.kelpysfoodmod.items.mochi;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class WhiteMochi extends Item {
    /**
     * This class implements all the logic and features of the WhiteMochi item.
     */

    public static final Item.Properties itemProperties = WhiteMochi.buildProperties();

    public WhiteMochi() {
        super(itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Properties buildProperties() {

        Properties properties = new Properties();
        properties.food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).alwaysEat().fast().build());
        properties.tab(CreativeModeTab.TAB_FOOD);

        return properties;
    }
}
