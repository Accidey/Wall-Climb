package com.xulai.wallclimb;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class Config {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLED = BUILDER
            .comment("是否启用贴墙攀爬 # Whether wall climbing is enabled")
            .define("enabled", true);

    public static final ModConfigSpec.IntValue MAX_HEIGHT = BUILDER
            .comment("", "可攀爬的最大墙高（格）；3 格及以上永远爬不上去 # Maximum climbable wall height in blocks; three blocks or taller can never be climbed")
            .defineInRange("maxHeight", 2, 1, 2);

    public static final ModConfigSpec.DoubleValue CLIMB_SPEED = BUILDER
            .comment("", "攀爬速度（格/秒），原版梯子约为 4 # Climbing speed in blocks per second; a vanilla ladder is about 4")
            .defineInRange("climbSpeed", 3.0, 1.0, 8.0);

    public static final ModConfigSpec SPEC = BUILDER.build();

    private Config() {
    }
}
