package com.mrkelpy.kelpysfoodmod.items;

import com.mrkelpy.kelpysfoodmod.items.recipe.Pestle.PestleRecipe;
import com.mrkelpy.kelpysfoodmod.items.recipe.Pestle.PestleRecipeType;
import com.mrkelpy.kelpysfoodmod.utils.ItemUtils;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

/**
 * This class implements all the logic and features of the Pestle item.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class Pestle extends Item {

    public static Item.Properties itemProperties = Pestle.buildProperties();

    public Pestle() {
        super(itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Item.Properties buildProperties() {

        Properties properties = new Properties();
        properties.tab(CreativeModeTab.TAB_MISC);
        properties.stacksTo(1);
        properties.durability(250);

        return properties;
    }

    /**
     * Extends the functionality of using the item (right-clicking) to crafting a certain recipe.
     * <br>
     * This will use PestleRecipe, as per the official datapack forge recipe format.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand interactionHand) {

        String recipeId = Objects.requireNonNull(player.getInventory().getItem(Inventory.SLOT_OFFHAND).getItem().getRegistryName()).toString();
        Inventory inventory = player.getInventory();

        // Searches for a recipe with an ingredient matching the RecipeId of the item in the off-hand.
        PestleRecipe recipe = world.getRecipeManager().getAllRecipesFor(PestleRecipeType.INSTANCE).stream()
                .filter(x -> Objects.requireNonNull(x.ingredient.getItem().getRegistryName()).toString()
                        .equals(recipeId)).findFirst().orElse(null);

        // Skip if no recipe was found
        if (recipe == null) return super.use(world, player, interactionHand);

        // If the event is running client-side, plays the rooted dirt step sound.
        if (world.isClientSide()) {
            world.playSound(player, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ROOTED_DIRT_STEP, player.getSoundSource(), 1.0F, 1.0F);

            return super.use(world, player, interactionHand);
        }

        // Check if the recipe is a match for the current environment
        if (!recipe.matches(inventory, world))
            return super.use(world, player, interactionHand);

        // Assembles the item stack to be returned and drops the item
        ItemStack result = recipe.assemble(inventory);
        ItemUtils.giveItem(result, player);

        // Damages the Pestle upon use, and removes 1 ingredient from the off-hand.
        ItemStack pestleItem = inventory.getSelected();
        pestleItem.setDamageValue(pestleItem.getDamageValue() + 1);
        inventory.getItem(Inventory.SLOT_OFFHAND).setCount(inventory.getItem(Inventory.SLOT_OFFHAND).getCount() - 1);

        return super.use(world, player, interactionHand);
    }
}
