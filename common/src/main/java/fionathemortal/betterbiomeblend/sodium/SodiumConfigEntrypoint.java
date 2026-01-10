package fionathemortal.betterbiomeblend.sodium;

import fionathemortal.betterbiomeblend.BetterBiomeBlend;
import fionathemortal.betterbiomeblend.BetterBiomeBlendClient;
import net.caffeinemc.mods.sodium.api.config.ConfigEntryPoint;
import net.caffeinemc.mods.sodium.api.config.option.OptionFlag;
import net.caffeinemc.mods.sodium.api.config.option.OptionImpact;
import net.caffeinemc.mods.sodium.api.config.structure.ConfigBuilder;
import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class SodiumConfigEntrypoint implements ConfigEntryPoint {
    @Override
    public void registerConfigLate(ConfigBuilder configBuilder) {
        Minecraft mc = Minecraft.getInstance();

        configBuilder.registerOwnModOptions().setName("Better Biome Reblend")
            .setVersion(BetterBiomeBlend.VERSION)
            .setNonTintedIcon(Identifier.fromNamespaceAndPath("betterbiomeblend", "textures/gui/icon.png"))
            .addPage(configBuilder.createOptionPage().setName(Component.literal("Configuration")).addOptionGroup(
                configBuilder.createOptionGroup().addOption(
                    configBuilder.createIntegerOption(Identifier.parse("betterbiomeblend:quality.biome_blend"))
                        .setStorageHandler(mc.options == null ? null : () -> {
                            mc.options.save();

                            SodiumClientMod.logger().info("Flushed changes to Minecraft configuration");
                        })
                        .setName(Component.translatable("options.biomeBlendRadius"))
                        .setValueFormatter(value -> {
                            if (value >= 0 && value <= 14) {
                                if (value == 0) {
                                    return Component.translatable("gui.none");
                                } else {
                                    int radius = 2 * value + 1;
                                    return Component.translatable("sodium.options.biome_blend.value", radius, radius);
                                }
                            } else {
                                return Component.translatable("parsing.int.invalid", value);
                            }
                        })
                        .setTooltip(Component.translatable("sodium.options.biome_blend.tooltip"))
                        .setRange(0, 14, 1)
                        .setDefaultValue(14)
                        .setBinding(BetterBiomeBlendClient.betterBiomeBlendRadius()::set, BetterBiomeBlendClient.betterBiomeBlendRadius()::get)
                        .setImpact(OptionImpact.LOW)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
            )));
    }
}
