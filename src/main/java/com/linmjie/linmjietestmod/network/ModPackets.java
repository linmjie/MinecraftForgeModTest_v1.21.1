package com.linmjie.linmjietestmod.network;

import com.linmjie.linmjietestmod.TestingMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

public class ModPackets {
    public static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static void id(){
        packetId++;
    }

    public static void register(){
        INSTANCE = ChannelBuilder.named(ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "slots_machine_comms"))
                .networkProtocolVersion(1)
                .clientAcceptedVersions((s, v) -> true)
                .serverAcceptedVersions((s,v) -> true)
                .simpleChannel();

        INSTANCE.messageBuilder(SyncSlotsMachinePacket.class).encoder(SyncSlotsMachinePacket::encode).decoder(SyncSlotsMachinePacket::new)
                .consumerMainThread(SyncSlotsMachinePacket::handle).add();
    }

}
