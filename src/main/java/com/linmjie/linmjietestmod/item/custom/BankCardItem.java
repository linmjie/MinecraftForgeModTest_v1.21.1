package com.linmjie.linmjietestmod.item.custom;

import com.linmjie.linmjietestmod.component.ModDataComponentTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;

public class BankCardItem extends Item {
    public BankCardItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if(pStack.getComponents().get(ModDataComponentTypes.EMERALDS_ACCOUNT.get()) != null) {
            pTooltipComponents.add(Component.literal("Emeralds: " + pStack.get(ModDataComponentTypes.EMERALDS_ACCOUNT.get())));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
