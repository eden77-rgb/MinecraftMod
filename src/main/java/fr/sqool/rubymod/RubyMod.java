package fr.sqool.rubymod;

import com.mojang.logging.LogUtils;
import fr.sqool.rubymod.block.RubyBlock;
import fr.sqool.rubymod.item.RubyItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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

        LOGGER.info("Mod started succesfuly !");
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {


    }
}
