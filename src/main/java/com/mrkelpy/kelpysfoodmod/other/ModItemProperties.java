package com.mrkelpy.kelpysfoodmod.other;

import com.mrkelpy.kelpysfoodmod.setup.Registration;
import com.mrkelpy.kelpysfoodmod.utils.resources.PartialItemProperty;
import com.mrkelpy.kelpysfoodmod.utils.resources.PartialItemPropertyManager;

import java.util.ArrayList;

public class ModItemProperties extends PartialItemPropertyManager {

    /**
     * This list contains all the available custom item properties in the mod.
     * These item properties are used in the json for overriding model textures.
     */
    @Override
    public ArrayList<PartialItemProperty> registerProperties() {
        ArrayList<PartialItemProperty> properties = new ArrayList<>();

        properties.add(new PartialItemProperty("held",
                (stack, world, entity, seed) -> entity != null && entity.getMainHandItem().getItem().equals(Registration.LEMBAS.get()) ? 1 : 0));

        return properties;
    }

}

