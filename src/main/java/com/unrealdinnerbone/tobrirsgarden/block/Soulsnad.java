package com.unrealdinnerbone.tobrirsgarden.block;

import com.unrealdinnerbone.tobrirsgarden.TobrirsBlocks;
import com.unrealdinnerbone.tobrirsgarden.TobrirsGarden;
import com.unrealdinnerbone.tobrirsgarden.events.DataGenerator;
import com.unrealdinnerbone.tobrirsgarden.util.IItemProperitesProvider;
import com.unrealdinnerbone.tobrirsgarden.util.IRecipeCreater;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import java.util.Random;
import java.util.function.Consumer;

public class Soulsnad extends SoulSandBlock implements IRecipeCreater, IItemProperitesProvider {

    public Soulsnad() {
        super(Block.Properties.create(Material.SAND, MaterialColor.BROWN).tickRandomly().hardnessAndResistance(0.5F).speedFactor(0.2F).sound(SoundType.SAND));
    }

    @Override
    public int tickRate(IWorldReader worldIn) {
        return TobrirsGarden.tickAmount.get();
    }

    @Override
    public void create(Item item, DataGenerator.RecipeProvider recipeProvider, Consumer<IFinishedRecipe> iFinishedRecipeConsumer) {
        ShapelessRecipeBuilder.shapelessRecipe(TobrirsBlocks.SOULSNAD.getBlock()).addIngredient(Items.SOUL_SAND, 2)
                .addCriterion("has_soulsand", recipeProvider.hasItem(Items.SOUL_SAND))
                .setGroup(TobrirsGarden.MOD_ID)
                .build(iFinishedRecipeConsumer);
    }

    @Override
    public void randomTick(BlockState blockState, ServerWorld world, BlockPos blockPos, Random random) {
        super.randomTick(blockState, world, blockPos, random);
        BlockPos aboveBlockPos = blockPos.up();
        BlockState aboveBlockState = world.getBlockState(aboveBlockPos);
        Block blockAbove = aboveBlockState.getBlock();
        if (blockAbove instanceof NetherWartBlock) {
            if (canSustainPlant(world.getBlockState(blockPos), world, blockPos, Direction.UP, (IPlantable) blockAbove)) {
                try {
                    blockAbove.tick(aboveBlockState, world, aboveBlockPos, random);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public boolean canSustainPlant(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos, Direction direction, IPlantable iPlantable) {
        return iPlantable.getPlantType(iBlockReader, blockPos.up()) == PlantType.Nether;
    }

    @Override
    public void registerStatesAndModels(Block block, DataGenerator.BlockModelProvider blockModelProvider) {
        blockModelProvider.simpleBlock(block, blockModelProvider.models().cubeAll(block.getRegistryName().getPath(), new ResourceLocation("minecraft", "block/soul_sand")));
    }

    @Override
    public void registerModels(Block block, DataGenerator.ItemModelProvider itemModelProvider) {
        itemModelProvider.cubeAll(block.getRegistryName().getPath(), new ResourceLocation("minecraft", "block/soul_sand"));
    }

}
