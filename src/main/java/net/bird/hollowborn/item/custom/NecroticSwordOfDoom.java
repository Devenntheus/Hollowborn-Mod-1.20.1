package net.bird.hollowborn.item.custom;

import net.bird.hollowborn.config.Config;
import net.bird.hollowborn.config.ConfigDefaultValues;
import net.bird.hollowborn.item.ModItems;
import net.bird.hollowborn.util.HelperMethods;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class NecroticSwordOfDoom extends SwordItem {

    public NecroticSwordOfDoom(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    private static int stepMod = 0;
    public static boolean scalesWithSpellPower;
    int radius = (int) Config.getFloat("soulAnguishRadius", "UniqueEffects", ConfigDefaultValues.soulAnguishRadius);
    float abilityDamage = Config.getFloat("soulAnguishDamage", "UniqueEffects", ConfigDefaultValues.soulAnguishDamage);
    float spellScalingModifier = Config.getFloat("soulAnguishSpellScaling", "UniqueEffects", ConfigDefaultValues.soulAnguishSpellScaling);

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity user, int slot, boolean selected) {
        float calculatedDamage = HelperMethods.commonSpellAttributeScaling(spellScalingModifier, user, "soul");
        if (calculatedDamage > 0) {
            abilityDamage = calculatedDamage;
            scalesWithSpellPower = true;
        }

        if (!user.getWorld().isClient() && user instanceof LivingEntity livingUser) {
            if (livingUser.age % 20 == 0 && livingUser.getEquippedStack(EquipmentSlot.MAINHAND) == stack && !livingUser.isUsingItem()) {
                Box box = new Box(livingUser.getX() + radius, livingUser.getY() + radius, livingUser.getZ() + radius,
                        livingUser.getX() - radius, livingUser.getY() - radius, livingUser.getZ() - radius);
                for (Entity entity : world.getOtherEntities(livingUser, box, EntityPredicates.VALID_LIVING_ENTITY)) {
                    if ((entity instanceof LivingEntity le) && HelperMethods.checkFriendlyFire((LivingEntity) entity, livingUser)) {
                        le.damage(livingUser.getDamageSources().indirectMagic(user, user), abilityDamage);
                    }
                }
                double xpos = livingUser.getX() - (radius + 1);
                double ypos = livingUser.getY();
                double zpos = livingUser.getZ() - (radius + 1);

                for (int i = radius * 2; i > 0; i--) {
                    for (int j = radius * 2; j > 0; j--) {
                        float choose = (float) (Math.random() * 1);
                        HelperMethods.spawnParticle(world, ParticleTypes.SCULK_SOUL,
                                xpos + i + choose, ypos, zpos + j + choose,
                                0, 0.1, 0);
                        HelperMethods.spawnParticle(world, ParticleTypes.SOUL,
                                xpos + i + choose, ypos + 0.1, zpos + j + choose,
                                0, 0, 0);
                        HelperMethods.spawnParticle(world, ParticleTypes.MYCELIUM,
                                xpos + i + choose, ypos + 2, zpos + j + choose,
                                0, 0, 0);
                    }
                }
            }
        }
        if (stepMod > 0) stepMod--;
        if (stepMod <= 0) stepMod = 7;
        HelperMethods.createFootfalls(user, stack, world, stepMod, ParticleTypes.SOUL, ParticleTypes.SOUL,
                ParticleTypes.MYCELIUM, true);
        super.inventoryTick(stack, world, user, slot, selected);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 7777777, 7));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 7777777, 7));
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 200, 7));
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 7));
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 200, 7));
        stack.damage(1, attacker, (e) -> {
            e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
        });
        return true;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        Style CROUCHLEFTCLICK = HelperMethods.getStyle("croutchleftclick");
        Style ABILITY = HelperMethods.getStyle("ability");
        Style TEXT = HelperMethods.getStyle("text");
        tooltip.add(Text.translatable("item.hollowborn.hollowbornswordofdoom.quality").setStyle(TEXT));
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("item.hollowborn.hollowbornswordofdoom.tooltip1").setStyle(ABILITY));
        tooltip.add(Text.translatable("item.hollowborn.hollowbornswordofdoom.tooltip2").setStyle(TEXT));
        tooltip.add(Text.translatable("item.hollowborn.hollowbornswordofdoom.tooltip3").setStyle(TEXT));
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("item.hollowborn.hollowbornswordofdoom.tooltip4").setStyle(CROUCHLEFTCLICK));
        tooltip.add(Text.translatable("item.hollowborn.hollowbornswordofdoom.tooltip5").setStyle(TEXT));
        tooltip.add(Text.translatable("item.hollowborn.hollowbornswordofdoom.tooltip6").setStyle(TEXT));
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("item.hollowborn.hollowbornswordofdoom.tooltip7").setStyle(TEXT));
        tooltip.add(Text.translatable("item.hollowborn.hollowbornswordofdoom.tooltip8").setStyle(TEXT));
        tooltip.add(Text.translatable("item.hollowborn.hollowbornswordofdoom.tooltip9").setStyle(TEXT));
        
        if (scalesWithSpellPower) {
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable("item.hollowborn.compat.scaleSoul1"));
            tooltip.add(Text.translatable("item.hollowborn.compat.scaleSoul2"));
        }

        super.appendTooltip(itemStack, world, tooltip, tooltipContext);
    }



    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        super.onCraft(stack, world, player);
        NbtCompound hsod = new NbtCompound();
        NbtCompound sw14 = new NbtCompound();

        stack.addEnchantment(Enchantments.MENDING, 7);
        stack.addEnchantment(Enchantments.UNBREAKING, 7);
        stack.addEnchantment(Enchantments.FIRE_ASPECT, 7);
        stack.addHideFlag(ItemStack.TooltipSection.ENCHANTMENTS);

        if(stack.hasNbt()) {
            stack.getNbt().putInt("hollowbornsword", stack.getNbt().getInt("hollowbornsword") + 1);
            stack.getNbt().putInt("sw14", stack.getNbt().getInt("sw14") + 1);

        } else {
            hsod.putInt("hollowbornsword", 1);
            sw14.putInt("sw14", 1);
            stack.setNbt(hsod);
            stack.setNbt(sw14);
        }
    }
}