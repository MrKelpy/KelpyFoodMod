package com.mrkelpy.kelpysfoodmod.setup;

import com.mrkelpy.kelpysfoodmod.other.ModItemProperties;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class SetupClient {

    @SubscribeEvent
    public void RenderSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(Registration.SOYBEAN_PLANT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(Registration.RICE_PLANT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(Registration.VALINOR_WHEAT_PLANT.get(), RenderType.cutout());

        ModItemProperties itemProperties = new ModItemProperties();
        itemProperties.getProperties().forEach(p -> p.registerInto(Registration.LEMBAS.get()));
    }

}

