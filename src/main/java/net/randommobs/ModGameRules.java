package net.randommobs;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.gamerule.v1.rule.DoubleRule;
import net.minecraft.world.GameRules;

public class ModGameRules {

    public static final GameRules.Key<DoubleRule> MobMIN_ATTRIBUTE_MULTIPLIER = GameRuleRegistry.register(
                    "MobMinAttributeMultiplier",
                    net.minecraft.world.GameRules.Category.MOBS,
                    GameRuleFactory.createDoubleRule(0.8)
            );
    public static final GameRules.Key<DoubleRule> MobMax_ATTRIBUTE_MULTIPLIER = GameRuleRegistry.register(
            "MobMaxAttributeMultiplier",
            net.minecraft.world.GameRules.Category.MOBS,
            GameRuleFactory.createDoubleRule(1.2)
            );


    public static void initialize() {}
}

