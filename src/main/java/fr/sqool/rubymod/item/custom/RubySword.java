package fr.sqool.rubymod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RubySword extends SwordItem {
    public RubySword(Tier tier, int damage, float speed, Properties properties) {
        super(tier, damage, speed, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100));
        player.getCooldowns().addCooldown(this, 400);
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> componentsList, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, componentsList, p_41424_);

        if(Screen.hasShiftDown()) {
            componentsList.add((Component.literal(ChatFormatting.AQUA + "Donne un effet de heal")));
        }

        else {
            componentsList.add(Component.literal("Appuyer sur " + ChatFormatting.YELLOW + ChatFormatting.BOLD + "SHIFT"));
        }
    }
}
