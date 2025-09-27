package com.example.magicflowers;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "magicflowers");
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(ForgeRegistries.CREATIVE_MODE_TABS, "magicflowers");

    // Создаем собственную вкладку в Creative
    public static final RegistryObject<CreativeModeTab> MAGIC_FLOWERS_TAB = CREATIVE_MODE_TABS.register("magic_flowers_tab",
        () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(CRYSTAL_ROSE.get()))
            .title(Component.translatable("itemGroup.magicflowers"))
            .displayItems((parameters, output) -> {
                // Добавляем все предметы мода в вкладку
                output.accept(BLUE_LOTUS.get());
                output.accept(FIRE_POPPY.get());
                output.accept(MIST_LAVENDER.get());
                output.accept(GOLDEN_TULIP.get());
                output.accept(CRYSTAL_ROSE.get());
                output.accept(SHADOW_ORCHID.get());
                output.accept(STORM_LILY.get());
                output.accept(SUNFLOWER.get());
                output.accept(ICE_CARNATION.get());
                output.accept(LUCK_BOUQUET.get());
                output.accept(POWER_BOUQUET.get());
            })
            .build());

    // Original flowers
    public static final RegistryObject<Item> BLUE_LOTUS = ITEMS.register("blue_lotus",
        () -> new MagicFlowerItem(new Item.Properties(), MobEffects.MOVEMENT_SPEED));
    public static final RegistryObject<Item> FIRE_POPPY = ITEMS.register("fire_poppy",
        () -> new MagicFlowerItem(new Item.Properties(), MobEffects.FIRE_RESISTANCE));
    public static final RegistryObject<Item> MIST_LAVENDER = ITEMS.register("mist_lavender",
        () -> new MagicFlowerItem(new Item.Properties(), MobEffects.INVISIBILITY));
    public static final RegistryObject<Item> GOLDEN_TULIP = ITEMS.register("golden_tulip",
        () -> new MagicFlowerItem(new Item.Properties(), MobEffects.LUCK));

    // New magical flowers with enhanced effects
    public static final RegistryObject<Item> CRYSTAL_ROSE = ITEMS.register("crystal_rose",
        () -> new MagicFlowerItem(new Item.Properties(), MobEffects.REGENERATION, 45, 2));
    public static final RegistryObject<Item> SHADOW_ORCHID = ITEMS.register("shadow_orchid",
        () -> new MagicFlowerItem(new Item.Properties(), MobEffects.NIGHT_VISION, 60, 1));
    public static final RegistryObject<Item> STORM_LILY = ITEMS.register("storm_lily",
        () -> new MagicFlowerItem(new Item.Properties(), MobEffects.JUMP, 30, 3));
    public static final RegistryObject<Item> SUNFLOWER = ITEMS.register("sunflower",
        () -> new MagicFlowerItem(new Item.Properties(), MobEffects.GLOWING, 20, 1));
    public static final RegistryObject<Item> ICE_CARNATION = ITEMS.register("ice_carnation",
        () -> new MagicFlowerItem(new Item.Properties(), MobEffects.DAMAGE_RESISTANCE, 40, 1));

    // Special bouquets and items
    public static final RegistryObject<Item> LUCK_BOUQUET = ITEMS.register("luck_bouquet",
        () -> new LuckBouquetItem(new Item.Properties()));
    public static final RegistryObject<Item> POWER_BOUQUET = ITEMS.register("power_bouquet",
        () -> new PowerBouquetItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        CREATIVE_MODE_TABS.register(eventBus);
    }
} 