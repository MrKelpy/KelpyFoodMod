package com.mrkelpy.kelpysfoodmod.items;

import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;

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
}
