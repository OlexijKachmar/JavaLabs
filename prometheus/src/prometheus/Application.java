//package com.tasks5.command;
package prometheus;

public class Application
{
	public static void main(String[] args)
	{
		Command command;
		if(args == null || args.length == 0)
		{
			System.out.println("Error");
		}
		else
		{
			if(args.length == 2)
			{
				if(args[0].equals("date") && args[1].equals("now"))
				{
					command = new Now();
					command.execute();
				}
				else if(args[0].equals("echo"))
				{
					command = new Echo(args[1]);
					command.execute();
				}
				else
				{
					System.out.println("Error");
				}
			}
			else if(args.length == 1)
			{
				if(args[0].equals("exit"))
				{
					command = new Exit();
					command.execute();
				}
				else if(args[0].equals("help"))
				{
					command = new Help();
					command.execute();
				}
				else
				{
					System.out.println("Error");
				}
			}
			else
			{
				System.out.println("Error");
			}
		}
	}

}

 class Help implements Command
{
	@Override
	public void execute()
	{
		System.out.println("Help executed");
	}
}

 class Echo implements Command
{
	private String text;
	public Echo(String text)
	{
		this.text = text;
	}
	@Override
	public void execute()
	{
		System.out.println(text);
	}
}

 class Now implements Command
{
	@Override
	public void execute()
	{
		System.out.println(System.currentTimeMillis());
	}
}

 class Exit implements Command
{
	@Override
	public void execute()
	{
		System.out.println("Goodbye!");
	}
}
