package net.bird.hollowborn.util;

import net.bird.hollowborn.config.Config;
import net.bird.hollowborn.config.ConfigDefaultValues;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.scoreboard.AbstractTeam;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Style;
import net.minecraft.text.TextColor;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HelperMethods {

    public static Entity getTargetedEntity(Entity user, int range) {
        Vec3d rayCastOrigin = user.getEyePos();
        Vec3d userView = user.getRotationVec(1.0F).normalize().multiply(range);
        Vec3d rayCastEnd = rayCastOrigin.add(userView);
        Box searchBox = user.getBoundingBox().expand(range, range, range);
        EntityHitResult hitResult = ProjectileUtil.raycast(user, rayCastOrigin, rayCastEnd, searchBox,
                (target) -> !target.isSpectator() && target.canHit() && target instanceof LivingEntity, range * range);
        if (hitResult != null) {
            return hitResult.getEntity();
        }
        return null;
    }

    public static boolean isWalking(Entity entity) {
        return entity instanceof PlayerEntity player && (!player.isDead() && (player.isSwimming() || player.getVelocity().horizontalLength() > 0.1));
    }

    //spawnParticle - spawns particles across both client & server
    public static void spawnParticle(World world, ParticleEffect particle, double xpos, double ypos, double zpos,
                                     double xvelocity, double yvelocity, double zvelocity) {
        if (world.isClient) {
            world.addParticle(particle, xpos, ypos, zpos, xvelocity, yvelocity, zvelocity);
        } else if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(particle, xpos, ypos, zpos, 1, xvelocity, yvelocity, zvelocity, 0.1);
        }
    }

    // createFootfalls - creates weapon footfall particle effects (footsteps)
    public static void createFootfalls(Entity entity, ItemStack stack, World world, int stepMod, DefaultParticleType particle,
                                       DefaultParticleType sprintParticle, DefaultParticleType passiveParticle, boolean passiveParticles) {
        if ((entity instanceof PlayerEntity player) && Config.getBoolean("enableWeaponFootfalls", "General", ConfigDefaultValues.enableWeaponFootfalls) && player.getEquippedStack(EquipmentSlot.MAINHAND) == stack) {
            if (isWalking(player) && !player.isSwimming() && player.isOnGround()) {
                if (stepMod == 6) {
                    if (player.isSprinting()) {
                        world.addParticle(sprintParticle, player.getX() + player.getHandPosOffset(stack.getItem()).getX(),
                                player.getY() + player.getHandPosOffset(stack.getItem()).getY() + 0.2,
                                player.getZ() + player.getHandPosOffset(stack.getItem()).getZ(),
                                0, 0.0, 0);
                    } else {
                        world.addParticle(particle, player.getX() + player.getHandPosOffset(stack.getItem()).getX(),
                                player.getY() + player.getHandPosOffset(stack.getItem()).getY() + 0.2,
                                player.getZ() + player.getHandPosOffset(stack.getItem()).getZ(),
                                0, 0.0, 0);
                    }
                } else if (stepMod == 3) {
                    if (player.isSprinting()) {
                        world.addParticle(sprintParticle, player.getX() - player.getHandPosOffset(stack.getItem()).getX(),
                                player.getY() + player.getHandPosOffset(stack.getItem()).getY() + 0.2,
                                player.getZ() - player.getHandPosOffset(stack.getItem()).getZ(),
                                0, 0.0, 0);
                    } else {
                        world.addParticle(particle, player.getX() - player.getHandPosOffset(stack.getItem()).getX(),
                                player.getY() + player.getHandPosOffset(stack.getItem()).getY() + 0.2,
                                player.getZ() - player.getHandPosOffset(stack.getItem()).getZ(),
                                0, 0.0, 0);
                    }
                }
            }
            if (passiveParticles && Config.getBoolean("enablePassiveParticles", "General",ConfigDefaultValues.enablePassiveParticles)) {
                float randomy = (float) (Math.random());
                if (stepMod == 1) {
                    world.addParticle(passiveParticle, player.getX() - player.getHandPosOffset(stack.getItem()).getX(),
                            player.getY() + player.getHandPosOffset(stack.getItem()).getY() + 0.4 + randomy,
                            player.getZ() - player.getHandPosOffset(stack.getItem()).getZ(),
                            0, 0.0, 0);
                    world.addParticle(passiveParticle, player.getX() - player.getHandPosOffset(stack.getItem()).getX() + 0.1,
                            player.getY() + player.getHandPosOffset(stack.getItem()).getY() + randomy,
                            player.getZ() - player.getHandPosOffset(stack.getItem()).getZ() - 0.1,
                            0, 0.0, 0);
                } else if (stepMod == 4) {
                    world.addParticle(passiveParticle, player.getX() + player.getHandPosOffset(stack.getItem()).getX(),
                            player.getY() + player.getHandPosOffset(stack.getItem()).getY() + 0.4 + randomy,
                            player.getZ() + player.getHandPosOffset(stack.getItem()).getZ(),
                            0, 0.0, 0);
                    world.addParticle(passiveParticle, player.getX() + player.getHandPosOffset(stack.getItem()).getX() - 0.1,
                            player.getY() + player.getHandPosOffset(stack.getItem()).getY() + randomy,
                            player.getZ() + player.getHandPosOffset(stack.getItem()).getZ() + 0.1,
                            0, 0.0, 0);
                }
            }
        }
    }

    //Get Item attack damage
    public static double getAttackDamage(ItemStack stack){
        return stack.getItem().getAttributeModifiers(EquipmentSlot.MAINHAND)
                .get(EntityAttributes.GENERIC_ATTACK_DAMAGE)
                .stream()
                .mapToDouble(EntityAttributeModifier::getValue)
                .sum();
    }

    //Check if we should be able to hit the target
    public static boolean checkFriendlyFire(LivingEntity target, LivingEntity attacker) {
        if (!checkEntityBlacklist(target, attacker)) {
            return false;
        }

        // Check if the player and the living entity are on the same team
        AbstractTeam playerTeam = attacker.getScoreboardTeam();
        AbstractTeam entityTeam = target.getScoreboardTeam();
        if (playerTeam != null && entityTeam != null && target.isTeammate(attacker)) {
            // They are on the same team, so friendly fire should not be allowed
            return false;
        }

        if (target instanceof PlayerEntity playerTarget) {
            if (playerTarget == attacker) {
                return false;
            }
            return !(attacker instanceof PlayerEntity playerAttacker) || playerAttacker.shouldDamagePlayer(playerTarget);
        }
        if (target instanceof Tameable tameable && attacker instanceof PlayerEntity player) {
            if (tameable.getOwner() != null) {
                if (tameable.getOwner() != player
                        && (tameable.getOwner() instanceof PlayerEntity ownerPlayer))
                    return player.shouldDamagePlayer(ownerPlayer);
                return tameable.getOwner() != player;
            }
            return true;
        }
        return true;
    }

    //Check if the target matches blacklisted entities (expand this to be configurable if there is demand)
    public static boolean checkEntityBlacklist(LivingEntity target, LivingEntity player) {
        if (target == null || player == null) {
            return false;
        }
        return !(target instanceof ArmorStandEntity)
                && !(target instanceof VillagerEntity);
    }

    public static float getDamageForEntity(Entity entity) {
        if (entity instanceof PlayerEntity) {
            return ConfigDefaultValues.soulAnguishDamage;
        } else {
            return 7f;
        }
    }

    public static Style getStyle(String styleType) {
        int rgbUnique = 0xE2A834;
        int rgbLegendary = 0xE26234;
        int rgbAbility = 0xE2A834;
        int rgbCrouchLeftClick = 0x20BD69;
        int rgbText = 0xE0E0E0;
        Style UNIQUE = Style.EMPTY.withColor(TextColor.fromRgb(rgbUnique));
        Style LEGENDARY = Style.EMPTY.withColor(TextColor.fromRgb(rgbLegendary));
        Style ABILITY = Style.EMPTY.withColor(TextColor.fromRgb(rgbAbility));
        Style CROUCHLEFTCLICK = Style.EMPTY.withColor(TextColor.fromRgb(rgbCrouchLeftClick));
        Style TEXT = Style.EMPTY.withColor(TextColor.fromRgb(rgbText));

        return switch (styleType) {
            case "unique" -> UNIQUE;
            case "legendary" -> LEGENDARY;
            case "ability" -> ABILITY;
            case "crouchleftclick" -> CROUCHLEFTCLICK;
            case "text" -> TEXT;
            default -> LEGENDARY;
        };
    }
}