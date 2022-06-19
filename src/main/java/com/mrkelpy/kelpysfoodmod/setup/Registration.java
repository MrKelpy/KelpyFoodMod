package com.mrkelpy.kelpysfoodmod.setup;

import com.mrkelpy.kelpysfoodmod.KelpysFoodMod;
import com.mrkelpy.kelpysfoodmod.items.Dango;
import com.mrkelpy.kelpysfoodmod.items.Pestle;
import com.mrkelpy.kelpysfoodmod.items.mochi.GreenMochi;
import com.mrkelpy.kelpysfoodmod.items.mochi.RedMochi;
import com.mrkelpy.kelpysfoodmod.items.mochi.WhiteMochi;
import com.mrkelpy.kelpysfoodmod.items.recipe.Pestle.PestleRecipeSerializer;
import com.mrkelpy.kelpysfoodmod.items.soup.Porridge;
import com.mrkelpy.kelpysfoodmod.items.soup.SeedSoup;
import com.mrkelpy.kelpysfoodmod.items.soup.SweetPorridge;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * This class is responsible for registering every object into the game.
 */
public class Registration {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, KelpysFoodMod.MODID);

    public static final DeferredRegister<RecipeSerializer<?>> RECIPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, KelpysFoodMod.MODID);


    /**
     * ITEMS REGISTRATION
     */
    public static final RegistryObject<Item> DANGO = ITEMS.register("dango", Dango::new);
    public static final RegistryObject<Item> RED_MOCHI = ITEMS.register("red_mochi", RedMochi::new);
    public static final RegistryObject<Item> WHITE_MOCHI = ITEMS.register("white_mochi", WhiteMochi::new);
    public static final RegistryObject<Item> GREEN_MOCHI = ITEMS.register("green_mochi", GreenMochi::new);
    public static final RegistryObject<Item> SEED_SOUP = ITEMS.register("seed_soup", SeedSoup::new);
    public static final RegistryObject<Item> PORRIDGE = ITEMS.register("porridge", Porridge::new);
    public static final RegistryObject<Item> SWEET_PORRIDGE = ITEMS.register("sweet_porridge", SweetPorridge::new);
    public static final RegistryObject<Item> PESTLE = ITEMS.register("pestle", Pestle::new);

    /**
     * RECIPE REGISTRATION
     */
    public static final RegistryObject<RecipeSerializer<?>> PESTLE_SERIALIZER =
            RECIPES.register("pestle", PestleRecipeSerializer::new);
}
