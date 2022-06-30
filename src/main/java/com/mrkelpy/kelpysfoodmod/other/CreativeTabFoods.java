package com.mrkelpy.kelpysfoodmod.other;

import com.mrkelpy.kelpysfoodmod.KelpysFoodMod;
import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

@MethodsReturnNonnullByDefault
public class CreativeTabFoods extends CreativeModeTab {

    public CreativeTabFoods() {
        super(KelpysFoodMod.MODID + ".foodTab");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Registration.ONIGIRI_KELP.get());
    }
}

