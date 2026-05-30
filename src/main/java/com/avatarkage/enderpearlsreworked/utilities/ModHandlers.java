package com.avatarkage.enderpearlsreworked.utilities;

import com.avatarkage.enderpearlsreworked.EnderPearlsReworkedMod;
import com.avatarkage.enderpearlsreworked.tags.ModEntityTypeTags;
import com.avatarkage.enderpearlsreworked.tags.ModItemTags;
import com.avatarkage.kagelibrary.states.TeleportationState;
import com.avatarkage.kagelibrary.utilities.TeleportationHandlers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.UUID;

public class ModHandlers {
    public static void tryTeleport(ServerPlayerEntity player, Object projectileOrId) {
        UUID playerUUID = player.getUuid();
        StatusEffectInstance onTeleportEffect = null;
        Entity projectileEntity = null;
        Identifier id = null;

        if (projectileOrId instanceof Entity entity) {
            projectileEntity = entity;
            id = Registries.ENTITY_TYPE.getId(entity.getType());
        } else if (projectileOrId instanceof String idString) {
            id = Identifier.tryParse(idString);
        }

        if (id != null) {
            TeleportationState.setTeleportationMethod(playerUUID, id.toString());

            if (id.toString().equals("endermanoverhaul:crimson_pearl")) {
                onTeleportEffect = new StatusEffectInstance(StatusEffects.STRENGTH, 300, 1);
            } else if (id.toString().equals("endermanoverhaul:warped_pearl")) {
                onTeleportEffect = new StatusEffectInstance(StatusEffects.RESISTANCE, 300, 1);
            }
        }

        TeleportationHandlers.tryTeleport(
                player,
                projectileEntity,
                EnderPearlsReworkedMod.CONFIG.particle(),
                EnderPearlsReworkedMod.CONFIG.sound(),
                EnderPearlsReworkedMod.CONFIG.entityType(),
                EnderPearlsReworkedMod.CONFIG.entityChance(),
                onTeleportEffect
        );
    }

    public static boolean isPearlEntity(Entity entity) {
        return entity.getType().isIn(ModEntityTypeTags.PEARLS);
    }

    public static void projectileSpawn(Entity entity) {
        if (!(entity instanceof ProjectileEntity projectile)) return;
        if (!(projectile.getOwner() instanceof ServerPlayerEntity player)) return;

        for (ItemStack stack : List.of(player.getMainHandStack(), player.getOffHandStack())) {
            if (stack.isIn(ModItemTags.PEARLS)) {
                /*? minecraft: <1.21.4 {*/ player.getItemCooldownManager().set(stack.getItem(), 5); /*?}*/
                /*? minecraft: >=1.21.4 {*/
                /*Identifier id = Registries.ITEM.getId(stack.getItem());
                player.getItemCooldownManager().set(id, 5);
                *//*?}*/
            }
        }

        tryTeleport(player, projectile);
    }

    public static void handleServerTick(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            for (ServerPlayerEntity player : world.getPlayers()) {

                UUID playerUUID = player.getUuid();

                if (TeleportationState.isTeleporting(playerUUID)) {
                    player.setInvulnerable(true);
                    Entity projectile = player.getVehicle();

                    if (projectile == null) {
                        tryTeleport(player, TeleportationState.getTeleportationMethod(playerUUID));
                    }
                }

                List<Entity> projectiles = world.getEntitiesByClass(
                        Entity.class,
                        player.getBoundingBox().expand(4.0D),
                        ModHandlers::isPearlEntity
                );

                for (Entity projectile : projectiles) {
                    if (!projectile.isAlive()) continue;

                    for (int i = 0; i < 1; i++) {
                        double offsetX = (projectile.getWorld().random.nextDouble() - 0.5);
                        double offsetY = -projectile.getWorld().random.nextDouble();
                        double offsetZ = (projectile.getWorld().random.nextDouble() - 0.5);
                        ParticleEffect particle = null;

                        if (TeleportationState.getTeleportationMethod(playerUUID).equals("endermanoverhaul:bubble_pearl")) {
                            particle = ParticleTypes.BUBBLE;
                        } else {
                            particle = EnderPearlsReworkedMod.CONFIG.particle();
                        }

                        world.spawnParticles(
                                particle,
                                projectile.getX(),
                                projectile.getY(),
                                projectile.getZ(),
                                1,
                                offsetX,
                                offsetY,
                                offsetZ,
                                0.0
                        );
                    }

                    if (projectile.getPassengerList().isEmpty()) {
                        projectile.discard();
                    }
                }
            }
        }
    }

    public static void handleImpact(Entity projectile) {
        if (!ModHandlers.isPearlEntity(projectile)) return;

        if (!projectile.getPassengerList().isEmpty()
                && projectile.getPassengerList().get(0) instanceof ServerPlayerEntity player) {

            tryTeleport(player, projectile);
        }
    }

    public static void handleProjectileShot(Entity projectile, Entity hitEntity) {
        if (!ModHandlers.isPearlEntity(projectile)) return;

        if (hitEntity instanceof ProjectileEntity) {
            projectile.discard();
            handleImpact(projectile);
        }
    }
}
