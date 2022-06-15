package com.mrkelpy.kelpysfoodmod;
import com.mojang.logging.LogUtils;
import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;


@Mod(KelpysFoodMod.MODID)
public class KelpysFoodMod {
    /**
     * This class handles core interactions with Forge in order to make the mod work.
     */

    public static final String MODID = "kelpysfoodmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public KelpysFoodMod() {
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        Registration.ITEMS.register(modbus);
        MinecraftForge.EVENT_BUS.register(this);
    }

}
