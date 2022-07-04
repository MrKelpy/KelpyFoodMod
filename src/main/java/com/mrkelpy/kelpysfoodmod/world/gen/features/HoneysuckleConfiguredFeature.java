package com.mrkelpy.kelpysfoodmod.world.gen.features;

import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class HoneysuckleConfiguredFeature {

    private static final ConfiguredFeature<?, ?> HONEYSUCKLE_FEATURE =
                new ConfiguredFeature<>(Feature.RANDOM_PATCH, buildConfig());

    /**
     * Returns the specific feature configuration for the current feature. This configuration
     * can be any config that inherits from FeatureConfiguration.
     */
    private static RandomPatchConfiguration buildConfig() {

        return new RandomPatchConfiguration(32, 3, 5, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(Registration.HONEYSUCKLE_FLOWER.get()))));

    }

    /**
     * Returns the holder with the configured feature for the current feature. This is used
     * for the placement feature.
     */
    public static Holder<ConfiguredFeature<?, ?>> get() {
        return Holder.direct(HONEYSUCKLE_FEATURE);
    }

}

