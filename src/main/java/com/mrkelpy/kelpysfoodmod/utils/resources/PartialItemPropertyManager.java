package com.mrkelpy.kelpysfoodmod.utils.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This abstract class allows for the creation of a PartialItemPropertyManager, which will be a class
 * storing all the PartialItemProperties in the mod, which can then be applied to any item in the
 * form of an ItemProperty.
 */
public abstract class PartialItemPropertyManager {

    /**
     * This list contains all the available custom item properties in the mod.
     * These item properties are used in the json for overriding model textures.
     */
    private final ArrayList<PartialItemProperty> properties = this.registerProperties();

    /**
     * This method is one to be implemented in subclasses, that will contain every property that
     * will be used for every item in the mod, in the form of a partial property.
     * @return A list with all the PartialItemProperties to be used by the items.
     */
    public abstract ArrayList<PartialItemProperty> registerProperties();

    /**
     * Returns a filtered list of properties based on a given filter (predicate)
     * @param filter The predicate to use as filter
     * @return The filtered list of properties
     */
    public ArrayList<PartialItemProperty> getProperties(Predicate<? super PartialItemProperty> filter) {
        return (ArrayList<PartialItemProperty>) this.getProperties().stream().filter(filter).toList();
    }

    /**
     * Returns a filtered list of properties whose name matches the ones requested.
     * @param requestedProperties An array of property names to retrieve the properties for
     * @return An ArrayList with only the requested properties
     */
    public ArrayList<PartialItemProperty> getProperties(String... requestedProperties) {

        List<String> requestedPropertyList = Arrays.asList(requestedProperties);
        return (ArrayList<PartialItemProperty>) getProperties().stream().filter(p -> requestedPropertyList.contains(p.itemPropertyName)).collect(Collectors.toList());
    }

    /**
     * @return Unfilteredly returns all the available properties.
     */
    public ArrayList<PartialItemProperty> getProperties() {
        return this.properties;
    }



}

