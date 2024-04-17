import java.util.*;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;


public class Main {
    public static void main(String[] args)
    {
        //declaring variables
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        Scanner in;

        try
        {
            //toolkit to get working directory of IDE
            File workingDirectory = new File(System.getProperty("user.dir"));

            //to allow user to pick a file
            chooser.setCurrentDirectory(workingDirectory);

            //to make sure the user doesn't close without picking a file
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                in = new Scanner(selectedFile);

                /*wrapping the BufferedWriter around BufferedOutputStream because of class hierarchy

                InputStream stream = new BufferedInputStream(Files.newInputStream(file, CREATE));

                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                 */

                //now read the file
                int line = 0;
                int characters = 0;
                int wordCount = 0;
                while (in.hasNextLine()) {
                    rec = in.nextLine();
                    line++;
                    characters += rec.length();
                    String words [] = rec.split(" ");
                    wordCount += words.length;

                    //print to screen summary report
                    System.out.printf("\nLine %4d %-60s ", line, rec);
                }

                //Print to screen
                System.out.println(line + " lines " + characters + " characters " + wordCount + " words ");
                in.close(); // closing file to seal it and flush buffer
                System.out.println("\n\nData file read!");
            } else //user closed the chooser without selecting a file
            {
                System.out.println("No file selected! ... exiting. \nRun the program again and select a file.");
            }
        }
        catch (FileNotFoundException e)
            {
                System.out.println("File not found!");
                e.printStackTrace();
            }
        catch (IOException e)
            {
                e.printStackTrace();
            }
    }
}