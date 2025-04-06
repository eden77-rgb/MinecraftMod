package fr.sqool.rubymod;

import com.mojang.logging.LogUtils;
import fr.sqool.rubymod.block.RubyBlock;
import fr.sqool.rubymod.block.entity.RubyBlockEntities;
import fr.sqool.rubymod.block.entity.RubyBlockEntity;
import fr.sqool.rubymod.item.RubyItem;
import fr.sqool.rubymod.screen.RubyChestMenu;
import fr.sqool.rubymod.screen.RubyChestScreen;
import fr.sqool.rubymod.screen.RubyMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(RubyMod.MODID)
public class RubyMod {

    public static final String MODID = "rubymod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public RubyMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        RubyItem.register(modEventBus);
        RubyBlock.register(modEventBus);
        RubyBlockEntities.register(modEventBus);

        RubyMenuTypes.register(modEventBus);

        LOGGER.info("Mod started succesfuly !");
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(RubyMenuTypes.CHEST_MENU.get(), RubyChestScreen::new);
        }
    }
}
