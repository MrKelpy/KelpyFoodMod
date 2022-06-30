package com.mrkelpy.kelpysfoodmod.items.tools;

import com.mrkelpy.kelpysfoodmod.recipe.Pestle.PestleRecipe;
import com.mrkelpy.kelpysfoodmod.recipe.Pestle.PestleRecipeType;
import com.mrkelpy.kelpysfoodmod.setup.Registration;
import com.mrkelpy.kelpysfoodmod.utils.ItemUtils;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

/**
 * This class implements all the logic and features of the Pestle item.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class PestleItem extends Item {

    public static Item.Properties itemProperties = PestleItem.buildProperties();

    public PestleItem() {
        super(itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Item.Properties buildProperties() {

        Properties properties = new Properties();
        properties.tab(Registration.CUSTOM_TAB_ITEMS);
        properties.stacksTo(1);
        properties.durability(250);

        return properties;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.EAT;
    }


    /**
     * Extends the functionality of using the item (right-clicking) to crafting a certain recipe.
     * <br>
     * This will use PestleRecipe, as per the official datapack forge recipe format.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand interactionHand) {

        String offhandItemName = Objects.requireNonNull(player.getInventory().getItem(Inventory.SLOT_OFFHAND).getItem().getRegistryName()).toString();
        Inventory inventory = player.getInventory();

        // Searches for a recipe with an ingredient matching the registry name of the item in the off-hand.
        PestleRecipe recipe = world.getRecipeManager().getAllRecipesFor(PestleRecipeType.INSTANCE).stream().filter(x -> Objects.requireNonNull(x.ingredient.getItem().getRegistryName()).toString().equals(offhandItemName)).findFirst().orElse(null);

        // Skip if no recipe was found or the recipe doesn't match for the current environment.
        if (recipe == null || !recipe.matches(inventory, world) )
            return super.use(world, player, interactionHand);

        // Plays a "mashing" sound upon use.
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ROOTED_DIRT_STEP, SoundSource.PLAYERS, 0.6F, 2.0F);

        // Serverside assertion
        if (world.isClientSide()) return super.use(world, player, interactionHand);

        // If the player is not holding shift, only one item will be crafted at use.
        if (!player.isShiftKeyDown()) {

            this.executeNormalBehaviour(recipe, inventory, player);
            return super.use(world, player, interactionHand);
        }

        // If the player is holding shift, all the items in the offhand will be crafted at use
        int ingredientCount = inventory.getItem(Inventory.SLOT_OFFHAND).getCount();
        for (int i = 0; i < ingredientCount; i++) {

            // If the item fails to be created (due to the lack of durability), break the loop.
            if (!this.executeNormalBehaviour(recipe, inventory, player))
                break;
        }

        return super.use(world, player, interactionHand);
    }

    /**
     * Executes the normal behaviour for the Pestle Craftings, after all the checks have been made.
     * @param recipe [PestleRecipe] The recipe to be crafted.
     * @param inventory [Inventory] The inventory of the player.
     * @param player [Player] The player who is crafting the recipe.
     */
    private boolean executeNormalBehaviour(PestleRecipe recipe, Inventory inventory, Player player) {

        // Assembles the item stack to be returned and gives the item to the player
        ItemStack pestleItem = inventory.getSelected();
        if (pestleItem.getDamageValue() >= pestleItem.getMaxDamage()) return false;

        // Assembles the item, damages the pestle, gives the item to the player and decrements the ingredients.
        ItemStack product = recipe.assemble(inventory);
        pestleItem.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
        ItemUtils.giveItem(product, player);

        if (!recipe.getRemainderFlag())
            inventory.getItem(Inventory.SLOT_OFFHAND).setCount(inventory.getItem(Inventory.SLOT_OFFHAND).getCount() - 1);

        return true;
    }
}
