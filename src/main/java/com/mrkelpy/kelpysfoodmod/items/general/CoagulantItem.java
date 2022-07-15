package com.mrkelpy.kelpysfoodmod.items.general;

import com.mrkelpy.kelpysfoodmod.setup.Registration;
import com.mrkelpy.kelpysfoodmod.utils.ItemUtils;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class implements all the logic and features of the Coagulant item.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CoagulantItem extends Item {

    private static final Properties itemProperties = CoagulantItem.buildProperties();

    public CoagulantItem() {
        super(itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Properties buildProperties() {

        Properties properties = new Properties();
        properties.food(new FoodProperties.Builder().nutrition(0).saturationMod(0).alwaysEat().build());
        properties.tab(Registration.CUSTOM_TAB_ITEMS);

        return properties;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.DRINK;
    }

    /**
     * Expands upon the behaviour of the finishUsingItem method to give a stick back to the player
     * after finishing drinking the Coagulant.
     * @param itemStack The ItemStack of the Coagulant.
     * @param world The level where the item was used.
     * @param livingEntity The entity that finished using the item
     * @return [ItemStack]
     */
    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level world, LivingEntity livingEntity) {

        if (!world.isClientSide() && livingEntity instanceof ServerPlayer serverplayer) {
            ItemUtils.giveItem(new ItemStack(Items.GLASS_BOTTLE), serverplayer);
            serverplayer.addEffect(new MobEffectInstance(MobEffects.POISON, 3*20, 3));
            serverplayer.addEffect(new MobEffectInstance(MobEffects.HUNGER, 10*20, 3));
        }

        return super.finishUsingItem(itemStack, world, livingEntity);
    }
}
