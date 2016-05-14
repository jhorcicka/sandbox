package pottocsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Main {
	private static final String FILE_ENDING = ".pot";
	private static final String TOKEN_ID_REGEXP = "msgid.*";
	private static final String TOKEN_VALUE_REGEXP = "msgstr.*";
	private static final String TARGET_FILENAME = "slovnik.csv";
	private static final String CSV_SEPARATOR = "\t";

	private JFileChooser fileChooser = null; 
	private String directoryPath = "";
	private final Map<String, String> tokens = new HashMap<>();
	
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
	
	public static void put(String... texts) {
		for (String parameter : texts) {
			System.out.println(parameter);
		}
	}
	
	private void alert(String... texts) {
		for (String message : texts) {
			JOptionPane.showMessageDialog(null, message);
		}
	}
	
	public void run() {
		try {
			showFileChooser();
			loadTokens();
			writeTokens();
		}
		catch (Exception e) {
			String message = e.getMessage();
			
			if (message != null) {
				alert("CHYBA: " + message);
			}
		}
	}
	
	private void showFileChooser() {
		fileChooser = new JFileChooser();
		int status = fileChooser.showOpenDialog(null);
		
		if (status == JFileChooser.APPROVE_OPTION) {
			directoryPath = fileChooser.getSelectedFile().getParentFile().getPath();
		}
	}
		
	private void loadTokens() throws IOException {
		File directory = new File(directoryPath);
		File[] files = directory.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.toLowerCase().endsWith(FILE_ENDING);
		    }
		});
		

		for (File potFile : files) {
			Path path = Paths.get(potFile.getAbsolutePath());
			List<String> fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
				
			for (int i = 0; i < fileLines.size(); i++) {
				if (fileLines.get(i).matches(TOKEN_ID_REGEXP)) {
					addToken(fileLines.get(i), fileLines.get(i+1));
				}
			}
		}
	}

	private void addToken(String idLine, String valueLine) {
		if (! idLine.matches(TOKEN_ID_REGEXP) || ! valueLine.matches(TOKEN_VALUE_REGEXP)) {
			return;
		}
		
		String id = idLine.substring(7, idLine.length() - 1);
		String value = valueLine.substring(8, valueLine.length() - 1);

		if (id.length() == 0) { 
			return;
		}
		
		if (tokens.containsKey(id)) {
			String existingValue = tokens.get(id);

			if (existingValue.length() > 0) {
				return;
			}
		}
		
		tokens.put(id, value);
	}

	private void writeTokens() throws FileNotFoundException {
		PrintWriter writer;

		File targetFile = new File(TARGET_FILENAME);
		writer = new PrintWriter(targetFile);
		Set<String> allIds = tokens.keySet();
			
		for (String id : allIds) {
			String value = tokens.get(id);
			writer.println(String.format("%s%s%s", id, CSV_SEPARATOR, value));
		}
			
		writer.close();
		alert("Zapsano do: " + targetFile.getAbsolutePath());
	}
}