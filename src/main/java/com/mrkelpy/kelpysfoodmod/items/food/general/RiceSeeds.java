package com.mrkelpy.kelpysfoodmod.items.food.general;

import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class implements all the logic and features of the Rice Seeds item.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RiceSeeds extends ItemNameBlockItem {

    public static final Properties itemProperties = RiceSeeds.buildProperties();

    public RiceSeeds() {
        super(Registration.RICE_PLANT.get(), itemProperties);
    }

    /**
     * Handles the creation of the item properties for this item.
     * @return [Item.Properties] The item properties.
     */
    private static Properties buildProperties() {

        Properties properties = new Properties();
        properties.tab(CreativeModeTab.TAB_MISC);

        return properties;
    }

    /**
     * Checks if the default position that the block will be placed on overlaps the water block,
     * if so, places it one block above the water block.
     */
    @Override
    protected boolean placeBlock(BlockPlaceContext pContext, BlockState pState) {

        Level world = pContext.getLevel();
        BlockPos placementPosition = world.getBlockState(pContext.getClickedPos()).is(Blocks.WATER)
                ? pContext.getClickedPos().above() : pContext.getClickedPos();
        return pContext.getLevel().setBlock(placementPosition, pState, 11);
    }

    @Override
    protected SoundEvent getPlaceSound(BlockState state, Level world, BlockPos pos, Player entity) {
        return SoundEvents.CROP_PLANTED;
    }
}
