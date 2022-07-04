package com.mrkelpy.kelpysfoodmod.world.gen.placement;

import com.mrkelpy.kelpysfoodmod.world.gen.features.HoneysuckleConfiguredFeature;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.ArrayList;

public class HoneysucklePlacedFeature {

    private static final PlacedFeature HONEYSUCKLE_PLACED =
            new PlacedFeature(HoneysuckleConfiguredFeature.get(), buildModifiers());

    /**
     * Creates a PlacementModifier list for the placement of the current feature.
     */
    private static ArrayList<PlacementModifier> buildModifiers() {

        ArrayList<PlacementModifier> modifiers = new ArrayList<>();
        modifiers.add(RarityFilter.onAverageOnceEvery(10));
        modifiers.add(InSquarePlacement.spread());
        modifiers.add(PlacementUtils.HEIGHTMAP);
        modifiers.add(BiomeFilter.biome());

        return modifiers;
    }

    /**
     * Returns the holder for the current placed feature. This is used
     * to register the feature in the worldgen.
     */
    public static Holder<PlacedFeature> get() {
        return Holder.direct(HONEYSUCKLE_PLACED);
    }

}

