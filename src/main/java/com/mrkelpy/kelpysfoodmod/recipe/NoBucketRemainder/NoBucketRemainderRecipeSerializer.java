package com.mrkelpy.kelpysfoodmod.recipe.NoBucketRemainder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;


/**
 * This class is responsible for acting as a custom JSON Serializer for the recipes added
 * for a given RecipeType. In this case, for the NoBucketRemainderRecipe.
 * <br>
 * The serializer's usage is directly referenced in the JSON file for any recipe, under the "type"
 * field.
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class NoBucketRemainderRecipeSerializer extends ForgeRegistryEntry<RecipeSerializer<?>>
        implements RecipeSerializer<NoBucketRemainderRecipe> {

    public NoBucketRemainderRecipeSerializer() { }
    public static final NoBucketRemainderRecipeSerializer INSTANCE = new NoBucketRemainderRecipeSerializer();

    /**
     * This property is the same that will be specified under the "type" field
     * in the JSON Recipes.
     */
    public static final String ID = "no_bucket_remainder_crafting";

    /**
     * Parses out the specific data in any PestleRecipeType recipe JSON and applies it to a PestleRecipe instance, which
     * is then returned.
     */
    @Override
    public NoBucketRemainderRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

        String group = json.get("group") != null ? json.get("group").getAsString() : "";

        ItemStack product = new ItemStack(CraftingHelper.getItem(json.getAsJsonObject("result").get("item").getAsString(), false));
        product.setCount(json.getAsJsonObject("result").get("count").getAsInt());

        NonNullList<Ingredient> ingredients = this.itemListFromJson(GsonHelper.getAsJsonArray(json, "ingredients"));

        return new NoBucketRemainderRecipe(recipeId, group, product, ingredients);
    }

    /**
     * This method obtains the encoded data from the buffer on the toNetwork method
     * and creates a new Recipe object from it. <br>
     * NOTE: Due to the buffer behaving like a Queue, the data needs to be read in the same way as it was written.
     */
    @Nullable
    @Override
    public NoBucketRemainderRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

        NonNullList<Ingredient> ingredients = NonNullList.create();
        String group = buffer.readUtf();
        ItemStack product = buffer.readItem();
        int header_size = buffer.readInt();

        for (int i = 0; i < header_size; i++) {
            ingredients.add(Ingredient.fromNetwork(buffer));
        }

        return new NoBucketRemainderRecipe(recipeId, group, product, ingredients);

    }

    /**
     * This function obtains the Recipe object that has already been serialized, and encodes
     * it to send it to the client. This is done using a buffer, which can be understood as a
     * Queue data structure, of packets. <br>
     * The data inserted in the buffer will be inserted just like as in a Queue.
     */
    @Override
    public void toNetwork(FriendlyByteBuf buffer, NoBucketRemainderRecipe recipe) {

        buffer.writeUtf(recipe.getGroup());
        buffer.writeItem(recipe.getResultItem());
        buffer.writeInt(recipe.getIngredients().size());

        for (Ingredient ingredient : recipe.getIngredients())
            ingredient.toNetwork(buffer);
    }

    /**
     * Creates a NonNullList<Ingredient> from a given JsonArray. This is used
     * almost exclusively for getting Ingredient Lists.
     * @param array [JsonArray] The array to be converted to a NonNullList<Ingredient>
     * @return [NonNullList<Ingredient>] The NonNullList<Ingredient> created from the JsonArray
     */
    public NonNullList<Ingredient> itemListFromJson(JsonArray array) {
        NonNullList<Ingredient> ingredients = NonNullList.create();

        // For each item in the array, add it to the NonNullList<Ingredient>, if it isn't empty
        for (int i = 0; i < array.size(); i++) {
            Ingredient ingredient = Ingredient.fromJson(array.get(i));
            if (!ingredient.isEmpty()) ingredients.add(ingredient);
        }
        return ingredients;
    }
}

