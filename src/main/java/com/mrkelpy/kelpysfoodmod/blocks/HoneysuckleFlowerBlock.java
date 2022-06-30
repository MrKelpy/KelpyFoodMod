package com.mrkelpy.kelpysfoodmod.blocks;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class HoneysuckleFlowerBlock extends FlowerBlock {

    public HoneysuckleFlowerBlock() {
        super(MobEffects.MOVEMENT_SPEED, 20*3, buildBlockProperties());
    }

    /**
     * This method builds the block properties for the HoneysuckleFlowerBlock block.
     */
    private static Properties buildBlockProperties() {
        Properties properties = Properties.of(Material.PLANT, MaterialColor.QUARTZ);
        properties.instabreak();
        properties.noOcclusion();
        properties.noCollission();
        properties.sound(SoundType.CROP);
        return properties;
    }

}

