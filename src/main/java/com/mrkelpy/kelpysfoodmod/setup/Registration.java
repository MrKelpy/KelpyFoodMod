package com.mrkelpy.kelpysfoodmod.setup;

import com.mrkelpy.kelpysfoodmod.KelpysFoodMod;
import com.mrkelpy.kelpysfoodmod.items.general.*;
import com.mrkelpy.kelpysfoodmod.items.mediums.Coagulant;
import com.mrkelpy.kelpysfoodmod.items.mochi.GreenMochi;
import com.mrkelpy.kelpysfoodmod.items.mochi.RedMochi;
import com.mrkelpy.kelpysfoodmod.items.mochi.WhiteMochi;
import com.mrkelpy.kelpysfoodmod.items.soup.*;
import com.mrkelpy.kelpysfoodmod.items.mediums.Pestle;
import com.mrkelpy.kelpysfoodmod.recipe.Pestle.PestleRecipeSerializer;
import com.mrkelpy.kelpysfoodmod.recipe.Pestle.PestleRecipeType;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * This class is responsible for registering every object into the game.
 */
public class Registration {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, KelpysFoodMod.MODID);

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, KelpysFoodMod.MODID);

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, KelpysFoodMod.MODID);


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
    public static final RegistryObject<Item> MEAT_SKEWER = ITEMS.register("meat_skewer", MeatSkewer::new);
    public static final RegistryObject<Item> BOILED_EGG = ITEMS.register("boiled_egg", BoiledEgg::new);
    public static final RegistryObject<Item> COAGULANT = ITEMS.register("coagulant", Coagulant::new);
    public static final RegistryObject<Item> COAGULATED_MILK = ITEMS.register("coagulated_milk", CoagulatedMilk::new);
    public static final RegistryObject<Item> COAGULATED_SOY_MILK = ITEMS.register("coagulated_soy_milk", CoagulatedSoyMilk::new);
    public static final RegistryObject<Item> SOYBEAN = ITEMS.register("soybean", Soybean::new);
    public static final RegistryObject<Item> SOY_MILK = ITEMS.register("soy_milk", SoyMilk::new);
    public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese", Cheese::new);
    public static final RegistryObject<Item> TOFU = ITEMS.register("tofu", Tofu::new);
    public static final RegistryObject<Item> PESTLE = ITEMS.register("pestle", Pestle::new);

    /**
     * RECIPE REGISTRATION
     */
    public static final RegistryObject<RecipeSerializer<?>> PESTLE_SERIALIZER =
            RECIPE_SERIALIZERS.register(PestleRecipeType.ID, PestleRecipeSerializer::new);

    public static final RegistryObject<RecipeType<?>> PESTLE_RECIPE_TYPE =
            RECIPE_TYPES.register(PestleRecipeType.ID, PestleRecipeType::new);
}
