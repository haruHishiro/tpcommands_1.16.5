package com.haruHishiro.tp_commands.events;

import com.haruHishiro.tp_commands.TPCommands;
import com.haruHishiro.tp_commands.commands.ReturnHomeCommand;
import com.haruHishiro.tp_commands.commands.SetHomeCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = TPCommands.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event){
        new SetHomeCommand(event.getDispatcher());
        new ReturnHomeCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event){
        if(event.getOriginal().isAddedToWorld()){
            event.getPlayer().getPersistentData().putIntArray(TPCommands.MOD_ID + "home_pos",
                    event.getOriginal().getPersistentData().getIntArray(TPCommands.MOD_ID + "home_pos"));
        }
    }
}
