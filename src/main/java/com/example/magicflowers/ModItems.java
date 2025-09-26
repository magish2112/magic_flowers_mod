package com.example.magicflowers;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "magicflowers");

    public static final RegistryObject<Item> BLUE_LOTUS = ITEMS.register("blue_lotus",
        () -> new MagicFlowerItem(new Item.Properties().tab(CreativeModeTabs.FUNCTIONAL_BLOCKS), MobEffects.MOVEMENT_SPEED));
    public static final RegistryObject<Item> FIRE_POPPY = ITEMS.register("fire_poppy",
        () -> new MagicFlowerItem(new Item.Properties().tab(CreativeModeTabs.FUNCTIONAL_BLOCKS), MobEffects.FIRE_RESISTANCE));
    public static final RegistryObject<Item> MIST_LAVENDER = ITEMS.register("mist_lavender",
        () -> new MagicFlowerItem(new Item.Properties().tab(CreativeModeTabs.FUNCTIONAL_BLOCKS), MobEffects.INVISIBILITY));
    public static final RegistryObject<Item> GOLDEN_TULIP = ITEMS.register("golden_tulip",
        () -> new MagicFlowerItem(new Item.Properties().tab(CreativeModeTabs.FUNCTIONAL_BLOCKS), MobEffects.LUCK));
    public static final RegistryObject<Item> LUCK_BOUQUET = ITEMS.register("luck_bouquet",
        () -> new LuckBouquetItem(new Item.Properties().tab(CreativeModeTabs.FUNCTIONAL_BLOCKS)));

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
} 