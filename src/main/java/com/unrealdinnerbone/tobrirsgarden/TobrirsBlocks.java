package com.unrealdinnerbone.tobrirsgarden;

import com.unrealdinnerbone.tobrirsgarden.block.Soulsnad;
import com.unrealdinnerbone.tobrirsgarden.util.IItemProperitesProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.function.Supplier;

public enum TobrirsBlocks
{
    SOULSNAD(Soulsnad::new);

    private final Block block;
    private BlockItem blockItem;
    private final ResourceLocation resourceLocation;

    public static final TobrirsBlocks[] ALL = values();

    TobrirsBlocks(Supplier<Block> blockConsumer) {
        this.resourceLocation = new ResourceLocation(TobrirsGarden.MOD_ID, name().toLowerCase());
        this.block = blockConsumer.get().setRegistryName(resourceLocation);
        if(block instanceof IItemProperitesProvider) {
            this.blockItem = new BlockItem(block, ((IItemProperitesProvider) block).getItemProperties());
            this.blockItem.setRegistryName(resourceLocation);
        }

    }

    public Block getBlock() {
        return block;
    }

    public BlockItem getBlockItem() {
        return blockItem;
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

}
