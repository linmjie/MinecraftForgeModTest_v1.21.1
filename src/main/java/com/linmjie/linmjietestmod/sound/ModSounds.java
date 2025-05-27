package com.linmjie.linmjietestmod.sound;

import com.linmjie.linmjietestmod.TestingMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TestingMod.MOD_ID);

    public static final RegistryObject<SoundEvent> I_DO = registrySoundEvent("i_do");
    public static final RegistryObject<SoundEvent> MONKEY_NOISE = registrySoundEvent("monkey_noise");

    public static final RegistryObject<SoundEvent> JACK_BLACK_AMBIENT = registrySoundEvent("jack_black_ambient");
    public static final RegistryObject<SoundEvent> LAVA_CHICKEN = registrySoundEvent("lava_chicken");
    public static final RegistryObject<SoundEvent> THE_NETHER = registrySoundEvent("the_nether");
    public static final RegistryObject<SoundEvent> ENDER_PEARL = registrySoundEvent("ender_pearl");
    public static final RegistryObject<SoundEvent> FLINT_AND_STEEL = registrySoundEvent("flint_and_steel");
    public static final RegistryObject<SoundEvent> WATER_BUCKET_RELEASE = registrySoundEvent("water_bucket_release");

    public static final ResourceKey<JukeboxSong> I_DO_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "i_do"));

    private static RegistryObject<SoundEvent> registrySoundEvent(String name){
        return SOUND_EVENTS.register(name,
                () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, name)));
    }
    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
