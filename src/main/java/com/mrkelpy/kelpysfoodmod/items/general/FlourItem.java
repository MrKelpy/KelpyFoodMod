package com.mrkelpy.kelpysfoodmod.items.general;

import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.Item;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class implements all the logic and features of the Flour item.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class FlourItem extends Item {

    public static final Properties itemProperties = FlourItem.buildProperties();

    public FlourItem() {
        super(itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Properties buildProperties() {

        Properties properties = new Properties();
        properties.tab(Registration.CUSTOM_TAB_ITEMS);

        return properties;
    }
}
