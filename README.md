This is an external [MGBridge](https://github.com/MCUmbrella/MGBridge) command implementation.
The command is '/mgb test', wrapped in a Bukkit plugin that requires MGBridge to be installed on the Minecraft server.
It replies 'OK' when the command is executed.
# How to create your own
1. Clone the MGBridge's repository and run `mvn install` to install MGBridge to your local Maven repository.
2. Create a new Maven project and add `vip.floatationdevice.mgbridge` as dependency (see this plugin's pom.xml for example).
3. Re-import your Maven project if needed.
4. Now you can access MGBridge's data and functions. Everything is ready.

# Useful classes / functions:
## MGBridge
This is MGBridge's main class. You can use `MGBridge.instance` to access what MGBridge plugin can also access.
- `MGBridge.instance.getLogger()` returns MGBridge's Logger.
- `MGBridge.instance.getGEventListener()` returns MGBridge's Guilded event listener. You can use it to register commands.
- `MGBridge.instance.sendGuildedMsg(String msg, String replyTo)` sends message to Guilded server. If you want to reply to any message, set `replyTo` to the message's ID, otherwise set that to null.
## BindManager
This is MGBridge's account binding system.
-  HashMap<String, UUID> `BindManager.bindMap` has players' bind info stored.
- `BindManager.saveBindMap()` saves the bind info to a file called 'bindMap.dat'.
- `BindManager.loadBindMap()` loads bind info from the file 'bindMap.dat'.
## GuildedEventListener
This class manages incoming Guilded messages, MGB commands and some other events. It must be accessed using `MGBridge.instance.getGEventListener()`.
- `registerExecutor(GuildedCommandExecutor)` register a GuildedCommandExecutor. It adds a new subcommand to MGB on the Guilded side.
- `unregisterExecutor(String)` unregister the subcommand with the specified name.
## interface GuildedCommandExecutor
- String `getCommandName()` returns the subcommand's name.
- boolean `execute(ChatMessage chatMessage, String[] args)` is what should be executed when the user enters this subcommand.
  - ChatMessage `chatMessage` is the corresponding message's ChatMessage object.
  - String[] `args` is the String(s) after the subcommand name, separated by spaces. For example you typed '/mgb test 123 4' and `args[]` should be {"123", "4"}
