package fionathemortal.betterbiomeblend.mixin;

import fionathemortal.betterbiomeblend.BetterBiomeBlendClient;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.gui.screens.options.VideoSettingsScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = VideoSettingsScreen.class)
public abstract class MixinVideoSettingsScreen extends OptionsSubScreen
{
    public
    MixinVideoSettingsScreen(Screen screen, Options options, Component component)
    {
        super(screen, options, component);
    }

    @Redirect(
        method = "qualityOptions",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/Options;biomeBlendRadius()Lnet/minecraft/client/OptionInstance;"
        )
    )
    private static OptionInstance<Integer> modifyBiomeBlendRadius(Options instance) {
        return BetterBiomeBlendClient.betterBiomeBlendRadius();
    }
}
