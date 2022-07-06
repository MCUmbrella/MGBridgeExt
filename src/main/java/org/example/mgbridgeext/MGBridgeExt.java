package org.example.mgbridgeext;

import org.bukkit.plugin.java.JavaPlugin;

import vip.floatationdevice.guilded4j.object.ChatMessage;
import vip.floatationdevice.mgbridge.GuildedCommandExecutor;
import vip.floatationdevice.mgbridge.MGBridge;

public final class MGBridgeExt extends JavaPlugin implements GuildedCommandExecutor
{
    static MGBridgeExt instance = null;

    @Override
    public void onEnable()
    {
        instance = this;
        MGBridge.instance.getGEventListener().registerExecutor(this);
        getLogger().info("Registered test command. Type '/mgb test' in Guilded server to see the result.");
    }

    @Override
    public void onDisable()
    {
        MGBridge.instance.getGEventListener().unregisterExecutor(getCommandName());
        getLogger().info("Test command unregistered");
    }

    @Override
    public String getCommandName()
    {
        return "test";
    }

    @Override
    public String getCommandDescription()
    {
        return "Test command";
    }

    @Override
    public String getCommandUsage()
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
