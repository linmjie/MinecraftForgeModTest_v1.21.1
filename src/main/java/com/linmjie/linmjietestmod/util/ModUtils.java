package com.linmjie.linmjietestmod.util;

import com.google.common.collect.Lists;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class ModUtils {
    public static VoxelShape[] makeDirectionalVoxelShapes (VoxelShape voxelShape){
        List<Double> list = Lists.newArrayList();
        voxelShape.forAllBoxes(
                (x1, y1, z1, x2, y2, z2) -> {
                    //when constructing the voxel shape, the constructor method divides all doubles by 16, so this reverses that operation
                    list.add(x1*16);
                    list.add(y1*16);
                    list.add(z1*16);
                    list.add(x2*16);
                    list.add(y2*16);
                    list.add(z2*16);
                });
        return makeDirectionalVoxelShapes(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
    }

    public static VoxelShape[] makeDirectionalVoxelShapes(double x1, double y1, double z1, double x2, double y2, double z2) {
        return new VoxelShape[]{
                Block.box( // NORTH
                        Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2),
                        Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2)
                ),
                Block.box( // WEST
                        Math.min(z1, z2), Math.min(y1, y2), Math.min(16 - x2, 16 - x1),
                        Math.max(z1, z2), Math.max(y1, y2), Math.max(16 - x2, 16 - x1)
                ),
                Block.box( // SOUTH
                        Math.min(16 - x2, 16 - x1), Math.min(y1, y2), Math.min(16 - z2, 16 - z1),
                        Math.max(16 - x2, 16 - x1), Math.max(y1, y2), Math.max(16 - z2, 16 - z1)
                ),
                Block.box( // EAST
                        Math.min(16 - z2, 16 - z1), Math.min(y1, y2), Math.min(x1, x2),
                        Math.max(16 - z2, 16 - z1), Math.max(y1, y2), Math.max(x1, x2)
                )
        };
    }


    public static VoxelShape[] makeDirectionalVoxelShapes (VoxelShape[] stdVoxelShapeArr){
        VoxelShape[] voxelShapes = new VoxelShape[stdVoxelShapeArr.length*4];
        for (int i = 0; i < stdVoxelShapeArr.length; i++) {
            VoxelShape[] voxelShapesForOneStage = makeDirectionalVoxelShapes(stdVoxelShapeArr[i]);
            System.arraycopy(voxelShapesForOneStage, 0, voxelShapes, i*4, voxelShapesForOneStage.length);
        }
        return voxelShapes;
    }

    public static int getIndexForDirectionalVoxelShape (int intProperty, Direction direction, int maxValue){
        int adder = maxValue+1;
        return switch (direction) {
            case WEST -> adder*intProperty+1;
            case SOUTH -> adder*intProperty+2;
            case EAST -> adder*intProperty+3;
            default -> adder*intProperty;
        };
    }

    public static int randomIndex(int max){
        return Math.toIntExact((long) (Math.random() * max));
    }
    public static int randomInt(int i){
        return Math.toIntExact((long) Math.ceil(Math.random() * i));
    }
    public static int randomInt(int min, int max){
        return randomInt(max-min) + min;
    }
}
