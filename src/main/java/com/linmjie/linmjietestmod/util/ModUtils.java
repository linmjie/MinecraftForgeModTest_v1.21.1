package com.linmjie.linmjietestmod.util;

import com.google.common.collect.Lists;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Arrays;
import java.util.List;

public class ModUtils {
    public static VoxelShape[] makeDirectionalVoxelShapes (VoxelShape voxelShape){
        List<Double> list = Lists.newArrayList();
        voxelShape.forAllBoxes(
                (x1, y1, z1, x2, y2, z2) -> {
                    //when constructing, the constructor method divides all doubles by 16, so this reverses that operation
                    list.add(x1*16);
                    list.add(y1*16);
                    list.add(z1*16);
                    list.add(x2*16);
                    list.add(y2*16);
                    list.add(z2*16);
                });
        System.out.println(list);
        return makeDirectionalVoxelShapes(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
    }

    public static VoxelShape[] makeDirectionalVoxelShapes(double x1, double y1, double z1, double x2, double y2, double z2) {
        return new VoxelShape[]{
                Block.box(
                        Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2),
                        Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2)
                ),// NORTH
                Block.box(
                        Math.min(z1, z2), Math.min(y1, y2), Math.min(16 - x2, 16 - x1),
                        Math.max(z1, z2), Math.max(y1, y2), Math.max(16 - x2, 16 - x1)
                ),// WEST
                Block.box(
                        Math.min(16 - x2, 16 - x1), Math.min(y1, y2), Math.min(16 - z2, 16 - z1),
                        Math.max(16 - x2, 16 - x1), Math.max(y1, y2), Math.max(16 - z2, 16 - z1)
                ),// SOUTH
                Block.box(
                        Math.min(16 - z2, 16 - z1), Math.min(y1, y2), Math.min(x1, x2),
                        Math.max(16 - z2, 16 - z1), Math.max(y1, y2), Math.max(x1, x2)
                ) // EAST
        };
    }


    public static VoxelShape[] makeDirectionalVoxelShapes (VoxelShape[] stdVoxelShapeArr){
        VoxelShape[] voxelShapes = new VoxelShape[stdVoxelShapeArr.length*4];
        for (int i = 0; i < stdVoxelShapeArr.length; i++) {
            VoxelShape[] voxelShapesForOneStage = makeDirectionalVoxelShapes(stdVoxelShapeArr[i]);
            for (int j = 0; j < voxelShapesForOneStage.length; j++) {
                voxelShapes[i*4 +j] = voxelShapesForOneStage[j];
            }
        }
        return voxelShapes;
    }

    public static int getIndexForDirectionalVoxelShape (int intProperty, BlockState state, DirectionProperty facing, int maxValue){
        int adder = maxValue+1;
        return switch (state.getValue(facing)) {
            case WEST -> adder*intProperty+1;
            case SOUTH -> adder*intProperty+2;
            case EAST -> adder*intProperty+3;
            default -> adder*intProperty;
        };
    }
}
