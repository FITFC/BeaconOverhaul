package dev.sapphic.beacons.mixin;

import dev.sapphic.beacons.BeaconMobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Player.class)
abstract class PlayerMixin extends LivingEntity {
  PlayerMixin(final EntityType<? extends LivingEntity> type, final Level level) {
    super(type, level);
  }

  @ModifyVariable(
      method = "canEat(" + "Z" + ")Z",
      require = 1,
      allow = 1,
      argsOnly = true,
      at = @At("HEAD"))
  private boolean orHasNutritionEffect(final boolean alwaysEdibleFood) {
    return alwaysEdibleFood || this.hasEffect(BeaconMobEffects.NUTRITION);
  }
}
