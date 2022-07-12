package com.mrkelpy.kelpysfoodmod.utils.resources;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

/**
 * This class implements a way to generalise any Item Property and to have it be
 * used by any item, at any point.
 */
public class PartialItemProperty {

    public final ItemPropertyFunction propertyProvider;
    public final String itemPropertyName;

    /**
     * Main constructor for the class.
     * @param propertyName The name of the property to be referenced in the json files
     * @param provider The lamdba function to determine whether this property is being used or not.
     *                 Instead of a boolean value return, this lambda should return 1 or 0.
     */
    public PartialItemProperty(String propertyName, ItemPropertyFunction provider) {
        this.itemPropertyName = propertyName;
        this.propertyProvider = provider;
    }

    /**
     * Registers the Partial Item Property as an actual Item Property into the specified item.
     * After registration, the item property will be able to be used as an override in the json file
     * for the item model.
     * @param item The item to register the Property into.
     * @return Returning the partial property to allow for chaining.
     */
    public PartialItemProperty registerInto(Item item) {
        ItemProperties.register(item, new ResourceLocation(this.itemPropertyName), this.propertyProvider);
        return this;
    }

}

