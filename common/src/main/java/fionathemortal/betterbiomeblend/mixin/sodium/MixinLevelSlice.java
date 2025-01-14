package fionathemortal.betterbiomeblend.mixin.sodium;

import me.jellysquid.mods.sodium.client.world.WorldSlice;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ColorResolver;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = WorldSlice.class, remap = false)
public abstract class MixinLevelSlice implements BlockAndTintGetter {
    @Shadow(remap = false) @Final private WorldSlice level;

    /**
     * @author icanttellyou
     * @reason Use vanilla method
     */
    @Overwrite
    public int getBlockTint(BlockPos pos, ColorResolver resolver) {
        return level.getBlockTint(pos, resolver);
    }
}
