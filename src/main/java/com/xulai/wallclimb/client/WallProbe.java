package com.xulai.wallclimb.client;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

final class WallProbe {

    private static final double FORWARD = 0.4;
    private static final double EPS = 1.0E-7;

    record Wall(double topY) {
    }

    private WallProbe() {
    }

    static Wall startWall(Player player, Vec3 dir, int maxHeight) {
        if (!player.onGround()) {
            return null;
        }
        Level level = player.level();
        BlockPos column = frontColumn(level, player, dir);
        if (column == null) {
            return null;
        }
        int baseY = Mth.floor(player.getY() + 0.05);
        int height = 0;
        while (height <= maxHeight) {
            BlockPos pos = new BlockPos(column.getX(), baseY + height, column.getZ());
            if (level.getBlockState(pos).getCollisionShape(level, pos).isEmpty()) {
                break;
            }
            height++;
        }
        if (height == 0 || height > maxHeight) {
            return null;
        }
        double topY = baseY + height;
        AABB standing = new AABB(
                column.getX(), topY, column.getZ(),
                column.getX() + 1.0, topY + player.getBbHeight(), column.getZ() + 1.0).deflate(EPS);
        if (!level.noCollision(player, standing)) {
            return null;
        }
        return new Wall(topY);
    }

    static boolean touching(Player player, Vec3 dir) {
        return frontColumn(player.level(), player, dir) != null;
    }

    private static BlockPos frontColumn(Level level, Player player, Vec3 dir) {
        AABB reach = player.getBoundingBox().move(dir.x * FORWARD, 0.0, dir.z * FORWARD);
        BlockPos min = BlockPos.containing(reach.minX, reach.minY, reach.minZ);
        BlockPos max = BlockPos.containing(reach.maxX - EPS, reach.maxY - EPS, reach.maxZ - EPS);
        BlockPos best = null;
        double bestAhead = Double.NEGATIVE_INFINITY;
        for (BlockPos pos : BlockPos.betweenClosed(min, max)) {
            VoxelShape shape = level.getBlockState(pos).getCollisionShape(level, pos);
            if (shape.isEmpty() || !shape.bounds().move(pos).intersects(reach)) {
                continue;
            }
            double ahead = (pos.getX() + 0.5 - player.getX()) * dir.x + (pos.getZ() + 0.5 - player.getZ()) * dir.z;
            if (ahead > bestAhead) {
                bestAhead = ahead;
                best = pos.immutable();
            }
        }
        return best;
    }
}
