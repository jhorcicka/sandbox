package csvtopot;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * This program reads data (translations) from csv file. Then it goes through 
 * all pot files. Each module is in a separated directory. Provided translations  
 * are replaced in all files. 
 * @author j.horcicka
 */
public class Main {
	private static final String TOKEN_ID_REGEXP = "msgid.*";
	private static final String TOKEN_VALUE_REGEXP = "msgstr.*";
	private static final String CSV_SEPARATOR = ";";
    private static final int MSG_ID_LENGTH = 7; // start of the msg, msgid "abcd"

	private JFileChooser fileChooser = null; 
	private String directoryPath = "";
	private String sourcePath = "";
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
			selectFiles();
			loadTokens();
			updateAllModules();
			alert("Hotovo");
		}
		catch (Exception e) {
			e.printStackTrace();
			String message = e.getMessage();
			
			if (message != null) {
				alert("CHYBA: " + message);
			}
		}
	}
	
	private void selectFiles() {
		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Vyber .pot");
		int status = fileChooser.showOpenDialog(null);
		
		if (status == JFileChooser.APPROVE_OPTION) {
			directoryPath = fileChooser.getSelectedFile().getParentFile().getPath();
		}

		fileChooser.setDialogTitle("Vyber zdrojovy .csv");
		status = fileChooser.showOpenDialog(null);

		if (status == JFileChooser.APPROVE_OPTION) {
			sourcePath = fileChooser.getSelectedFile().getAbsolutePath();
		}
	}

	private void loadTokens() throws IOException {
		File file = new File(sourcePath);
        Path path = Paths.get(file.getAbsolutePath());
		List<String> fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
				
		for (int i = 0; i < fileLines.size(); i++) {
			String[] parts = fileLines.get(i).trim().split(CSV_SEPARATOR);
			
			if (parts.length >= 2) {
				String id = trim(parts[0]);
				String value = trim(parts[1]);
				addToken(id, value);
			}
		}
	}

    private String trim(String text) {
        text = text.trim()
                .replaceAll("^\t", "")
                .replaceAll("\t$", "")
                .replaceAll("^\n", "")
                .replaceAll("\n$", "")
        ;

        return text;
    }

	private void addToken(String id, String value) {
		if (tokens.containsKey(id)) {
			String existingValue = tokens.get(id);

			if (existingValue.length() > 0) {
				return;
			}
		}

		tokens.put(id, value);
	}

    private void updateAllModules() throws IOException {
        File directory = new File(directoryPath).getParentFile();
		File[] files = directory.listFiles();

		for (File moduleDirectory : files) {
			updateAllFiles(moduleDirectory.getAbsolutePath());
		}
    }

	private void updateAllFiles(String moduleDirectoryPath) throws IOException {
		File directory = new File(moduleDirectoryPath);
		File[] files = directory.listFiles();

		for (File potFile : files) {
			Path path = Paths.get(potFile.getAbsolutePath());
			updateFile(path);
		}
	}

	private void updateFile(Path path) throws IOException {
		List<String> fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
		File targetFile = new File(path.toString());
		PrintWriter writer = new PrintWriter(targetFile);
			
		for (int i = 0; i < fileLines.size(); i++) {
			String currentLine = fileLines.get(i);
			String followingLine = (fileLines.size() > (i + 1)) ? fileLines.get(i + 1) : "";

			if (currentLine.matches(TOKEN_ID_REGEXP) && followingLine.matches(TOKEN_VALUE_REGEXP)) {
                String originalText = "";

                if (currentLine.length() > Main.MSG_ID_LENGTH) {
                    originalText = currentLine.substring(MSG_ID_LENGTH, currentLine.length() - 1);
                }
				
				if (tokens.containsKey(originalText)) {
					String translatedText = tokens.get(originalText);
					followingLine = String.format("msgstr \"%s\"", translatedText);
				}

				writer.println(escape(currentLine));
				writer.println(escape(followingLine));
				i++;
			}
			else {
				writer.println(currentLine);
			}
		}	
		
		writer.close();
	}

    private String escape(String text) {
        if (text.charAt(0) == '"' && text.charAt(text.length() - 1) == '"') {
            text = text.substring(1, text.length() - 1);
        }

        String[] keywords = { "msgid", "msgstr" };

        for (String prefix : keywords) {
            if (text.startsWith(prefix)) {
                String valueWithoutEdgeQuotes = text.substring(prefix.length() + 2, text.length() - 1);

                if (valueWithoutEdgeQuotes.contains("\"")) {
                    String escapedText = valueWithoutEdgeQuotes.replaceAll("\"{1,}", "\\\\\\\"");
                    text = prefix + " \"" + escapedText + "\"";
                }

                return text;
            }
        }

        return text;
    }
}
