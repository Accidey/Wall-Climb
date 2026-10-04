package com.xulai.wallclimb;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(WallClimb.MODID)
public class WallClimb {

    public static final String MODID = "wallclimb";

    public WallClimb(ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
    }
}
