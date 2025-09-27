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
    private final int durationSeconds;
    private final int amplifier;

    // Constructor for backward compatibility
    public MagicFlowerItem(Properties properties, MobEffect effect) {
        this(properties, effect, 30, 0); // Default 30 seconds, level 1
    }

    // Enhanced constructor with duration and amplifier
    public MagicFlowerItem(Properties properties, MobEffect effect, int durationSeconds, int amplifier) {
        super(properties);
        this.effect = effect;
        this.durationSeconds = durationSeconds;
        this.amplifier = amplifier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            // Add magical particle effect
            addFlowerParticles(world, player);

            // Apply the effect
            player.addEffect(new MobEffectInstance(effect, 20 * durationSeconds, amplifier));

            // Consume the item
            player.getItemInHand(hand).shrink(1);

            // Play sound effect
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                net.minecraft.sounds.SoundEvents.AMETHYST_BLOCK_CHIME,
                net.minecraft.sounds.SoundSource.PLAYERS, 1.0F, 1.5F);
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
    }

    private void addFlowerParticles(Level world, Player player) {
        // Add magical particles around the player
        for (int i = 0; i < 20; i++) {
            double x = player.getX() + (world.random.nextDouble() - 0.5) * 2.0;
            double y = player.getY() + world.random.nextDouble() * 2.0;
            double z = player.getZ() + (world.random.nextDouble() - 0.5) * 2.0;

            // Different particle colors based on effect
            net.minecraft.core.particles.ParticleTypes particleType;
            if (effect == MobEffects.FIRE_RESISTANCE) {
                particleType = net.minecraft.core.particles.ParticleTypes.FLAME;
            } else if (effect == MobEffects.INVISIBILITY) {
                particleType = net.minecraft.core.particles.ParticleTypes.SMOKE;
            } else {
                particleType = net.minecraft.core.particles.ParticleTypes.HAPPY_VILLAGER;
            }

            world.addParticle(particleType, x, y, z, 0.0, 0.1, 0.0);
        }
    }
} 