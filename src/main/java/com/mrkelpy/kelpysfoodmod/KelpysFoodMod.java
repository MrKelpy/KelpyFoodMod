package com.mrkelpy.kelpysfoodmod;

import com.mojang.logging.LogUtils;
import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


/**
 * This class handles core interactions with Forge in order to make the mod work.
 */
@Mod(KelpysFoodMod.MODID)
public class KelpysFoodMod {

    public static final String MODID = "kelpysfoodmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public KelpysFoodMod() {
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        Registration.ITEMS.register(modbus);
        Registration.RECIPE_SERIALIZERS.register(modbus);
        Registration.RECIPE_TYPES.register(modbus);
    }

}
