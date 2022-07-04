package org.example.mgbridgeext;

import vip.floatationdevice.guilded4j.object.ChatMessage;
import vip.floatationdevice.mgbridge.GuildedCommandExecutor;
import vip.floatationdevice.mgbridge.MGBridge;

public class GCommandTest implements GuildedCommandExecutor
{
    @Override
    public String getCommandName()
    {
        return "test";
    }

    @Override
    public String getDescription()
    {
        return "Test command";
    }

    @Override
    public String getUsage()
    {
        return "/mgb test";
    }

    @Override
    public boolean execute(ChatMessage chatMessage, String[] args)
    {
        MGBridgeExt.instance.getLogger().info("User with ID '" + chatMessage.getCreatorId() + "' issued test command");
        MGBridge.instance.sendGuildedMessage("Ok", chatMessage.getId(), null, null);
        return true;
    }
}
