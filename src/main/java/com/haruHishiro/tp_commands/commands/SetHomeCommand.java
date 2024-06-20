package com.haruHishiro.tp_commands.commands;

import com.haruHishiro.tp_commands.TPCommands;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;

public class SetHomeCommand {
    public SetHomeCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("home").then(Commands.literal("set"))
                .executes((command) -> {

                    return setHome(command.getSource());
                }));
    }

    private int setHome(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player;
        player = source.getPlayerOrException();
        Vector3d playerPos = source.getPosition();
        String pos = "(" + playerPos.x + ", " + playerPos.y + ", " + playerPos.z + ")";

        player.getPersistentData().putIntArray(TPCommands.MOD_ID + "home_pos",
                new int[] {(int) playerPos.x, (int) playerPos.y, (int) playerPos.z});

        source.sendSuccess(new StringTextComponent("Set home at" + pos), true);

        return 1;
    }
}
