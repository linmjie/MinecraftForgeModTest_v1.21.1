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
        public static final TagKey<Block> NEEDS_URANIUM_TOOL = createTag("needs_uranium_tool");
        public static final TagKey<Block> INCORRECT_FOR_URANIUM_TOOL = createTag("incorrect_for_uranium_tool");
        public static final TagKey<Block> NEEDS_NETHERITE_TOOL = createTag("needs_netherite_tool");

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
