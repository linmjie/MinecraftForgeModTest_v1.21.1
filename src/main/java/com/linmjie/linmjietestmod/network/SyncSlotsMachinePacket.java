package com.linmjie.linmjietestmod.network;

import com.google.common.collect.ListMultimap;
import com.linmjie.linmjietestmod.block.entity.custom.SlotsMachineBlockEntity;
import com.linmjie.linmjietestmod.util.VirtualBlitSprite;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class SyncSlotsMachinePacket {
    private BlockPos pos;
    private Level level;

    private VirtualBlitSprite[] virtualBlitSprites1;
    private VirtualBlitSprite[] virtualBlitSprites2;
    private VirtualBlitSprite[] virtualBlitSprites3;

    private static final int min_y = 20;
    private static final int max_y = 120;

    public SyncSlotsMachinePacket(BlockPos pos, Level level, VirtualBlitSprite[] virtualBlitSprites1, VirtualBlitSprite[] virtualBlitSprites2, VirtualBlitSprite[] virtualBlitSprites3){
        if (!(level instanceof ServerLevel)){
            throw new IllegalArgumentException("Ooopsies");
        }
        this.pos = pos;
        this.level = level;
        this.virtualBlitSprites1 = virtualBlitSprites1;
        this.virtualBlitSprites2 = virtualBlitSprites2;
        this.virtualBlitSprites3 = virtualBlitSprites3;
    }

    public SyncSlotsMachinePacket(FriendlyByteBuf buf){
        this.pos = buf.readBlockPos();
        if (!this.level.isClientSide() && this.level.getBlockEntity(this.pos) instanceof SlotsMachineBlockEntity machine){
            this.virtualBlitSprites1 = machine.virtualBlitSprites1;
            this.virtualBlitSprites2 = machine.virtualBlitSprites2;
            this.virtualBlitSprites3 = machine.virtualBlitSprites3;
        }
    }

    public void encode(FriendlyByteBuf buf){
        buf.writeBlockPos(pos);

    }

    public void handle(CustomPayloadEvent.Context context){
        context.enqueueWork(() -> {
            ClientLevel level = Minecraft.getInstance().level;

            if(level != null && level.getBlockEntity(pos) instanceof SlotsMachineBlockEntity machine){
                machine.virtualBlitSprites1 = this.virtualBlitSprites1;
                machine.virtualBlitSprites2 = this.virtualBlitSprites2;
                machine.virtualBlitSprites3 = this.virtualBlitSprites3;
            }
        });
        context.setPacketHandled(true);
    }
}
