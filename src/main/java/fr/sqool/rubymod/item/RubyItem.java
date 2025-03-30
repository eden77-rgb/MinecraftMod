package fr.sqool.rubymod.item;

import fr.sqool.rubymod.RubyMod;
import fr.sqool.rubymod.RubyCreativeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RubyItem {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RubyMod.MODID);

    public static final RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword", () -> new Item((new Item.Properties().tab(RubyCreativeTabs.RUBY_MOD).stacksTo(1))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
