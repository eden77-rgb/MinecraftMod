package fr.sqool.tutomod;

import fr.sqool.tutomod.item.TutoItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TutoCreativeTabs {
    public static final CreativeModeTab TUTO_MOD = new CreativeModeTab("tutomod") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(TutoItem.TUTO_ITEM.get());
        }
    };
}
