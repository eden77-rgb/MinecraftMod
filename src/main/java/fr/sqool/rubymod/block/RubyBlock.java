package fr.sqool.rubymod.block;

import fr.sqool.rubymod.RubyCreativeTabs;
import fr.sqool.rubymod.RubyMod;
import fr.sqool.rubymod.block.custom.RubyChest;
import fr.sqool.rubymod.block.custom.RubyOre;
import fr.sqool.rubymod.item.RubyItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RubyBlock {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RubyMod.MODID);

    public static final RegistryObject<Block> RUBY_ORE = registerBlock("ruby_ore", () -> new RubyOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0f, 6.0f).requiresCorrectToolForDrops()), RubyCreativeTabs.RUBY_MOD);
    public static final RegistryObject<Block> RUBY_CHEST = registerBlock("ruby_chest", () -> new RubyChest(BlockBehaviour.Properties.of(Material.METAL).strength(6F).requiresCorrectToolForDrops().noOcclusion()), RubyCreativeTabs.RUBY_MOD);

    private  static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private  static  <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return RubyItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}