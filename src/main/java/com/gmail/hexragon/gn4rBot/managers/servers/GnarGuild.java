package com.gmail.hexragon.gn4rBot.managers.servers;

import com.gmail.hexragon.gn4rBot.command.admin.JavascriptCommand;
import com.gmail.hexragon.gn4rBot.command.admin.MemoryReportCommand;
import com.gmail.hexragon.gn4rBot.command.admin.ReassignPermissionCommand;
import com.gmail.hexragon.gn4rBot.command.admin.ReassignTokenCommand;
import com.gmail.hexragon.gn4rBot.command.ai.CleverbotCommand;
import com.gmail.hexragon.gn4rBot.command.ai.PandorabotCommand;
import com.gmail.hexragon.gn4rBot.command.fun.*;
import com.gmail.hexragon.gn4rBot.command.games.GameLookupCommand;
import com.gmail.hexragon.gn4rBot.command.games.LeagueLookupCommand;
import com.gmail.hexragon.gn4rBot.command.games.OverwatchLookupCommand;
import com.gmail.hexragon.gn4rBot.command.general.*;
import com.gmail.hexragon.gn4rBot.command.media.*;
import com.gmail.hexragon.gn4rBot.command.mod.BanCommand;
import com.gmail.hexragon.gn4rBot.command.mod.MuteCommand;
import com.gmail.hexragon.gn4rBot.command.mod.UnmuteCommand;
import com.gmail.hexragon.gn4rBot.managers.commands.CommandManager;
import com.gmail.hexragon.gn4rBot.managers.users.UserManager;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.managers.GuildManager;

public class GnarGuild extends GuildManager
{
	private final String accessID;
	private final UserManager userManager;
	private final CommandManager commandManager;
	private final ServerManagers manager;

	private final String basePath;
	//private final File baseFile;
	
	public GnarGuild(String accessID, ServerManagers manager, Guild guild)
	{
		super(guild);

		this.accessID = accessID;
		this.basePath = String.format("_DATA/servers/%s/", accessID);
		this.manager = manager;

//		baseFile = new File(basePath);
//		if (!baseFile.exists()) //noinspection ResultOfMethodCallIgnored
//			baseFile.mkdirs();

		this.userManager = new UserManager(this);
		this.commandManager = new CommandManager(this);
	}

	public void defaultSetup()
	{
		commandManager.builder("help", "guide").executor(HelpCommand.class);
		commandManager.builder("info", "botinfo").executor(BotInfoCommand.class);
		commandManager.builder("whois", "infoon", "infoof").executor(WhoIsCommand.class);
		commandManager.builder("invite", "invitebot").executor(InviteBotCommand.class);
		commandManager.builder("uptime").executor(UptimeCommand.class);
		commandManager.builder("math").executor(MathCommand.class);
		
		commandManager.builder("rand", "random", "rnd", "roll").executor(RollCommand.class);
		commandManager.builder("coinflip", "flip").executor(CoinFlipCommand.class);
		commandManager.builder("8ball").executor(EightBallCommand.class);
		
		commandManager.builder("google").executor(GoogleCommand.class);
		commandManager.builder("youtube").executor(YoutubeCommand.class);

		commandManager.builder("ban").executor(BanCommand.class);
		commandManager.builder("mute").executor(MuteCommand.class);
		commandManager.builder("unmute").executor(UnmuteCommand.class);
		

		commandManager.builder("cbot", "cleverbot").executor(CleverbotCommand.class);
		commandManager.builder("pbot", "pandorabot").executor(PandorabotCommand.class);
		commandManager.builder("tts", "texttospeech").executor(TextToSpeechCommand.class);

		commandManager.registerCommand(xkcdCommand.class);
		commandManager.registerCommand(ExplosmCommand.class);
		commandManager.registerCommand(ExplosmRCGCommand.class);
		commandManager.registerCommand(GarfieldCommand.class);
		commandManager.registerCommand(GetMediaCommand.class);
		commandManager.registerCommand(ListMediaCommand.class);
		commandManager.registerCommand(CatsCommand.class);
		
		
		commandManager.builder("googleyeyes", "eyes").executor(GoogleyEyesCommand.class);
		commandManager.builder("discordgold").executor(DiscordGoldCommand.class);
		commandManager.builder("goodshit").executor(GoodShitCommand.class);
		
		
		commandManager.builder("gamelookup", "game").executor(GameLookupCommand.class);
		commandManager.builder("league", "lol").executor(LeagueLookupCommand.class);
		commandManager.builder("overwatch", "ow").executor(OverwatchLookupCommand.class);
		

//		commandManager.builder("kotlin_test").executor(KOTLIN_KotlinBase.class);
//		commandManager.builder("kotlin_xkcd").executor(KOTLIN_xkcdCommand.class);
//		commandManager.builder("test").executor(TestCommand.class);
		
		
//		commandManager.builder("joinchannel").executor(JoinChannelCommand.class);
//		commandManager.builder("queue").executor(QueueCommand.class);
//		commandManager.builder("volume").executor(VolumeCommand.class);
//		commandManager.builder("play", "resume").executor(PlayCommand.class);
//		commandManager.builder("leavechannel").executor(LeaveChannelCommand.class);
//		commandManager.builder("nowplaying").executor(NowPlayingCommand.class);
//		commandManager.builder("repeat").executor(RepeatCommand.class);
//		commandManager.builder("skip").executor(SkipCommand.class);
//		commandManager.builder("stop").executor(StopCommand.class);
//		commandManager.builder("restart").executor(RestartCommand.class);
//		commandManager.builder("resetmusic").executor(ResetCommand.class);
		
		commandManager.builder("reassigntoken", "rtoken").executor(ReassignTokenCommand.class);
		commandManager.builder("runjs", "javascript").executor(JavascriptCommand.class);
		commandManager.builder("reassignperm", "changeperm", "reassignpermission").executor(ReassignPermissionCommand.class);
		commandManager.builder("memory").executor(MemoryReportCommand.class);
	}
	
	public CommandManager getCommandManager()
	{
		return commandManager;
	}

	
	public UserManager getUserManager()
	{
		return userManager;
	}

	
	public String getAccessID()
	{
		return accessID;
	}

	
	public ServerManagers getServerManager()
	{
		return manager;
	}

	
	public void messageEvent(MessageReceivedEvent event)
	{
		getCommandManager().callCommand(event);
	}

//
//	public File getBaseFile()
//	{
//		return baseFile;
//	}

	
	public String getBasePath()
	{
		return basePath;
	}

}
