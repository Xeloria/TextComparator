package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Alexander J. Cintrón Báez
 * @email cintronalexj97@gmail.com
 * @version 1.0
 * @since 2018-01-15
 * @class TextComparator: Compares two text files.
 */
public class TextComparator {
	
	private static Scanner inputReader, text1, text2;
	private static String file1, file2;
	private static PrintWriter w1, w2;

	/**
	 * Main method, opens text files and create result files.
	 * @param args Unused
	 */
	public static void main(String[] args) {
		long exeTime = System.currentTimeMillis();
		try {
			inputReader = new Scanner(new File("COMPARE_TEXT\\TEXT_FILE_NAMES.txt"));
			file1 = inputReader.nextLine();
			file2 = inputReader.nextLine();
			inputReader.close();
			if(!file1.contains(".txt"))
				file1 = (file1+".txt");
			if(!file2.contains(".txt"))
				file2 = (file2+".txt");
			text1 = new Scanner(new File("COMPARE_TEXT\\"+file1));
			text2 = new Scanner(new File("COMPARE_TEXT\\"+file2));
			if(file1.equals(file2)) {
				file2 = (file2+"(2)");
			}
			w1 = new PrintWriter(new File("RESULTS\\("+file1+")_RESULT.txt"));
			w2 = new PrintWriter(new File("RESULTS\\("+file2+")_RESULT.txt"));
			compareText();
			text1.close();
			text2.close();
			w1.close();
			w2.close();
			System.out.println("Comparation was successful!");
			System.out.println("Execution time: "+(System.currentTimeMillis()-exeTime)+" ms");
		} catch (FileNotFoundException e) {
			System.out.println("There was an error while opening the text files."
							+ "\nPlease make sure to read the instructions in \"READ_ME.txt\","
							+ "\nwhich should be located inside the project directory: "
							+ "\n\""+System.getProperty("user.dir")+"\"");
		} catch (NoSuchElementException e) {
			System.out.println("Missing file names in \"TEXT_FILE_NAMES.txt\"."
							+ "\nPlease make sure to read the instructions in \"READ_ME.txt\","
							+ "\nwhich should be located inside the project directory: "
							+ "\n\""+System.getProperty("user.dir")+"\"");
		}
	}
	
	/**
	 * compareText method will compare two text files by line
	 * and mark the lines that are different.
	 * @throws FileNotFoundException If result files does not exist.
	 */
	private static void compareText() throws FileNotFoundException {
		String t1, t2, mark1, mark2;
		Boolean equal = true;
		while(text1.hasNextLine() && text2.hasNextLine()) {
			t1 = text1.nextLine();
			t2 = text2.nextLine();
			if(!t1.equals(t2)) {
				mark1 = ("#| "+t1);
				mark2 = ("#| "+t2);	
				equal = false;
			} else {
				mark1 = (" | "+t1);
				mark2 = (" | "+t2);
			}
			w1.println(mark1);
			w2.println(mark2);
		}
		if(text1.hasNextLine()) {
			while(text1.hasNextLine()) {
				w1.println("#| "+text1.nextLine());
				w2.println("#| ");
				equal = false;
			}
		}
		if(text2.hasNextLine()) {
			while(text2.hasNextLine()) {
				w2.println("#| "+text2.nextLine());
				w1.println("#| ");
				equal = false;
			}
		}
		if(equal)
			System.out.println("Both texts are identical!");
	}
}
