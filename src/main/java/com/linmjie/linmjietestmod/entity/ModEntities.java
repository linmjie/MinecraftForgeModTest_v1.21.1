package com.linmjie.linmjietestmod.entity;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.entity.custom.EvanEntity;
import com.linmjie.linmjietestmod.entity.custom.JackBlackEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TestingMod.MOD_ID);

    public static final RegistryObject<EntityType<EvanEntity>> BETA_EVAN =
            ENTITY_TYPE.register("evan", () -> EntityType.Builder.of(EvanEntity::new, MobCategory.CREATURE)
                    .sized(1.0F, 1.5F).build("evan"));
    public static final RegistryObject<EntityType<JackBlackEntity>> JACK_BLACK =
            ENTITY_TYPE.register("jack_black", () -> EntityType.Builder.of(JackBlackEntity::new, MobCategory.CREATURE)
                    .sized(1F, 2.2F).build("jack_black"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPE.register(eventBus);
    }
}
