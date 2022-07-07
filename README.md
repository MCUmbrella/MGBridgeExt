This contains the source code of an external [MGBridge](https://github.com/MCUmbrella/MGBridge) subcommand implementation.
The example command is '/mgb test', wrapped in a Bukkit plugin that requires MGBridge to be installed on the Minecraft server.
It replies 'OK' when someone sends the command '/mgb test' to the Guilded server.
# How to create your own
1. Clone the MGBridge's repository and run `mvn install` to install MGBridge to your local Maven repository.
2. Create a new Maven project and add `vip.floatationdevice.mgbridge` as dependency (see this plugin's pom.xml for example).
3. Re-import your Maven project if needed.
4. Now you can access MGBridge's data and functions. Everything is ready.

# Useful classes / functions:
## MGBridge
This is MGBridge's main class. You can use `MGBridge.instance` to access what MGBridge plugin can also access.
- `MGBridge.isBound(String userId)` checks if the Guilded user is bound to a Minecraft account.
- `MGBridge.getServerId()` returns the server ID of the server that MGBridge is running on.
- `MGBridge.getChannelId(String userId)` returns the channel ID of the channel used by MGBridge.
- `MGBridge.instance.getLogger()` returns MGBridge's Logger.
- `MGBridge.instance.getG4JClient()` returns the G4JClient used by the MGBridge.
- `MGBridge.instance.getGEventListener()` returns MGBridge's Guilded event listener. You can use it to register commands (shown below).
- `MGBridge.instance.sendGuildedMessage(String msg, String replyTo, Boolean isPrivate, Boolean isSilent)` sends message to Guilded server. 
  - If you want to reply to any message, set `replyTo` to the message's ID, otherwise set that to null.
  - If you want to privately reply to a message, set `isPrivate` to true, otherwise set that to false or null.
  - If you want to reply without notifying the user, set `isSilent` to true, otherwise set that to false or null.
- `MGBridge.sendGuildedEmbed(Embed emb, String replyTo, Boolean isPrivate, Boolean isSilent)` sends an embed message to Guilded server.
  - The `replyTo`, `isPrivate` and `isSilent` parameters are the same as in `sendGuildedMessage()`.
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
- String `getCommandDescription()` returns the subcommand's description.
- String `getCommandUsage()` returns the subcommand's usage.
- boolean `execute(ChatMessage chatMessage, String[] args)` is what should be executed when the user enters this subcommand.
  - ChatMessage `chatMessage` is the corresponding message's ChatMessage object.
  - String[] `args` is the String(s) after the subcommand name, separated by spaces. For example you typed '/mgb test 123 4' and `args[]` should be {"123", "4"}
  - The return value is used to represent the result of command execution, but is not currently used anywhere by MGB.
