package fr.sqool.rubymod.block.entity;

import fr.sqool.rubymod.RubyMod;
import fr.sqool.rubymod.block.RubyBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RubyBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RubyMod.MODID);

    public static final RegistryObject<BlockEntityType<RubyBlockEntity>> RUBY_CHEST = BLOCK_ENTITIES.register("ruby_chest", () -> BlockEntityType.Builder.of(RubyBlockEntity::new, RubyBlock.RUBY_ORE.get()).build(null));
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
