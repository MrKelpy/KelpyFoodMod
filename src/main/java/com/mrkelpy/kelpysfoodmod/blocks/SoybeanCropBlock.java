package com.mrkelpy.kelpysfoodmod.blocks;

import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.PlantType;

import javax.annotation.ParametersAreNonnullByDefault;


/**
 * This class implements the logic and features of the SoybeanCropBlock block.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SoybeanCropBlock extends CropBlock {

    private static final int MAX_AGE = 9;
    private static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);
    private static final VoxelShape[] SHAPE_BY_AGE = getCropVoxelShapeArray();

    public SoybeanCropBlock() {
        super(buildBlockProperties());
    }

    /**
     * This method builds the block properties for the SoybeanCropBlock block.
     */
    private static Properties buildBlockProperties() {

        BlockBehaviour.Properties cropProperties = BlockBehaviour.Properties.copy(Blocks.POTATOES);
        cropProperties.noOcclusion();
        return cropProperties;
    }

    /**
     * This method returns the shapes of the SoybeanCropBlock block in compliance to the age of the crop.
     */
    private static VoxelShape[] getCropVoxelShapeArray() {

        VoxelShape[] voxelShapeArray = new VoxelShape[MAX_AGE + 1];

        for (int i = 0; i < MAX_AGE + 1; i++) {
            double yVoxelValue = 2 + (2 * i) <= 16 ? 2 + (2 * i) : 16.0D;
            voxelShapeArray[i] = Block.box(0.0D, 0.0D, 0.0D, 16.0D, yVoxelValue, 16.0D);
        }

        return voxelShapeArray;
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
        return Registration.SOYBEAN.get();
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(Blocks.COARSE_DIRT) || pState.is(Blocks.DIRT) || pState.is(Blocks.ROOTED_DIRT);
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
        return PlantType.get("dry");
    }
}

