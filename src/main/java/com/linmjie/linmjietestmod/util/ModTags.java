package com.linmjie.linmjietestmod.util;

import com.linmjie.linmjietestmod.TestingMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {

        private static net.minecraft.tags.TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, name));
        }
    }

    public static class Items{
        public static final TagKey<Item> DEEPSLATE_SOLID = createTag("deepslate_solid");
        private static net.minecraft.tags.TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, name));
        }
    }
}
