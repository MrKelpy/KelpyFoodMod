package com.mrkelpy.kelpysfoodmod.setup;
import com.mrkelpy.kelpysfoodmod.KelpysFoodMod;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {
    /**
     * This class handles all item, block, etc... registrations, mainly using Deferred Registers.
     */

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, KelpysFoodMod.MODID);

    // -------------------------------------------------------------------------- //
    /**
     * Dango Item Registration
     */
    private static final Item.Properties DANGO_PROPERTIES = new Item.Properties()
            .food(Foods.GOLDEN_CARROT)
            .tab(CreativeModeTab.TAB_FOOD);

    private static final RegistryObject<Item> DANGO =
            ITEMS.register("dango", () -> new Item(DANGO_PROPERTIES));

    // -------------------------------------------------------------------------- //
    /**
     * Mochi Item & Variants Registration
     */
    private static final Item.Properties MOCHI_PROPERTIES = new Item.Properties()
            .food(Foods.COOKIE)
            .tab(CreativeModeTab.TAB_FOOD);

    private static final RegistryObject<Item> RED_DANGO =
            ITEMS.register("red_mochi", () -> new Item(MOCHI_PROPERTIES));

    private static final RegistryObject<Item> WHITE_MOCHI =
            ITEMS.register("white_mochi", () -> new Item(MOCHI_PROPERTIES));

    private static final RegistryObject<Item> GREEN_MOCHI =
            ITEMS.register("green_mochi", () -> new Item(MOCHI_PROPERTIES));

}
