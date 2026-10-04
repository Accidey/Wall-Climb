package com.xulai.wallclimb.client;

import com.xulai.wallclimb.Config;
import com.xulai.wallclimb.WallClimb;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@Mod(value = WallClimb.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = WallClimb.MODID, value = Dist.CLIENT)
public final class WallClimbClient {

    private static final double GRAVITY_STEP = 0.08;
    private static final double MOUNT_PUSH = 0.2;
    private static final double MOUNT_RISE = 0.09;
    private static final int MOUNT_TICKS = 4;
    private static final double TOP_EPS = 1.0E-3;

    private static double targetTopY = Double.NaN;
    private static Vec3 targetDir = Vec3.ZERO;
    private static Vec3 mountDir = Vec3.ZERO;
    private static int mounting;

    public WallClimbClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onPlayerTick(PlayerTickEvent.Pre event) {
        if (!(event.getEntity() instanceof LocalPlayer player)) {
            return;
        }
        if (mounting > 0) {
            mounting--;
            double push = mounting == 0 ? 0.0 : MOUNT_PUSH;
            player.setDeltaMovement(mountDir.x * push, MOUNT_RISE, mountDir.z * push);
            player.hasImpulse = true;
            player.fallDistance = 0.0F;
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        if (Config.MAX_HEIGHT.get() <= 0 || mc.screen != null || !mc.isWindowActive()
                || !mc.options.keyJump.isDown() || unavailable(player)) {
            targetTopY = Double.NaN;
            return;
        }
        Vec3 dir = Vec3.directionFromRotation(0.0F, player.getYRot());
        WallProbe.Wall wall = WallProbe.startWall(player, dir, Config.MAX_HEIGHT.get());
        if (wall != null) {
            targetTopY = wall.topY();
            targetDir = dir;
        }
        if (Double.isNaN(targetTopY)) {
            return;
        }
        double feetY = player.getY();
        if (feetY >= targetTopY - TOP_EPS) {
            mountDir = targetDir;
            mounting = MOUNT_TICKS;
            targetTopY = Double.NaN;
            return;
        }
        if (!WallProbe.touching(player, targetDir)) {
            targetTopY = Double.NaN;
            return;
        }
        double step = Config.CLIMB_SPEED.get() / 20.0 + GRAVITY_STEP;
        double rise = Math.min(step, targetTopY - feetY + GRAVITY_STEP);
        Vec3 motion = player.getDeltaMovement();
        player.setDeltaMovement(motion.x, rise, motion.z);
        player.hasImpulse = true;
        player.fallDistance = 0.0F;
        if (player.onGround()) {
            player.setOnGround(false);
        }
    }

    private static boolean unavailable(LocalPlayer player) {
        return player.isSpectator() || player.getAbilities().flying || player.isFallFlying()
                || player.isPassenger() || player.onClimbable() || player.isInWater() || player.isSwimming();
    }
}
