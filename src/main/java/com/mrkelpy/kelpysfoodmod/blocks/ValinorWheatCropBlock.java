package com.mrkelpy.kelpysfoodmod.blocks;

import com.mrkelpy.kelpysfoodmod.other.ModTags;
import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;


/**
 * This class implements the logic and features of the VanilorWheatCropBlock block.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ValinorWheatCropBlock extends CropBlock {

    private static int MAX_AGE = 7;
    private static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    private static final BooleanProperty IS_WILTED = BooleanProperty.create("wilted");

    public ValinorWheatCropBlock() {
        super(buildBlockProperties());
        this.registerDefaultState(this.defaultBlockState().setValue(IS_WILTED, false));
    }

    /**
     * This method builds the block properties for the VanilorWheatCropBlock block.
     */
    private static Properties buildBlockProperties() {

        Properties cropProperties = Properties.copy(Blocks.WHEAT);
        cropProperties.noCollission();
        cropProperties.noOcclusion();
        return cropProperties;
    }

    /**
     * Uses the randomly generated seed for a given block position and returns the wilting age for a block
     * based on the last number on the seed.
     *
     * @return The crop wilting age
     */
    public int getCropWiltAge(BlockPos pPos) {

        // Gets the remainder of the division of the seed by 10, which turns the last digit into a decimal place.
        if (Mth.getSeed(pPos) % 10 <= 3) return 5;
        if (Mth.getSeed(pPos) % 10 <= 6) return 4;
        return 3;
    }

    /**
     * Checks if the adjacent blocks are either air or the same block as the block being harvested.
     * By "Adjacent" I mean all blocks touching the crop, including diagonals. A 3x3 Grid.
     *
     * @return Whether or not the adjacent blocks match the criteria
     */
    public boolean validateAdjacentBlocks(BlockState pState, ServerLevel pLevel, BlockPos pPos) {

        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {

                Block block = pLevel.getBlockState(mutableBlockPos.set(pPos.getX() + x, pPos.getY(), pPos.getZ() + z)).getBlock();
                if (block != Blocks.AIR && block != pState.getBlock() && !pState.getValue(IS_WILTED))
                    return false;
            }
        }
        return true;

    }

    /**
     * Expands upon the randomTick functionality to toggle the isWilted property if the following conditions aren't met:
     *
     * <ol>
     *     <li>The current biome is a "pure" biome. Pure biomes are defined in the pure.json tag.</li>
     *     <li>The current age is between 3 and 5 (inclusive).</li>
     *     <li>The crop does not have skylight.</li>
     *     <li>The adjacent blocks aren't either air or a healthy valinor wheat crop</li>
     * </ol>
     * <p>
     * Aditionallly, the crop will not attempt to grow at night, and the wilting can happen anywhere
     * from stage 3 to 5 for the crop.
     */
    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {

        // Does basic random tick logic prevention evaluations
        if (pLevel.isNight() || !pLevel.isAreaLoaded(pPos, 1)) return;
        if (this.getAge(pState) > MAX_AGE || pState.getValue(IS_WILTED)) return;

        // Evaluates whether the crop will wilt or not
        boolean isPureBiome = pLevel.getBiome(pPos).is(ModTags.PURE_BIOMES);
        boolean canWilt = this.getCropWiltAge(pPos) == this.getAge(pState);
        boolean hasSkylight = pLevel.canSeeSky(pPos.above());
        boolean isAdjacentValid = this.validateAdjacentBlocks(pState, pLevel, pPos);

        // Grows the crop normally if it won't wilt
        if ((isPureBiome && hasSkylight && isAdjacentValid) || !canWilt) {
            super.randomTick(pState, pLevel, pPos, pRandom);
            return;
        }

        // Grows by setting in the blockstate for the wilted version of the crop.
        float growthSpeed = CropBlock.getGrowthSpeed(pState.getBlock(), pLevel, pPos);
        if (ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt((int) (25.0F / growthSpeed) + 1) == 0)) {
            pLevel.setBlock(pPos, pState.setValue(IS_WILTED, true), 2);
            ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
        }
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
        return Registration.VALINOR_WHEAT_SEEDS.get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
        pBuilder.add(IS_WILTED);
    }

    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return PlantType.get("drought");
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return false;
    }
}

