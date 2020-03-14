package com.unrealdinnerbone.tobrirsgarden.util;

import com.unrealdinnerbone.tobrirsgarden.TobrirsBlocks;
import com.unrealdinnerbone.tobrirsgarden.TobrirsGarden;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TorbrisItemGroup extends ItemGroup {

    public TorbrisItemGroup() {
        super(TobrirsGarden.MOD_ID);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(TobrirsBlocks.SOULSNAD.getBlock());
    }
}
