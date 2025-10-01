package fionathemortal.betterbiomeblend.forge;

import com.mojang.brigadier.CommandDispatcher;
import fionathemortal.betterbiomeblend.BetterBiomeBlendClient;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
