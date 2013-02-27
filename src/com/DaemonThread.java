package com;

import java.util.Date;
import java.util.Iterator;

public class DaemonThread extends Thread{
	
	public void run()
	{
		while(true)
		{
			System.out.println("Running");
			try
			{
				Iterator<String> i=Server.sessionMap.keySet().iterator();
				Date currentTimeStamp = new Date();
				String key="";
				SessionDetails sd; 
				while(i.hasNext())
				{
					key=i.next();
					sd = Server.sessionMap.get(key);
					if(sd.isExpired(currentTimeStamp))
					{
						System.out.println(key+"Expired");
						Server.sessionMap.remove(key);
					}
				}
				Thread.sleep(120000);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
