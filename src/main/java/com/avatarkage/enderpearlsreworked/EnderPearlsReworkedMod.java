package com.avatarkage.enderpearlsreworked;

import com.avatarkage.enderpearlsreworked.events.TeleportationEvents;
import com.avatarkage.kagelibrary.network.ModNetwork;
import net.minecraft.entity.EntityType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

/*? if forge {*/
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
/*?}*/

/*? if neoforge {*/
/*import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
*//*?}*/

/*? if fabric {*/
/*import net.fabricmc.api.ModInitializer;
*//*?}*/

/*? if forgeLike {*/ @Mod(EnderPearlsReworkedMod.MOD_ID) /*?}*/
public final class EnderPearlsReworkedMod /*? if fabric {*/ /*implements ModInitializer *//*?}*/ {
    public static final String MOD_ID = "enderpearlsreworked";

    public static final TeleportConfig CONFIG = new TeleportConfig(
            ParticleTypes.PORTAL,
            SoundEvents.ENTITY_ENDERMAN_TELEPORT,
            EntityType.ENDERMITE,
            0.05f
    );

    public record TeleportConfig(ParticleEffect particle, SoundEvent sound, EntityType<?> entityType, float entityChance) {}

    /*? if forge {*/
    public EnderPearlsReworkedMod(FMLJavaModLoadingContext ctx) {
        var modEventBus = ctx.getModEventBus();
        EventBuses.registerModEventBus(EnderPearlsReworkedMod.MOD_ID, modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(TeleportationEvents.class);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModNetwork.register();
    }
    /*?}*/

    /*? if neoforge {*/
    /*public EnderPearlsReworkedMod() {
        ModNetwork.register();
        NeoForge.EVENT_BUS.register(new TeleportationEvents());
    }
    *//*?}*/

    /*? if fabric {*/
    /*@Override
    public void onInitialize() {
       ModNetwork.register();
       TeleportationEvents.init();
    }
    *//*?}*/
}
