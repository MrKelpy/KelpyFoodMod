package com.mrkelpy.kelpysfoodmod.recipe.NoBucketRemainder;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapelessRecipe;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class NoBucketRemainderRecipe extends ShapelessRecipe {

    private final ResourceLocation ID;
    private final ItemStack product;
    private final ArrayList<Item> clearList = this.buildClearList();

    public NoBucketRemainderRecipe(ResourceLocation id, String group, ItemStack result, NonNullList<Ingredient> ingredients) {
        super(id, group, result, ingredients);
        this.ID = id;
        this.product = result;
    }

    /**
     * Creates a new clearList containing any item instances that are to be removed from
     * the crafting grid when the recipe is crafted.
     * @return [ArrayList<Item>] clearList
     */
    private ArrayList<Item> buildClearList() {

        var clearList = new ArrayList<Item>();
        clearList.add(Items.BUCKET);
        clearList.add(Items.WATER_BUCKET);
        clearList.add(Items.LAVA_BUCKET);
        clearList.add(Items.MILK_BUCKET);

        return clearList;
    }

    /**
     * This method will be called right when the result item is taken out of the crafting grid, and determine
     * if any items should stay in the crafting grid, in the form of a NonNullList, with each index representing
     * the container slot that the remains should be put in.
     * <br>
     * In this case, ignores the remains of any bucket items, but allows all others.
     */
    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer workbench) {

        NonNullList<ItemStack> remainingItems = NonNullList.withSize(workbench.getContainerSize(), ItemStack.EMPTY);

        for (int i = 0; i < workbench.getContainerSize(); ++i) {
            ItemStack item = workbench.getItem(i);

            // If the item is in the clearList, there'll be no remaining items of it.
            if (this.clearList.contains(item.getItem())) continue;

            if (item.hasContainerItem())
                remainingItems.set(i, item.getContainerItem());
        }
        return remainingItems;
    }

    @Override
    public ResourceLocation getId() {
        return this.ID;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return NoBucketRemainderRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeType.CRAFTING;
    }
}

