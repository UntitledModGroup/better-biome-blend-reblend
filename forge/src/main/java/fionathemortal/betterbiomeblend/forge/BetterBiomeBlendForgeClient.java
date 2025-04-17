package fionathemortal.betterbiomeblend.forge;

import com.mojang.brigadier.CommandDispatcher;
import fionathemortal.betterbiomeblend.BetterBiomeBlend;
import fionathemortal.betterbiomeblend.BetterBiomeBlendClient;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterBiomeBlend.MOD_ID, value = Dist.CLIENT)
public class BetterBiomeBlendForgeClient
{
    @SubscribeEvent
    public static void
    registerCommandsEvent(RegisterCommandsEvent event)
    {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        BetterBiomeBlendClient.registerCommands(dispatcher);
    }
}
