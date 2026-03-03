package net.randommobs;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class RandomizeAttributes {
    public static void modifyAttributes(HostileEntity mob) {
        var random = mob.getRandom();


        double min = mob.getWorld().getGameRules().get(ModGameRules.MobMIN_ATTRIBUTE_MULTIPLIER).get();
        double max = mob.getWorld().getGameRules().get(ModGameRules.MobMax_ATTRIBUTE_MULTIPLIER).get();

        if(min >= max) max = min;


        // This piece of code sets the multiplier for its unique attribute
        double scaleMultiplier  = min + (max - min) * random.nextDouble();
        double damageMultiplier = min + (max - min) * random.nextDouble();
        double speedMultiplier  = min + (max - min) * random.nextDouble();
        double healthMultiplier = min + (max - min) * random.nextDouble();

        //code uses method applyMultiplier with 3 args mob,Entity attribute, and the multiplier

        applyMultiplier(mob, EntityAttributes.GENERIC_SCALE, scaleMultiplier);
        applyMultiplier(mob, EntityAttributes.GENERIC_ATTACK_DAMAGE, damageMultiplier);
        applyMultiplier(mob, EntityAttributes.GENERIC_MOVEMENT_SPEED, speedMultiplier);
        applyMultiplier(mob, EntityAttributes.GENERIC_MAX_HEALTH, healthMultiplier);

        // change in maxhealth != change in current health that fixes that
        mob.setHealth(mob.getMaxHealth());
    }

    //method that kinda drives whole operation
    private static void applyMultiplier(HostileEntity mob, RegistryEntry<EntityAttribute> attribute, double multiplier) {

        // This
        EntityAttributeInstance instance = mob.getAttributeInstance((RegistryEntry<EntityAttribute>) attribute);

        // no mob return makes sense
        if (instance == null) return;

        //sets an id for mob  also not having 2 colons is bullshit
        Identifier id = Identifier.of("randommobs", "random_" +
                ((RegistryEntry<?>) attribute).getIdAsString().replace(":", "_"));

        //prevents mobs from stacking by removing the modifier before adding, does nothing if mob has no modifier
        instance.removeModifier(id);


        //creates modifier object then kinda like setting the attribute to right one
        EntityAttributeModifier modifier = new EntityAttributeModifier(
                id, multiplier - 1.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

        // ummmm so that the modifier is not temporary and survives is world and or mob unloads then reloads
        instance.addPersistentModifier(modifier);
    }

}
