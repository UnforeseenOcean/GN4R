package com.gmail.hexragon.gn4rBot.command.mod;

import com.gmail.hexragon.gn4rBot.managers.commands.CommandExecutor;
import com.gmail.hexragon.gn4rBot.managers.commands.annotations.Command;
import com.gmail.hexragon.gn4rBot.managers.commands.annotations.RequiresGuild;
import com.gmail.hexragon.gn4rBot.managers.guildMessage.GuildManager;
import com.gmail.hexragon.gn4rBot.managers.users.PermissionLevel;
import com.gmail.hexragon.gn4rBot.util.GnarMessage;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.exceptions.PermissionException;

@RequiresGuild
@Command(
        aliases = {"ban"},
        usage = "(@user)",
        description = "Ban users from the server.",
        permissionRequired = PermissionLevel.BOT_COMMANDER
)
public class BanCommand extends CommandExecutor
{
    @Override
    public void execute(GnarMessage message, String[] args)
    {
        if (message.getMentionedUsers().size() < 1)
        {
            message.reply("You did not mention a user.");
            return;
        }
        
        User target = message.getMentionedUsers().get(0);
        
        try
        {
            ((GuildManager) getGnarManager()).ban(target, 0);
        }
        catch (PermissionException e)
        {
            message.reply("GN4R does not have sufficient permission/can't ban a user with higher rank.");
            return;
        }
        message.reply("You have banned " + target.getAsMention() + ".");
    }
}
