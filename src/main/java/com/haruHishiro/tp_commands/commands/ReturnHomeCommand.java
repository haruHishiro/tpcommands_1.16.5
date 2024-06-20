package com.haruHishiro.tp_commands.commands;

import com.haruHishiro.tp_commands.TPCommands;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;

public class ReturnHomeCommand {
    public ReturnHomeCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("home").then(Commands.literal("return"))
                .executes((command) -> {
                    return returnHome(command.getSource());
                }));
    }

    private int returnHome(CommandSource source) throws CommandSyntaxException{
        ServerPlayerEntity player;
        player = source.getPlayerOrException();

        boolean hasHomePos = player.getPersistentData().getIntArray(TPCommands.MOD_ID + "home_pos").length != 0;

        if (hasHomePos){
            int [] playerPos = player.getPersistentData().getIntArray(TPCommands.MOD_ID + "home_pos");
            player.setPos(playerPos[0],playerPos[1],playerPos[2]);

            source.sendSuccess(new StringTextComponent("PlayerReturned Home!"), true);
            return 1;
        } else {
            source.sendSuccess(new StringTextComponent("No Home Position has been set"), true);
            return -1;
        }
    }
}
