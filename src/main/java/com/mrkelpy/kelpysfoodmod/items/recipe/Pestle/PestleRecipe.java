package com.mrkelpy.kelpysfoodmod.items.recipe.Pestle;

import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class is responsible for defining the Recipe Object that carries the information
 * and every method necessary in order to develop craftings with the Pestle. It's essentially a model. <br>
 * This class receives the information needed from the PestleRecipeSerializer.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class PestleRecipe implements Recipe<Container> {

    /**
     * The constructor and properties of the recipe is totally custom and up to
     * the developer to choose.<br>
     * NOTE: The ID field should probably be implemented this way for ease of use later on registration.
     */
    private final ResourceLocation ID;

    // To be set during serialization
    public ItemStack ingredient;
    public ItemStack remains;
    public ItemStack product;
    public int productCount;

    public PestleRecipe(ResourceLocation id) {
        this.ID = id;
    }

    /**
     * This method is responsible for checking if the current environment conditions are sufficient
     * for the product to be able to be produced.
     * <br>
     * Ex. Checking if all the ingredients exist in the Container in the proper order, etc...
     * <br>
     * <br>
     * In this case, checks if the main hand contains a pestle, and if the ingredient is in the offhand.
     */
    @Override
    public boolean matches(Container container, Level world) {

        if (!world.isClientSide() && container instanceof Inventory inventory)
            return inventory.getSelected().equals(new ItemStack(Registration.PESTLE.get())) &&
                    inventory.getItem(Inventory.SLOT_OFFHAND).equals(this.ingredient);

        return false;
    }

    /**
     * This method takes in all the information estabilished and creates the result item. <br>
     * The return value of this method is the actual ItemStack of the product.
     * <br>
     * <br>
     * In this case, damages the pestle, and checks if there should be a remaining item. If so, sets the remaining item
     * as the offhand item for the inventory, and returns the product.
     */
    @Override
    public ItemStack assemble(Container container) {

        Inventory inventory = (Inventory) container;
        inventory.getSelected().setDamageValue(inventory.getSelected().getDamageValue() + 1);

        // Check if there should be a remaining item, and if so, set it as the offhand item
        if (this.remains != null && inventory.getItem(Inventory.SLOT_OFFHAND).getCount() == 1)
            inventory.setItem(Inventory.SLOT_OFFHAND, this.remains);

        return this.product;
    }

    /**
     * This class defines whether the recipe can be crafted within certain dimensions, and which
     * details for the x and y axises are required for the crafting to occur. <br>
     * NOTE: "Dimensions" refers to MC's referential's X and Y axises.
     */
    @Override
    public boolean canCraftInDimensions(int height, int width) {
        return true;
    }

    /**
     * This class returns the ItemStack of the result item. This class does not return the product
     * in itself, rather a copy of it, for comparison/checking purposes.
     */
    @Override
    public ItemStack getResultItem() {
        return this.product.copy();
    }

    /**
     * This class returns the Recipe ID, which is, most of the time, just the path of the JSON file the recipe
     * is in.
     */
    @Override
    public ResourceLocation getId() {
        return this.ID;
    }

    /**
     * This method is responsible for returning the Serializer instance used to create the Recipe.
     */
    @Override
    public RecipeSerializer<?> getSerializer() {
        return PestleRecipeSerializer.INSTANCE;
    }

    /**
     * This method is responsible for returning a RecipeType instance of the PestleRecipe model.
     */
    @Override
    public RecipeType<?> getType() {
        return PestleRecipeType.INSTANCE;
    }

}

