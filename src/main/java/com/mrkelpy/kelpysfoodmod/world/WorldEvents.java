package com.mrkelpy.kelpysfoodmod.world;

import com.mrkelpy.kelpysfoodmod.world.gen.placement.HoneysucklePlacedFeature;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WorldEvents {

    @SubscribeEvent
    public void setupBiomeGen(BiomeLoadingEvent event) {

        if (event.getName() == null) return;
        ResourceKey<Biome> biome = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());

        if (biome == Biomes.MEADOW || biome == Biomes.FLOWER_FOREST || biome == Biomes.BIRCH_FOREST || biome == Biomes.OLD_GROWTH_BIRCH_FOREST)
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HoneysucklePlacedFeature.get());

    }
}

