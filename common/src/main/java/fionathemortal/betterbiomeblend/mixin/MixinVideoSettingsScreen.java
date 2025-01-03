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
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = VideoSettingsScreen.class)
public abstract class MixinVideoSettingsScreen extends OptionsSubScreen
{
    public
    MixinVideoSettingsScreen(Screen screen, Options options, Component component)
    {
        super(screen, options, component);
    }

    @ModifyArg(method = "addOptions", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/OptionsList;addBig(Lnet/minecraft/client/OptionInstance;)V", ordinal = 1), index = 0)
    private OptionInstance<?>
    modifyAddBig(OptionInstance<?> argument)
    {
        OptionInstance<?> result = argument;

        if (argument == this.options.biomeBlendRadius())
        {
            result = BetterBiomeBlendClient.betterBiomeBlendRadius;
        }

        return result;
    }
}
