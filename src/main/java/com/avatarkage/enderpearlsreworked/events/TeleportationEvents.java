package com.avatarkage.enderpearlsreworked.events;

import com.avatarkage.enderpearlsreworked.utilities.ModHandlers;
import com.avatarkage.kagelibrary.states.TeleportationState;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;

/*? minecraft: <1.21.4 {*/ import net.minecraft.util.TypedActionResult; /*?}*/

import java.util.UUID;

/*? if forge {*/
/*import net.minecraft.util.hit.EntityHitResult;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
*//*?}*/

/*? if neoforge {*/
import net.minecraft.util.hit.EntityHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
/*?}*/

/*? if fabric {*/
/*import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.ActionResult;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
*//*?}*/

/*? if forge {*/ /*@Mod.EventBusSubscriber *//*?}*/
public class TeleportationEvents {

    /*? if forgeLike {*/
    @SubscribeEvent
    public /*? if forge {*/ /*static *//*?}*/ void onProjectileSpawn(EntityJoinLevelEvent event) {
        Entity projectile = event.getEntity();
        if (!ModHandlers.isPearlEntity(projectile)) return;
        ModHandlers.projectileSpawn(projectile);
    }

    @SubscribeEvent
    public /*? if forge {*/ /*static *//*?}*/ void onProjectileImpact(ProjectileImpactEvent event) {
        ModHandlers.handleImpact(event.getEntity());
    }

    @SubscribeEvent
    public /*? if forge {*/ /*static *//*?}*/ void onProjectileShot(ProjectileImpactEvent event) {
        if (!(event.getRayTraceResult() instanceof EntityHitResult)) return;
        ModHandlers.handleProjectileShot(event.getEntity(), null);
    }

    @SubscribeEvent
    public /*? if forge {*/ /*static *//*?}*/ void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (!(event.getEntity() instanceof ServerPlayerEntity player)) return;
        UUID playerUUID = player.getUuid();
        if (!TeleportationState.isTeleporting(playerUUID)) return;
        ModHandlers.tryTeleport(player, TeleportationState.getTeleportationMethod(playerUUID));
    }

    @SubscribeEvent
    public /*? if forge {*/ /*static *//*?}*/ void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!(event.getEntity() instanceof ServerPlayerEntity player)) return;
        UUID playerUUID = player.getUuid();
        if (!TeleportationState.isTeleporting(playerUUID)) return;
        ModHandlers.tryTeleport(player, TeleportationState.getTeleportationMethod(playerUUID));
    }

    @SubscribeEvent
    public /*? if forge {*/ /*static *//*?}*/ void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (!(event.getEntity() instanceof ServerPlayerEntity player)) return;
        UUID playerUUID = player.getUuid();
        if (!TeleportationState.isTeleporting(playerUUID)) return;
        ModHandlers.tryTeleport(player, TeleportationState.getTeleportationMethod(playerUUID));
    }

    @SubscribeEvent
    public /*? if forge {*/ /*static *//*?}*/ void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getEntity() instanceof ServerPlayerEntity player)) return;
        UUID playerUUID = player.getUuid();
        if (!TeleportationState.isTeleporting(playerUUID)) return;
        ModHandlers.tryTeleport(player, TeleportationState.getTeleportationMethod(playerUUID));
    }

    @SubscribeEvent
    public /*? if forge {*/ /*static *//*?}*/ void onAttackEntity(AttackEntityEvent event) {
        if (!(event.getEntity() instanceof ServerPlayerEntity player)) return;
        UUID playerUUID = player.getUuid();
        if (!TeleportationState.isTeleporting(playerUUID)) return;
        ModHandlers.tryTeleport(player, TeleportationState.getTeleportationMethod(playerUUID));
    }

    @SubscribeEvent
    public /*? if forge {*/ /*static *//*?}*/ void onItemUseStart(LivingEntityUseItemEvent.Start event) {
        if (!(event.getEntity() instanceof ServerPlayerEntity player)) return;
        if (player.getWorld().isClient()) return;
        UUID playerUUID = player.getUuid();
        if (!TeleportationState.isTeleporting(playerUUID)) return;
        ModHandlers.tryTeleport(player, TeleportationState.getTeleportationMethod(playerUUID));
    }
    /*?}*/

    /*? if forge {*/
    /*@SubscribeEvent
    public static void onPlayerDamage(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof ServerPlayerEntity player)) return;
        if (player.getWorld().isClient()) return;
        UUID playerUUID = player.getUuid();
        if (!TeleportationState.isTeleporting(playerUUID)) return;
        ModHandlers.tryTeleport(player, TeleportationState.getTeleportationMethod(playerUUID));
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        var server = event.level.getServer();
        if (server == null) return;
        ModHandlers.handleServerTick(server);
    }
    *//*?}*/

    /*? if neoforge {*/
    @SubscribeEvent
    public void onPlayerDamage(LivingDamageEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayerEntity player)) return;
        if (player.getWorld().isClient()) return;
        UUID playerUUID = player.getUuid();
        if (!TeleportationState.isTeleporting(playerUUID)) return;
        ModHandlers.tryTeleport(player, TeleportationState.getTeleportationMethod(playerUUID));
    }

    @SubscribeEvent
    public void onLevelTick(LevelTickEvent.Pre event) {
        var server = event.getLevel().getServer();
        if (server == null) return;
        ModHandlers.handleServerTick(server);
    }
    /*?}*/

    /*? if fabric {*/
    /*public static void init() {
        EntityEvent.ADD.register((entity, world) -> {
            if (
                !world.isClient()
                && ModHandlers.isPearlEntity(entity)
                && (entity instanceof ProjectileEntity projectile)
                && (projectile.getOwner() instanceof PlayerEntity player)
                && (player.getVehicle() == null)) {
                onProjectileSpawn(projectile);
            }
            return EventResult.pass();
        });

        ServerTickEvents.END_SERVER_TICK.register(ModHandlers::handleServerTick);

        UseItemCallback.EVENT.register((player, world, hand) -> {
            UUID playerUUID = player.getUuid();
            if (!world.isClient() && TeleportationState.isTeleporting(playerUUID)) {
                ModHandlers.tryTeleport((ServerPlayerEntity) player, TeleportationState.getTeleportationMethod(playerUUID));
            }
            /^? minecraft: <1.21.4 {^/ return TypedActionResult.pass(player.getStackInHand(hand)); /^?}^/
            /^? minecraft: >=1.21.4 {^/ /^return ActionResult.PASS; ^//^?}^/
        });

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!world.isClient()) {
                UUID playerUUID = player.getUuid();
                ModHandlers.tryTeleport((ServerPlayerEntity) player, TeleportationState.getTeleportationMethod(playerUUID));
            }
            return ActionResult.PASS;
        });
    }

    public static void onProjectileSpawn(Entity projectile) {
        ModHandlers.projectileSpawn(projectile);
    }
    *//*?}*/
}
