package com.mrkelpy.kelpysfoodmod.recipe.Pestle;

import com.google.gson.JsonObject;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;


/**
 * This class is responsible for acting as a custom JSON Serializer for the recipes added
 * for a given RecipeType. In this case, for the PestleRecipe.
 * <br>
 * The serializer's usage is directly referenced in the JSON file for any recipe, under the "type"
 * field.
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class PestleRecipeSerializer extends ForgeRegistryEntry<RecipeSerializer<?>>
        implements RecipeSerializer<PestleRecipe> {

    public PestleRecipeSerializer() { }
    public static final PestleRecipeSerializer INSTANCE = new PestleRecipeSerializer();

    /**
     * This property is the same that will be specified under the "type" field
     * in the JSON Recipes.
     */
    public static final String ID = "pestle_crafting";

    /**
     * Parses out the specific data in any PestleRecipeType recipe JSON and applies it to a PestleRecipe instance, which
     * is then returned.
     */
    @Override
    public PestleRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

        // The "arbitrary" parsing parameters are only a design choice with the recipes I've designed.
        PestleRecipe recipe = new PestleRecipe(recipeId);
        recipe.ingredient = new ItemStack(CraftingHelper.getItem(json.get("ingredient").getAsString(), false));
        recipe.product = new ItemStack(CraftingHelper.getItem(json.getAsJsonObject("result").get("item").getAsString(), false));
        recipe.productCount = json.getAsJsonObject("result").get("count").getAsInt();

        return recipe;
    }

    /**
     * This method obtains the encoded data from the buffer on the toNetwork method
     * and creates a new Recipe object from it. <br>
     * NOTE: Due to the buffer behaving like a Queue, the data needs to be read in the same way as it was written.
     */
    @Nullable
    @Override
    public PestleRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

        PestleRecipe recipe = new PestleRecipe(recipeId);
        recipe.ingredient = buffer.readItem();
        recipe.product = buffer.readItem();
        recipe.productCount = buffer.readVarInt();
        return recipe;
    }

    /**
     * This function obtains the Recipe object that has already been serialized, and encodes
     * it to send it to the client. This is done using a buffer, which can be understood as a
     * Queue data structure, of packets. <br>
     * The data inserted in the buffer will be inserted just like as in a Queue.
     */
    @Override
    public void toNetwork(FriendlyByteBuf buffer, PestleRecipe recipe) {

        buffer.writeItem(recipe.ingredient);
        buffer.writeInt(recipe.productCount);
        buffer.writeItem(recipe.product);
    }
}

