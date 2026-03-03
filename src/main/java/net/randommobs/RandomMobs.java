package net.randommobs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomMobs implements ModInitializer {
    public static final String MOD_ID = "randommobs";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);





    @Override
    public void onInitialize() {

        ModGameRules.initialize();

        ServerEntityEvents.ENTITY_LOAD.register(
                (Entity entity, ServerWorld world) -> {

                    if (world.isClient()) return;

                    if (entity instanceof HostileEntity mob) {
                        RandomizeAttributes.modifyAttributes(mob);
                    }
                }
        );
    }
}