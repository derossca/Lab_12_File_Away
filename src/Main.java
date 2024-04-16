import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;


public class Main {
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";


        try
        {
            //toolkit to get working directory of IDE
            File workingDirectory = new File(System.getProperty("user.dir"));

            //to allow user to pick a file
            chooser.setCurrentDirectory(workingDirectory);

            //to make sure the user doesn't close without picking a file
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                //wrapping the Buffered writer around BufferedOutputStream
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                //now read the file
                int line = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;

                    //print to screen
                    System.out.printf("\nLine %4d %-60s ", line, rec);
                }
                reader.close(); // closing file to seal it and flush buffer
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