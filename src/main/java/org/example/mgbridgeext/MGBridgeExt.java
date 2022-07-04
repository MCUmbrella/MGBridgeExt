package org.example.mgbridgeext;

import org.bukkit.plugin.java.JavaPlugin;

import vip.floatationdevice.mgbridge.MGBridge;

public final class MGBridgeExt extends JavaPlugin
{
    GCommandTest testCommand = new GCommandTest();
    static MGBridgeExt instance = null;

    @Override
    public void onEnable()
    {
        instance = this;
        MGBridge.instance.getGEventListener().registerExecutor(testCommand);
        getLogger().info("Registered test command. Type '/mgb test' in Guilded server to see the result.");
    }

    @Override
    public void onDisable()
    {
        MGBridge.instance.getGEventListener().unregisterExecutor(testCommand.getCommandName());
        getLogger().info("Test command unregistered");
    }
}
