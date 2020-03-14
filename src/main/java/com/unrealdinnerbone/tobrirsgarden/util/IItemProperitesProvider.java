package com.unrealdinnerbone.tobrirsgarden.util;

import com.unrealdinnerbone.tobrirsgarden.TobrirsGarden;
import com.unrealdinnerbone.tobrirsgarden.events.DataGenerator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public interface IItemProperitesProvider
{
    default Item.Properties getItemProperties() {
        return TobrirsGarden.BASIC;
    }

    default void registerStatesAndModels(Block block, DataGenerator.BlockModelProvider blockModelProvider) {

    }

    default void registerModels(Block block, DataGenerator.ItemModelProvider itemModelProvider) {

    }

}
