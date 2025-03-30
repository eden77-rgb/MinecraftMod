package fr.sqool.tutomod.item;

import fr.sqool.tutomod.TutoCreativeTabs;
import fr.sqool.tutomod.TutoMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TutoItem {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutoMod.MODID);

    public static final RegistryObject<Item> TUTO_ITEM = ITEMS.register("tuto_item", () -> new Item((new Item.Properties().tab(TutoCreativeTabs.TUTO_MOD).stacksTo(1))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
