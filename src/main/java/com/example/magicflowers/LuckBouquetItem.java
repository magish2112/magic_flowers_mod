package com.example.magicflowers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffects;

public class LuckBouquetItem extends Item {
    public LuckBouquetItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 60));
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20 * 60));
            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 20 * 60));
            player.addEffect(new MobEffectInstance(MobEffects.LUCK, 20 * 60));
            player.getItemInHand(hand).shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
    }
} 