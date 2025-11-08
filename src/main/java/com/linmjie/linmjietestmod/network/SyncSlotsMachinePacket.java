package com.linmjie.linmjietestmod.network;

import com.linmjie.linmjietestmod.block.entity.custom.SlotsMachineBlockEntity;
import com.linmjie.linmjietestmod.util.VirtualBlitSprite;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.network.CustomPayloadEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public class SyncSlotsMachinePacket {
    private static final int MIN_Y = 20;
    private static final int MAX_Y = 120;

    private BlockPos pos;
    private LinkedHashMap<ResourceLocation, int[]>[] virtualSpriteMaps = new LinkedHashMap[3];



    public SyncSlotsMachinePacket(BlockPos pos, VirtualBlitSprite[][] virtualBlitSprites){
        this.pos = pos;
        for (int i = 0; i < virtualBlitSprites.length; i++) {
            virtualSpriteMaps[i] = new LinkedHashMap<>();
            for (int j = 0; j < virtualBlitSprites[i].length; j++) {
                VirtualBlitSprite sprite = virtualBlitSprites[i][j];
                virtualSpriteMaps[i].putLast(sprite.getResourceLocation(),
                        new int[]{sprite.getX(), sprite.getY()});
            }
        }
    }

    public SyncSlotsMachinePacket(FriendlyByteBuf buf){
        this.pos = buf.readBlockPos();
        //decode sequence of resLocs and ints into arr<linkedMap<ResLoc, int[]>>
        //objects have a constant size(the map's size is undetermined), arr -> 3 maps, each map -> C(one ResLoc, 2 ints)
        //therefore, 3C ResLocs and 6C ints
        int mapSize = buf.readInt();
        for (int i = 0; i < 3; i++) {
            LinkedHashMap<ResourceLocation, int[]> map = new LinkedHashMap<>();
            for (int j = 0; j < mapSize; j++) {
                ResourceLocation resLoc = buf.readResourceLocation();
                int[] intPair = new int[2];
                intPair[0] = buf.readInt();
                intPair[1] = buf.readInt();
                map.putLast(resLoc, intPair);
            }
            this.virtualSpriteMaps[i] = map;
        }
    }

    public void encode(FriendlyByteBuf buf){
        buf.writeBlockPos(pos);
        //encode arr<linkedMap<ResLoc, int[]> as sequence of resLocs and ints
        buf.writeInt(virtualSpriteMaps[0].size());
        for (var map: virtualSpriteMaps) {
            for (Map.Entry<ResourceLocation, int[]> entry: map.entrySet()){
                buf.writeResourceLocation(entry.getKey());
                buf.writeInt(entry.getValue()[0]);
                buf.writeInt(entry.getValue()[1]);
            }
        }
    }

    public void handle(CustomPayloadEvent.Context context){
        context.enqueueWork(() -> {
            ClientLevel level = Minecraft.getInstance().level;

            if(level != null && level.getBlockEntity(pos) instanceof SlotsMachineBlockEntity machine){
                for (int i = 0; i < virtualSpriteMaps.length; i++) {
                    int j = 0;
                    for (Map.Entry<ResourceLocation, int[]> entry: virtualSpriteMaps[i].entrySet()){
                        machine.virtualBlitSprites[i][j] = new VirtualBlitSprite(entry.getKey(),
                                entry.getValue()[0], entry.getValue()[1], MIN_Y, MAX_Y);
                        j++;
                    }
                }
            }
        });
        context.setPacketHandled(true);
    }
}
