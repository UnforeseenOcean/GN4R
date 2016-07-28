package com.gmail.hexragon.gn4rBot.command.media;

import com.gmail.hexragon.gn4rBot.managers.commands.Command;
import com.gmail.hexragon.gn4rBot.managers.commands.CommandExecutor;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@Command(
		aliases = {"rcg"},
		description = "Random shit-post generation!"
)
public class ExplosmRCGCommand extends CommandExecutor
{
	@Override
	public void execute(MessageReceivedEvent event, String[] args)
	{
		try
		{
			Document document;
			
			document = Jsoup.connect("http://explosm.net/rcg").get();
			
			Element element = document.getElementById("rcg-comic").getElementsByTag("img").get(0);
			
			String builder =
					"Cyanide and Happiness" + "\n" +
							"**Random Comic Generator**\n" +
							"Link: " + element.absUrl("src");
			
			event.getChannel().sendMessage(builder);
			
		}
		catch (Exception e)
		{
			event.getChannel().sendMessage(String.format("%s ➤ Unable to grab random Cyanide and Happiness comic.", event.getAuthor().getAsMention()));
			e.printStackTrace();
		}
	}
}
