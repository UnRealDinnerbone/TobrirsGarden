//package com.unrealdinnerbone.tobrirsgarden;
//
//import net.minecraft.item.Item;
//import net.minecraft.util.ResourceLocation;
//
//import java.util.function.Supplier;
//
//public enum TobrirsItems
//{
//    RANDOM(() -> {
//        return new Item(new Item.Properties());
//    });
//
//    private final Item item;
//    public static final TobrirsItems[] ALL = values();
//
//    TobrirsItems(Supplier<Item> itemSupplier) {
//        ResourceLocation resourceLocation = new ResourceLocation(TobrirsGarden.MOD_ID, name().toLowerCase());
//        item = itemSupplier.get();
//        item.setRegistryName(resourceLocation);
//    }
//
//    public Item getItem() {
//        return item;
//    }
//}
