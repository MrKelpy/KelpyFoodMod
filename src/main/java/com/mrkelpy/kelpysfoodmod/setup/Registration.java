package com.mrkelpy.kelpysfoodmod.setup;

import com.mrkelpy.kelpysfoodmod.KelpysFoodMod;
import com.mrkelpy.kelpysfoodmod.blocks.RiceCropBlock;
import com.mrkelpy.kelpysfoodmod.blocks.SoybeanCropBlock;
import com.mrkelpy.kelpysfoodmod.blocks.ValinorWheatCropBlock;
import com.mrkelpy.kelpysfoodmod.items.food.general.*;
import com.mrkelpy.kelpysfoodmod.items.food.ricestuffs.*;
import com.mrkelpy.kelpysfoodmod.items.food.soups.*;
import com.mrkelpy.kelpysfoodmod.items.tools.PestleItem;
import com.mrkelpy.kelpysfoodmod.other.CreativeTabFoods;
import com.mrkelpy.kelpysfoodmod.other.CreativeTabItems;
import com.mrkelpy.kelpysfoodmod.recipe.NoBucketRemainder.NoBucketRemainderRecipeSerializer;
import com.mrkelpy.kelpysfoodmod.recipe.Pestle.PestleRecipeSerializer;
import com.mrkelpy.kelpysfoodmod.recipe.Pestle.PestleRecipeType;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * This class is responsible for registering every object into the game.
 */
public class Registration {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, KelpysFoodMod.MODID);

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, KelpysFoodMod.MODID);

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, KelpysFoodMod.MODID);

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, KelpysFoodMod.MODID);


    /**
     * CREATIVE TAB "REGISTRATION"
     * <br>
     * "Registration" is quoted because it isn't really registration, but it's here for convention
     * and accessibility. A better term to refer to it, would be instantiation.
     */
    public static final CreativeModeTab CUSTOM_TAB_FOODS = new CreativeTabFoods();
    public static final CreativeModeTab CUSTOM_TAB_ITEMS = new CreativeTabItems();

    /**
     * BLOCKS REGISTRATION
     */
    public static final RegistryObject<Block> SOYBEAN_PLANT = BLOCKS.register("soybean_plant", SoybeanCropBlock::new);
    public static final RegistryObject<Block> RICE_PLANT = BLOCKS.register("rice_plant", RiceCropBlock::new);
    public static final RegistryObject<Block> VALINOR_WHEAT_PLANT = BLOCKS.register("valinor_wheat_plant", ValinorWheatCropBlock::new);
    /**
     * ITEMS REGISTRATION
     */
    public static final RegistryObject<Item> DANGO = ITEMS.register("dango", DangoItem::new);
    public static final RegistryObject<Item> RED_MOCHI = ITEMS.register("red_mochi", RedMochiItem::new);
    public static final RegistryObject<Item> WHITE_MOCHI = ITEMS.register("white_mochi", WhiteMochiItem::new);
    public static final RegistryObject<Item> GREEN_MOCHI = ITEMS.register("green_mochi", GreenMochiItem::new);
    public static final RegistryObject<Item> SEED_SOUP = ITEMS.register("seed_soup", SeedSoupItem::new);
    public static final RegistryObject<Item> PORRIDGE = ITEMS.register("porridge", PorridgeItem::new);
    public static final RegistryObject<Item> SWEET_PORRIDGE = ITEMS.register("sweet_porridge", SweetPorridgeItem::new);
    public static final RegistryObject<Item> MEAT_SKEWER = ITEMS.register("meat_skewer", MeatSkewerItem::new);
    public static final RegistryObject<Item> BOILED_EGG = ITEMS.register("boiled_egg", BoiledEggItem::new);
    public static final RegistryObject<Item> COAGULANT = ITEMS.register("coagulant", CoagulantItem::new);
    public static final RegistryObject<Item> COAGULATED_MILK = ITEMS.register("coagulated_milk", CoagulatedMilkItem::new);
    public static final RegistryObject<Item> COAGULATED_SOY_MILK = ITEMS.register("coagulated_soy_milk", CoagulatedSoyMilkItem::new);
    public static final RegistryObject<Item> SOYBEAN = ITEMS.register("soybean", SoybeanItem::new);
    public static final RegistryObject<Item> SOY_MILK = ITEMS.register("soy_milk", SoyMilkItem::new);
    public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese", CheeseItem::new);
    public static final RegistryObject<Item> TOFU = ITEMS.register("tofu", TofuItem::new);
    public static final RegistryObject<Item> RICE_SEEDS = ITEMS.register("rice_seeds", RiceSeedsItem::new);
    public static final RegistryObject<Item> RICE_STASH = ITEMS.register("rice_stash", RiceStashItem::new);
    public static final RegistryObject<Item> ONIGIRI = ITEMS.register("onigiri", OnigiriItem::new);
    public static final RegistryObject<Item> ONIGIRI_KELP = ITEMS.register("onigiri_kelp", OnigiriKelpItem::new);
    public static final RegistryObject<Item> VALINOR_WHEAT_SEEDS = ITEMS.register("valinor_wheat_seeds", ValinorWheatSeedsItem::new);
    public static final RegistryObject<Item> VALINOR_WHEAT = ITEMS.register("valinor_wheat", ValinorWheatItem::new);
    public static final RegistryObject<Item> LEMBAS = ITEMS.register("lembas", LembasItem::new);
    public static final RegistryObject<Item> PESTLE = ITEMS.register("pestle", PestleItem::new);


    /**
     * RECIPE REGISTRATION
     */
    public static final RegistryObject<RecipeSerializer<?>> PESTLE_SERIALIZER =
            RECIPE_SERIALIZERS.register(PestleRecipeSerializer.ID, PestleRecipeSerializer::new);

    public static final RegistryObject<RecipeType<?>> PESTLE_RECIPE_TYPE =
            RECIPE_TYPES.register(PestleRecipeType.ID, PestleRecipeType::new);

    public static final RegistryObject<RecipeSerializer<?>> NO_BUCKET_SERIALIZER =
            RECIPE_SERIALIZERS.register(NoBucketRemainderRecipeSerializer.ID, NoBucketRemainderRecipeSerializer::new);

}
