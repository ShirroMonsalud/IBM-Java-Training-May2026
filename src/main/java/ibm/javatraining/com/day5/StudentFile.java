package ibm.javatraining.com.day5;
import java.io.*;


public class StudentFile {

	public static void main(String[] args) {
		try 
			(BufferedReader br = new BufferedReader(new FileReader("student.csv"));
			BufferedWriter wr = new BufferedWriter(new FileWriter("student.json")))
		{
			String line;
			br.readLine();
			wr.write("[");
			wr.newLine();

			boolean isFirst = true;
			while ((line = br.readLine()) != null)
			{
				if (!isFirst) {
					wr.write(",");
					wr.newLine();
				}
				
				
				
				String[] words = line.replace("\"", "").split(",");
				wr.write("  {");
				wr.newLine();
				wr.write("    \"id\": \"" + words[0] + "\",\n");
				wr.write("    \"name\": \"" +  words[1] + "\",\n");
				wr.write("    \"course\": \"" +  words[2] + "\"\n");
				wr.write("  }");
				isFirst = false;
			}
			
			
			wr.newLine();
			wr.write("]");
			
		}
		
		catch (IOException e)
		{
			System.out.println("Error reading file: " + e.getMessage());
		}
		

	}

}
