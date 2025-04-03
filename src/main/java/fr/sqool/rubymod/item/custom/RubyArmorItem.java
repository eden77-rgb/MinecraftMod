package fr.sqool.rubymod.item.custom;

import com.google.common.eventbus.Subscribe;
import fr.sqool.rubymod.item.RubyItem;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class RubyArmorItem extends ArmorItem {
    public RubyArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);
    }

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if(fullRubyArmor(player)) {
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1));
        }

    }

    private static boolean fullRubyArmor(Player player) {
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

        return helmet.getItem() == RubyItem.RUBY_HELMET.get() &&
                chestplate.getItem() == RubyItem.RUBY_CHESTPLATE.get() &&
                leggings.getItem() == RubyItem.RUBY_LEGGINGS.get() &&
                boots.getItem() == RubyItem.RUBY_BOOTS.get();
    }
}
