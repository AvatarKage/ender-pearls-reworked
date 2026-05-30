package com.avatarkage.enderpearlsreworked.client;

import com.avatarkage.kagelibrary.client.TeleportationRenderHandlers;
import net.minecraft.client.MinecraftClient;

/*? if forge {*/
/*import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
*//*?}*/

/*? if neoforge {*/
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
/*?}*/

/*? if fabric {*/
/*import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
*//*?}*/

/*? if forge {*/ /*@Mod.EventBusSubscriber(modid = "enderpearlsreworked", value = Dist.CLIENT) *//*?}*/
public class TeleportationRenderer /*? if fabric {*/ /*implements ClientModInitializer *//*?}*/ {

    /*? if forgeLike {*/
    @SubscribeEvent
    public static void
    /*? if forge {*/ /*onClientTick(TickEvent.ClientTickEvent event)  *//*?}*/
    /*? if neoforge {*/ onClientTick(ClientTickEvent.Pre event) /*?}*/
    {
        /*? if !neoforge {*/
        /*MinecraftClient client = MinecraftClient.getInstance();
        TeleportationRenderHandlers.handleClientTick(client);
        *//*?}*/

        /*? if neoforge {*/
        TeleportationRenderHandlers.handleClientTick(event);
         /*?}*/
    }
    /*?}*/

    /*? if fabric {*/
    /*@Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(TeleportationRenderHandlers::handleClientTick);
    }
    *//*?}*/
}
