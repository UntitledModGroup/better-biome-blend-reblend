package fionathemortal.betterbiomeblend.fabric.mixin;

import fionathemortal.betterbiomeblend.common.ColorCaching;
import fionathemortal.betterbiomeblend.fabric.SodiumColorBlending;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import me.jellysquid.mods.sodium.client.world.WorldSlice;
import me.jellysquid.mods.sodium.client.world.biome.BiomeColorCache;


import me.jellysquid.mods.sodium.client.world.biome.BiomeSlice;
import me.jellysquid.mods.sodium.client.world.cloned.ChunkRenderContext;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.biome.BiomeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BiomeColorCache.class)
public class MixinBlockColorCache
{
    @Unique
    private int betterbiomeblend$baseX;

    @Unique
    private int betterbiomeblend$baseY;

    @Unique
    private int betterbiomeblend$baseZ;

    @Unique
    private Reference2ReferenceOpenHashMap<ColorResolver, int[]> betterbiomeblend$colors;

    @Unique
    private WorldSlice slice;

    @Inject(
        method = "update",
        at = @At("TAIL"),
            remap = false
    )
    public void
    constructorTail(ChunkRenderContext context, CallbackInfo ci)
    {
        SectionPos pos = context.getOrigin();

        this.betterbiomeblend$baseX = pos.minBlockX();
        this.betterbiomeblend$baseY = pos.minBlockY();
        this.betterbiomeblend$baseZ = pos.minBlockZ();

        this.betterbiomeblend$colors = new Reference2ReferenceOpenHashMap<>();
    }

    /**
     * @author fionathemortal
     * @reason Reimplement the getColor method to use our color cache and generate colors when necessary
     */
    @Overwrite(remap = false)
    public int
    getColor(ColorResolver resolver, int posX, int posY, int posZ)
    {
        int[] colors = this.betterbiomeblend$colors.computeIfAbsent(resolver, k -> new int[16 * 16 * 16]);

        int blockX = Mth.clamp(posX - this.betterbiomeblend$baseX, 0, 15);
        int blockY = Mth.clamp(posY - this.betterbiomeblend$baseY, 0, 15);
        int blockZ = Mth.clamp(posZ - this.betterbiomeblend$baseZ, 0, 15);

        int index = ColorCaching.getArrayIndex(16, blockX, blockY, blockZ);

        int color = colors[index];

        if (color == 0)
        {
            BiomeManager biomeManager = slice.getBiomeAccess();

            SodiumColorBlending.generateColors(
                biomeManager,
                resolver,
                blockX + this.betterbiomeblend$baseX,
                blockY + this.betterbiomeblend$baseY,
                blockZ + this.betterbiomeblend$baseZ,
                colors);

            color = colors[index];
        }

        return color;
    }
}
