package com.mrkelpy.kelpysfoodmod.recipe.NoBucketRemainder;

import com.mrkelpy.kelpysfoodmod.KelpysFoodMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

public class NoBucketRemainderRecipeType implements RecipeType<NoBucketRemainderRecipe> {

    public NoBucketRemainderRecipeType() { }

    public static final NoBucketRemainderRecipeType INSTANCE = new NoBucketRemainderRecipeType();

    public static final String ID = "crafting_no_bucket_remainder";

    /**
     * Overriding toString() with the proper Recipe ResourceLocation string for debugging
     * and readability purposes.
     */
    @Override
    public String toString() { return new ResourceLocation(KelpysFoodMod.MODID, ID).toString(); }
}

