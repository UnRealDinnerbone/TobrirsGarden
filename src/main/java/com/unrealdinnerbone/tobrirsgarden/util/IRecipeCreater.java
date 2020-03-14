package com.unrealdinnerbone.tobrirsgarden.util;

import com.unrealdinnerbone.tobrirsgarden.events.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;

import java.util.function.Consumer;

public interface IRecipeCreater {
    void create(Item item, DataGenerator.RecipeProvider recipeProvider, Consumer<IFinishedRecipe> iFinishedRecipeConsumer);
}
