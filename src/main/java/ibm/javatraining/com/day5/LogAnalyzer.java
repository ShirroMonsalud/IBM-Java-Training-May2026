package ibm.javatraining.com.day5;
import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class LogAnalyzer {

	public static void main(String[] args) {
		
		Map<String, Integer> logCounter = new HashMap<>();
		List<String> errorMessage = new ArrayList<>();
		logCounter.put("INFO", 0);
		logCounter.put("WARN", 0);
		logCounter.put("ERROR", 0);
		int totalEntry = 0;
		LocalDateTime earliestTime = null;
		LocalDateTime latestTime = null;
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		try
			(BufferedReader br = new BufferedReader(new FileReader("server.log"));
			BufferedWriter wr = new BufferedWriter(new FileWriter("summary.txt")))
		{
			String line;
			while ((line = br.readLine()) != null)
			{
				if (!(line.startsWith("[") && line.contains("]")) || 
					!(line.contains("INFO:") || line.contains("WARN:") || line.contains("ERROR:")))
				{
					throw new MalformedLogEntryException("Invalid Log Format");
						
				}
					
				else 
				{
					System.out.println(line);
					String afterBracket = line.substring(line.indexOf("]") + 2);
					String [] messageFull = afterBracket.split(": ", 2);
					
					String messageLevel = messageFull[0];
					String messageOnly = messageFull[1];
					logCounter.put(messageLevel, logCounter.get(messageLevel) + 1);
					totalEntry++;
					
					if(messageLevel.equals("ERROR"))
					{
						errorMessage.add(messageOnly);
					}
						
					String timeTxt = line.substring(1, line.indexOf("]"));
					LocalDateTime time = LocalDateTime.parse(timeTxt, timeFormat);
					if (earliestTime == null || time.isBefore(earliestTime))
					{
						earliestTime = time;
					}
						
					if (latestTime == null || time.isAfter(latestTime))
					{
						latestTime = time;
					}
				}
			
			}
			
			wr.write("Log Summary Report");
			wr.newLine();
			wr.write("------------------");
			wr.newLine();
			wr.write("Total Entry: " + totalEntry);
			wr.newLine();
			wr.write("INFO: " + logCounter.get("INFO"));
			wr.newLine();
			wr.write("WARN: " + logCounter.get("WARN"));
			wr.newLine();
			wr.write("ERROR: " + logCounter.get("ERROR"));
			wr.newLine();
			wr.newLine();
			wr.write("Error Messages:");
			wr.newLine();
			for (String e : errorMessage)
			{
				wr.write("- " + e);
				wr.newLine();
			}
			wr.newLine();
			wr.write("Earliest Timestamp: " + earliestTime.format(timeFormat));
			wr.newLine();
			wr.write("Latest Timestamp: " + latestTime.format(timeFormat));
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("Error: File not Found");
		}
		
		catch (IOException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		
		catch(MalformedLogEntryException e)
		{
			System.out.println("Malformed Log Entry: " + e.getMessage());
		}
		
	}
	
	static class MalformedLogEntryException extends Exception {
	    public MalformedLogEntryException(String message) {
	        super(message); 
	    }
	    
	}

}



