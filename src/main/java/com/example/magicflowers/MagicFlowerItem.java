package com.example.magicflowers;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.level.Level;

public class MagicFlowerItem extends Item {
    private final MobEffect effect;

    public MagicFlowerItem(Properties properties, MobEffect effect) {
        super(properties);
        this.effect = effect;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            player.addEffect(new MobEffectInstance(effect, 20 * 30)); // 30 секунд
            player.getItemInHand(hand).shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
    }
} 