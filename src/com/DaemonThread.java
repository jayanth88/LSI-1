package com;

import java.util.Date;
import java.util.Iterator;

/* 
 * This is a thread that runs in the background after a certain interval
 * and cleans up Server.sessionMap by deleting the timed-out entries.
 */
public class DaemonThread extends Thread
{	
	final int gcThreadSleepTime =  80000;
	public void run()
	{
		while(true)
		{
			System.out.println("GC Thread running...");
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
						Server.sessionMap.remove(key);
						System.out.println("Cleared data with key: "+key);
					}
				}
				Thread.sleep(gcThreadSleepTime);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
