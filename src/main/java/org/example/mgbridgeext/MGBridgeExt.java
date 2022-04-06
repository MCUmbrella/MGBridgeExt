package org.example.mgbridgeext;

import org.bukkit.plugin.java.JavaPlugin;
import vip.floatationdevice.guilded4j.object.ChatMessage;
import vip.floatationdevice.mgbridge.GuildedCommandExecutor;

import static vip.floatationdevice.mgbridge.MGBridge.instance;

public final class MGBridgeExt extends JavaPlugin implements GuildedCommandExecutor
{

    @Override
    public void onEnable()
    {
        instance.getGEventListener().registerExecutor(this);
        getLogger().info("Registered test command. Type '/mgb test' in Guilded server to see the result.");
    }

    @Override
    public void onDisable()
    {
        instance.getGEventListener().unregisterExecutor(getCommandName());
        getLogger().info("Test command unregistered");
    }

    @Override
    public String getCommandName()
    {
        return "test";
    }

    @Override
    public boolean execute(ChatMessage chatMessage, String[] strings)
    {
        getLogger().info("User with ID '" + chatMessage.getCreatorId() + "' issued test command");
        instance.sendGuildedMsg("Ok", chatMessage.getId());
        return true;
    }
}
