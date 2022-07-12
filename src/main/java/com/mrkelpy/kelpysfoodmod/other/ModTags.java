package com.mrkelpy.kelpysfoodmod.other;

import com.mrkelpy.kelpysfoodmod.KelpysFoodMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModTags {

    public static TagKey<Biome> PURE_BIOMES =
            TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(KelpysFoodMod.MODID, "pure"));

}

