package com.mrkelpy.kelpysfoodmod.setup;

import com.mrkelpy.kelpysfoodmod.KelpysFoodMod;
import com.mrkelpy.kelpysfoodmod.items.Dango;
import com.mrkelpy.kelpysfoodmod.items.mochi.GreenMochi;
import com.mrkelpy.kelpysfoodmod.items.mochi.RedMochi;
import com.mrkelpy.kelpysfoodmod.items.mochi.WhiteMochi;
import com.mrkelpy.kelpysfoodmod.items.soup.Porridge;
import com.mrkelpy.kelpysfoodmod.items.soup.SeedSoup;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, KelpysFoodMod.MODID);

    /**
     * ITEMS REGISTRATION
     */
    public static final RegistryObject<Item> DANGO = ITEMS.register("dango", Dango::new);
    public static final RegistryObject<Item> RED_MOCHI = ITEMS.register("red_mochi", RedMochi::new);
    public static final RegistryObject<Item> WHITE_MOCHI = ITEMS.register("white_mochi", WhiteMochi::new);
    public static final RegistryObject<Item> GREEN_MOCHI = ITEMS.register("green_mochi", GreenMochi::new);
    public static final RegistryObject<Item> SEED_SOUP = ITEMS.register("seed_soup", SeedSoup::new);
    public static final RegistryObject<Item> PORRIDGE = ITEMS.register("porridge", Porridge::new);
}
