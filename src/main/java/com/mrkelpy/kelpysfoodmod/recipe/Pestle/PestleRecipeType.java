package com.mrkelpy.kelpysfoodmod.recipe.Pestle;

import com.mrkelpy.kelpysfoodmod.KelpysFoodMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

/**
 * This class is responsible for defining the Recipe Type for the Pestle Recipe.
 * All pestle recipes will be of the PestleRecipeType type, and any recipe registered
 * under this type is expected to work with the behaviour provided by the Pestle.
 */
public class PestleRecipeType implements RecipeType<PestleRecipe> {

    public PestleRecipeType() { }  // Constructor so we can get the class instance

    /**
     * This property is required in order to provide the Recipe<T> class with the correct type
     * of recipe to be returned upon using getType()
     */
    public static final PestleRecipeType INSTANCE = new PestleRecipeType();

    /**
     * This ID is the same that will go under the "type" key in the recipe JSONs for Pestle Craftings.
     */
    public static final String ID = "pestle_crafting";

    /**
     * Overriding toString() with the proper Recipe ResourceLocation string for debugging
     * and readability purposes.
     */
    @Override
    public String toString() { return new ResourceLocation(KelpysFoodMod.MODID, ID).toString(); }
}

