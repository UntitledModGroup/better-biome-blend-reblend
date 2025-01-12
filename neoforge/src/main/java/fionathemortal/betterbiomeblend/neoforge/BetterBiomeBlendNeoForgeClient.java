package fionathemortal.betterbiomeblend.neoforge;

import com.mojang.brigadier.CommandDispatcher;
import fionathemortal.betterbiomeblend.BetterBiomeBlend;
import fionathemortal.betterbiomeblend.BetterBiomeBlendClient;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = BetterBiomeBlend.MOD_ID, value = Dist.CLIENT)
public class BetterBiomeBlendNeoForgeClient
{
    @SubscribeEvent
    public static void
    registerCommandsEvent(RegisterCommandsEvent event)
    {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        BetterBiomeBlendClient.registerCommands(dispatcher);
    }
}
