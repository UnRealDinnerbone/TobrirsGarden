package com.unrealdinnerbone.tobrirsgarden;

import com.unrealdinnerbone.tobrirsgarden.events.DataGenerator;
import com.unrealdinnerbone.tobrirsgarden.events.RegisteryEvents;
import com.unrealdinnerbone.tobrirsgarden.util.TorbrisItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TobrirsGarden.MOD_ID)
@Mod.EventBusSubscriber
public class TobrirsGarden
{
    public static TorbrisItemGroup ITEM_GROUP = new TorbrisItemGroup();
    public static Item.Properties BASIC = new Item.Properties().group(ITEM_GROUP);
    private static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec.IntValue tickAmount;

    public static final String MOD_ID = "tobrirsgarden";
    private static final Logger LOGGER = LogManager.getLogger();

    public TobrirsGarden() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(DataGenerator::onDataGen);
        RegisteryEvents.init();
        builder.push("general");
        tickAmount = builder.comment("The tickRate of soulsand to apply a another random tick to the soulsnad block").defineInRange("tickRate", 5, 1, Integer.MAX_VALUE);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, builder.build());
    }
}
