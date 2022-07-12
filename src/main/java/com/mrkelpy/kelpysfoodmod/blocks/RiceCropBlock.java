package com.mrkelpy.kelpysfoodmod.blocks;

import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.PlantType;

import javax.annotation.ParametersAreNonnullByDefault;


/**
 * This class implements the logic and features of the RiceCropBlock block.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RiceCropBlock extends CropBlock {

    private static final int MAX_AGE = 3;
    private static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape[] SHAPE_BY_AGE = getCropVoxelShapeArray();

    public RiceCropBlock() {
        super(buildBlockProperties());
    }

    /**
     * This method builds the block properties for the RiceCropBlock block.
     */
    private static Properties buildBlockProperties() {

        Properties cropProperties = Properties.of(Material.PLANT, MaterialColor.COLOR_YELLOW);
        cropProperties.instabreak();
        cropProperties.noOcclusion();
        cropProperties.noCollission();
        cropProperties.sound(SoundType.CROP);
        return cropProperties;
    }

    /**
     * This method returns the shapes of the RiceCropBlock block in compliance to the age of the crop.
     */
    private static VoxelShape[] getCropVoxelShapeArray() {

        VoxelShape[] voxelShapeArray = new VoxelShape[MAX_AGE + 1];

        for (int i = 0; i < MAX_AGE + 1; i++) {
            voxelShapeArray[i] = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2 + (2.5 * i), 16.0D);
        }

        return voxelShapeArray;
    }

    /**
     * Rice can only be planted in shallow water;
     * <br>
     * Checks if the target fluid is water, and if the block ontop of the target is air, and if the
     * one at the bottom isn't a fluid.
     * <br>
     * WARNING: When placing a crop down, this method will be called on all the adjacent crop blocks. This will also propagate down to the rest
     */
    @Override
    public boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {

        boolean isValidTop = pLevel.getBlockState(pPos.above()).is(Blocks.AIR) || pLevel.getBlockState(pPos.above()).is(Registration.RICE_PLANT.get());
        boolean isWaterOnMiddle = pLevel.getFluidState(pPos).is(Fluids.WATER);
        boolean isSolidBlockBelow = pLevel.getBlockState(pPos.below()).isFaceSturdy(pLevel, pPos.below(), Direction.UP);

        return isValidTop && isWaterOnMiddle && isSolidBlockBelow;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public ItemLike getBaseSeedId() {
        return Registration.RICE_SEEDS.get();
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_AGE[pState.getValue(this.getAgeProperty())];
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return PlantType.get("drought");
    }
}

