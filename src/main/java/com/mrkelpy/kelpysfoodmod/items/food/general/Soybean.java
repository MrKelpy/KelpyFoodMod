package com.mrkelpy.kelpysfoodmod.items.food.general;

import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemNameBlockItem;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class implements all the logic and features of the Soybean item.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class Soybean extends ItemNameBlockItem {

    public static final Properties itemProperties = Soybean.buildProperties();

    public Soybean() {
        super(Registration.SOYBEAN_PLANT.get(), itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Properties buildProperties() {

        Properties properties = new Properties();
        properties.tab(CreativeModeTab.TAB_MISC);

        return properties;
    }
}
