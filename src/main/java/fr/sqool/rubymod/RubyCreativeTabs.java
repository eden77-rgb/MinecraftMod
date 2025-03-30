package fr.sqool.rubymod;

import fr.sqool.rubymod.item.RubyItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class RubyCreativeTabs {
    public static final CreativeModeTab RUBY_MOD = new CreativeModeTab("ruby_mod") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RubyItem.RUBY_SWORD.get());
        }
    };
}
