package fr.sqool.rubymod.item;

import fr.sqool.rubymod.RubyMod;
import fr.sqool.rubymod.RubyCreativeTabs;
import fr.sqool.rubymod.item.custom.RubySword;
import fr.sqool.rubymod.item.custom.RubyTiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RubyItem {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RubyMod.MODID);

    public static final RegistryObject<Item> RUBY_INGOT = ITEMS.register("ruby_ingot", () -> new Item(new Item.Properties().tab(RubyCreativeTabs.RUBY_MOD)));
    public static final RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword", () -> new RubySword(RubyTiers.RUBY, 9, -2.4F, new Item.Properties().stacksTo(1).durability(1000).tab(RubyCreativeTabs.RUBY_MOD)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
