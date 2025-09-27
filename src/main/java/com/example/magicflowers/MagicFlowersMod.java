package com.example.magicflowers;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.item.CreativeModeTab;

@Mod("magicflowers")
public class MagicFlowersMod {
    public MagicFlowersMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register items and creative tabs
        ModItems.register(bus);
    }
} 