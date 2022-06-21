package com.mrkelpy.kelpysfoodmod.recipe.NoBucketRemainder;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
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
     * Changes the assembly method of the recipe to use the NoBucketRemainder method.
     * In this method, the product will be crafted, but any instances of buckets present in the
     * clearList will be removed upon assembly.
     */
    @Override
    public ItemStack assemble(CraftingContainer workbench) {

        for (int i = 0; i < workbench.getContainerSize(); i++) {
            var item = workbench.getItem(i);

            if (this.clearList.contains(item.getItem()))
                workbench.removeItem(i, item.getCount());
        }

        return this.product.copy();
    }

    @Override
    public ResourceLocation getId() {
        return this.ID;
    }

    @Override
    public RecipeType<?> getType() {
        return NoBucketRemainderRecipeType.INSTANCE;
    }
}

