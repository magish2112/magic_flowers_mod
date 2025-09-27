package com.example.magicflowers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffects;

public class PowerBouquetItem extends Item {
    public PowerBouquetItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            // Apply all powerful effects for 5 minutes
            int duration = 20 * 300; // 5 minutes

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, 1));     // Speed II
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration, 1));      // Strength II
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, duration, 2));              // Jump Boost III
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duration, 1));      // Regeneration II
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, duration, 0));   // Fire Resistance
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, duration, 0));      // Night Vision
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration, 1)); // Resistance II
            player.addEffect(new MobEffectInstance(MobEffects.LUCK, duration, 0));             // Luck

            // Add special power particles
            addPowerParticles(world, player);

            // Play epic sound
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                net.minecraft.sounds.SoundEvents.END_PORTAL_SPAWN,
                net.minecraft.sounds.SoundSource.PLAYERS, 1.0F, 1.2F);

            player.getItemInHand(hand).shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
    }

    private void addPowerParticles(Level world, Player player) {
        // Create epic particle effect
        for (int i = 0; i < 50; i++) {
            double x = player.getX() + (world.random.nextDouble() - 0.5) * 3.0;
            double y = player.getY() + world.random.nextDouble() * 3.0;
            double z = player.getZ() + (world.random.nextDouble() - 0.5) * 3.0;

            // Mix of different magical particles
            net.minecraft.core.particles.ParticleTypes particleType;
            int rand = world.random.nextInt(4);
            switch (rand) {
                case 0:
                    particleType = net.minecraft.core.particles.ParticleTypes.ENCHANTED_HIT;
                    break;
                case 1:
                    particleType = net.minecraft.core.particles.ParticleTypes.FLAME;
                    break;
                case 2:
                    particleType = net.minecraft.core.particles.ParticleTypes.SOUL_FIRE_FLAME;
                    break;
                default:
                    particleType = net.minecraft.core.particles.ParticleTypes.END_ROD;
                    break;
            }

            world.addParticle(particleType, x, y, z,
                (world.random.nextDouble() - 0.5) * 0.1,
                world.random.nextDouble() * 0.2,
                (world.random.nextDouble() - 0.5) * 0.1);
        }
    }
}
