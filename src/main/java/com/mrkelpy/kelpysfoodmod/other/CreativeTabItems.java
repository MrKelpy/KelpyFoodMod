package com.mrkelpy.kelpysfoodmod.other;

import com.mrkelpy.kelpysfoodmod.KelpysFoodMod;
import com.mrkelpy.kelpysfoodmod.setup.Registration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

@MethodsReturnNonnullByDefault
public class CreativeTabItems extends CreativeModeTab {

    public CreativeTabItems() {
        super(KelpysFoodMod.MODID + ".itemsTab");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Registration.RICE_STASH.get());
    }
}

