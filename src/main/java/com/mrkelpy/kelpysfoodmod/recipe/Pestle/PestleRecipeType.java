package com.mrkelpy.kelpysfoodmod.recipe.Pestle;

import com.mrkelpy.kelpysfoodmod.KelpysFoodMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

/**
 * This class is responsible for defining the Recipe Type for the Pestle Recipe. This type
 * will be the one used by any GUIs/Containers. It defines what can use the Pestle recipes.
 */
public class PestleRecipeType implements RecipeType<PestleRecipe> {

    public PestleRecipeType() { }  // Constructor so we can get the class instance

    /**
     * This property is required in order to provide the Recipe<T> class with the correct type
     * of recipe to be returned upon using getType()
     */
    public static final PestleRecipeType INSTANCE = new PestleRecipeType();

    /**
     * This ID is the one that will define what containers can use pestle crafting.
     */
    public static final String ID = "pestle_recipes";

    /**
     * Overriding toString() with the proper Recipe ResourceLocation string for debugging
     * and readability purposes.
     */
    @Override
    public String toString() { return new ResourceLocation(KelpysFoodMod.MODID, ID).toString(); }
}

