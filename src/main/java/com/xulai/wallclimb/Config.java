package com.xulai.wallclimb;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class Config {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    static {
        MAX_HEIGHT = BUILDER
                .comment(
                        "最大可爬高度（格）",
                        "Maximum climbable wall height in blocks",
                        "仍然爬得上去的最高墙高；填 0 关闭本模组。",
                        "Tallest wall that can still be climbed; set to 0 to disable the mod.",
                        "Default: 2 / 默认：2",
                        "Range: 0 ~ 1000"
                )
                .defineInRange("maxHeight", 2, 0, 1000);

        BUILDER.comment(" ");

        CLIMB_SPEED = BUILDER
                .comment(
                        "攀爬速度（格/秒）",
                        "Climbing speed in blocks per second",
                        "沿墙向上滑动的速度；原版梯子约为 4。",
                        "Speed of sliding up the wall; a vanilla ladder is about 4.",
                        "Default: 3.0 / 默认：3.0",
                        "Range: 1.0 ~ 8.0"
                )
                .defineInRange("climbSpeed", 3.0, 1.0, 8.0);
    }

    public static final ModConfigSpec.IntValue MAX_HEIGHT;
    public static final ModConfigSpec.DoubleValue CLIMB_SPEED;

    public static final ModConfigSpec SPEC = BUILDER.build();

    private Config() {
    }
}
