package com.unrealdinnerbone.tobrirsgarden.events;

import com.unrealdinnerbone.tobrirsgarden.TobrirsBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Arrays;

public class RegisteryEvents
{

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Block.class, RegisteryEvents::registerBlocks);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Item.class, RegisteryEvents::registerItems);
    }

    private static void registerBlocks(RegistryEvent.Register<Block> event) {
        Arrays.stream(TobrirsBlocks.ALL).forEach(value -> event.getRegistry().register(value.getBlock()));
    }

    private static void registerItems(RegistryEvent.Register<Item> event) {
        Arrays.stream(TobrirsBlocks.ALL).filter(tobrirsBlocks -> tobrirsBlocks.getBlockItem() != null).forEach(value -> event.getRegistry().register(value.getBlockItem()));
//        Arrays.stream(TobrirsItems.ALL).forEach(value -> event.getRegistry().register(value.getItem()));

    }
}
