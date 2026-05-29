package ibm.javatraining.com.day7;

import java.io.*;
import org.junit.jupiter.api.*;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.channels.FileChannel;
import ibm.javatraining.com.day7.LogAnalySerDennis;
import static org.junit.jupiter.api.Assertions.*;


class LogAnalySerDennisTest {

	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private LogAnalySerDennis la = new LogAnalySerDennis();
	
	@BeforeEach
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outputStream));
		Files.deleteIfExists(Path.of("src/main/resources/summarySirDennis.txt"));
	}
	
	@AfterEach
	public void restoreSystemOut() throws Exception {
		System.setOut(originalOut);
		//Files.deleteIfExists(Path.of("src/main/resources/summarySirDennis.txt"));
	}
	
	
	//should_ReturnMissingTimeStampBracket_WhenNoBracket
	@Test
	void exec001() throws Exception {
		String filename = "src/test/resources/exec001/server_001.log";
		String expectedFile = java.nio.file.Files.readString(Path.of("src/test/resources/exec001/summary_001.txt"));
		
		LogAnalySerDennis.main(new String[] {filename});
		
		String expectedOutput = 
				"Skipping malformed line: [2024-05-10 09:00:00 INFO: Server started successfully" + System.lineSeparator() +
				"Analysis complete. Summary written to summary.txt" + System.lineSeparator();
		
		String actualFile = java.nio.file.Files.readString(Path.of("src/main/resources/summarySirDennis.txt"));
	
		assertEquals(expectedFile.replace("\r\n", "\n").trim(), actualFile.replace("\r\n", "\n").trim());
		assertEquals(expectedOutput, outputStream.toString());
	}
	
	//should_ReturnMissingMessage_WhenNoMessage
	@Test
	void exec002() throws Exception {
		String filename = "src/test/resources/exec002/server_002.log";
		String expectedFile = java.nio.file.Files.readString(Path.of("src/test/resources/exec002/summary_002.txt"));
		
		LogAnalySerDennis.main(new String[] {filename});
		
		String expectedOutput = 
				"Skipping malformed line: [2024-05-10 09:00:00] INFO" + System.lineSeparator() +
				"Analysis complete. Summary written to summary.txt" + System.lineSeparator();
	
		String actualFile = java.nio.file.Files.readString(Path.of("src/main/resources/summarySirDennis.txt"));
		
		assertEquals(expectedFile.replace("\r\n", "\n").trim(), actualFile.replace("\r\n", "\n").trim());
		assertEquals(expectedOutput, outputStream.toString());
	}
	
	// should_ReturnInvalidLogLevel_WhenNoLogLevel
	@Test
	void exec003() throws Exception {
		String filename = "src/test/resources/exec003/server_003.log";
		String expectedFile = java.nio.file.Files.readString(Path.of("src/test/resources/exec003/summary_003.txt"));
		
		LogAnalySerDennis.main(new String[] {filename});
		
		String expectedOutput = 
				"Skipping malformed line: [2024-05-10 09:00:09] INFORMATION: Listening on port 8080" + System.lineSeparator() +
				"Analysis complete. Summary written to summary.txt" + System.lineSeparator();
	
		String actualFile = java.nio.file.Files.readString(Path.of("src/main/resources/summarySirDennis.txt"));
		
		assertEquals(expectedFile.replace("\r\n", "\n").trim(), actualFile.replace("\r\n", "\n").trim());
		assertEquals(expectedOutput, outputStream.toString());
	}

	// should_PrintFileNotFound_When_FileDoesNotExist
	@Test
	void exec004() throws Exception {
		Path filename = Path.of("src/test/resources/exec004/server_004.log");
		
		FileChannel channel = FileChannel.open(filename, StandardOpenOption.WRITE);
		FileLock lock = channel.lock();
		
		try {
			LogAnalySerDennis.main(new String[] { filename.toString() });
		} finally {
			lock.release();
			channel.close();
		}
	}
	
	
	
	// should_PrintCorrectSummaryReport_When_LogFileIsValid
	@Test
	void exec005() throws IOException {
		String filename = "src/test/resources/exec005/server_005.log";
		String expectedFile = java.nio.file.Files.readString(Path.of("src/test/resources/exec005/summary_005.txt"));
		
		LogAnalySerDennis.main(new String[] {filename});
		String expectedOutput = "Analysis complete. Summary written to summary.txt" + System.lineSeparator();
		
		String actualFile = java.nio.file.Files.readString(Path.of("src/main/resources/summarySirDennis.txt"));
		
		assertEquals(expectedFile.replace("\r\n", "\n").trim(), actualFile.replace("\r\n", "\n").trim());
		assertEquals(expectedOutput, outputStream.toString());
	}
	
	@Test
	void exec006() throws Exception {
		String filename = "src/test/resources/exec006/server_006.log";
		
		LogAnalySerDennis.main(new String[] {filename});
		
		String expectedOutput = "Log file not found." + System.lineSeparator();
		
		Path actualSummaryPath = Path.of("src/main/resources/summarySirDennis.txt");
		
		
		assertEquals(expectedOutput, outputStream.toString());
		assertFalse(Files.exists(actualSummaryPath));
	}
	
	
	@Test
	void exec007() throws Exception {
		String filename = "src/test/resources/exec007/server_007.log";
		
		Path summaryPath = Path.of("src/main/resources/summarySirDennis.txt");
				
		if (!Files.exists(summaryPath)) {
			Files.createFile(summaryPath);
		}
		
		FileChannel channel = FileChannel.open(summaryPath, StandardOpenOption.WRITE);
		FileLock lock = channel.lock();
		
		try {
			
			LogAnalySerDennis.main(new String[] {filename});
			
			String expectedOutput = 
					"Error writing summary file." + System.lineSeparator() +
					"Analysis complete. Summary written to summary.txt" + System.lineSeparator();
			
			assertEquals(expectedOutput, outputStream.toString());
			
		} finally {
			
			if (lock != null) {
				lock.release();
			}
			if (channel != null) {
				channel.close();
			}
		}
	}
	

}
