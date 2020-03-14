package com.unrealdinnerbone.tobrirsgarden.events;

import com.unrealdinnerbone.tobrirsgarden.TobrirsBlocks;
import com.unrealdinnerbone.tobrirsgarden.TobrirsGarden;
import com.unrealdinnerbone.tobrirsgarden.util.IItemProperitesProvider;
import com.unrealdinnerbone.tobrirsgarden.util.IRecipeCreater;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import java.util.Arrays;
import java.util.function.Consumer;

public class DataGenerator
{

    @SubscribeEvent
    public static void onDataGen(GatherDataEvent event) {
        event.getGenerator().addProvider(new RecipeProvider(event.getGenerator()));
        event.getGenerator().addProvider(new BlockModelProvider(event.getGenerator(), TobrirsGarden.MOD_ID, event.getExistingFileHelper()));
        event.getGenerator().addProvider(new ItemModelProvider(event.getGenerator(), TobrirsGarden.MOD_ID, event.getExistingFileHelper()));
    }

    public static class RecipeProvider extends net.minecraft.data.RecipeProvider {

        public RecipeProvider(net.minecraft.data.DataGenerator generatorIn) {
            super(generatorIn);
        }

        @Override
        protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
            Arrays.stream(TobrirsBlocks.ALL).filter(tobrirsBlocks -> tobrirsBlocks.getBlockItem() != null).filter(tobrirsBlocks -> tobrirsBlocks.getBlock() instanceof IRecipeCreater).forEach(tobrirsBlocks -> ((IRecipeCreater) tobrirsBlocks.getBlock()).create(tobrirsBlocks.getBlockItem(), this, consumer));
        }

        @Override
        public InventoryChangeTrigger.Instance hasItem(ItemPredicate... predicates) {
            return super.hasItem(predicates);
        }

        @Override
        public InventoryChangeTrigger.Instance hasItem(Tag<Item> tagIn) {
            return super.hasItem(tagIn);
        }

        @Override
        public InventoryChangeTrigger.Instance hasItem(IItemProvider itemIn) {
            return super.hasItem(itemIn);
        }
    }

    public static class BlockModelProvider extends BlockStateProvider {

        public BlockModelProvider(net.minecraft.data.DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
            super(gen, modid, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {
            Arrays.stream(TobrirsBlocks.ALL).filter(blocks -> blocks.getBlockItem() != null).filter(blocks -> blocks.getBlock() instanceof IItemProperitesProvider).forEach(blocks -> ((IItemProperitesProvider) blocks.getBlock()).registerStatesAndModels(blocks.getBlock(), this));

        }
    }

    public static class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {

        public ItemModelProvider(net.minecraft.data.DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
            super(generator, modid, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            Arrays.stream(TobrirsBlocks.ALL).filter(blocks -> blocks.getBlockItem() != null).filter(blocks -> blocks.getBlock() instanceof IItemProperitesProvider).forEach(blocks -> ((IItemProperitesProvider) blocks.getBlock()).registerModels(blocks.getBlock(), this));
        }

        @Override
        public String getName() {
            return TobrirsGarden.MOD_ID;
        }
    }


}
