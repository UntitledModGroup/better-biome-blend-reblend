package fionathemortal.betterbiomeblend.mixin.sodium;

import me.jellysquid.mods.sodium.client.world.WorldSlice;
import me.jellysquid.mods.sodium.client.world.biome.BiomeColorSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ColorResolver;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = WorldSlice.class, remap = false)
public abstract class MixinWorldSlice {
    @Shadow(remap = false) @Final private ClientLevel world;

    /**
     * @author AlphaMode
     * @reason Use vanilla method
     */
    @Overwrite
    public int getColor(BiomeColorSource source, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        return switch (source) {
            case GRASS -> getBlockTint(pos, BiomeColors.GRASS_COLOR_RESOLVER);
            case FOLIAGE -> getBlockTint(pos, BiomeColors.FOLIAGE_COLOR_RESOLVER);
            case WATER -> getBlockTint(pos, BiomeColors.WATER_COLOR_RESOLVER);
        };
    }

    /**
     * @author AlphaMode
     * @reason Use vanilla method
     */
    @Overwrite
    public int getBlockTint(BlockPos pos, ColorResolver resolver) {
        return world.getBlockTint(pos, resolver);
    }
}