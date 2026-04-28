package prj5;

import java.io.IOException;

/**
 * Runs Project 5.
 * 
 * @authors Ahnaf Hasan, Joshua Cruz, Arhan Sethi, Raj Derangula
 * @version 4/24/26
 */
public class ProjectRunner
{

    /**
     * Main method for the project.
     * 
     * @param args
     *            command line arguments
     * @throws IOException
     *             if the file cannot be read
     */
    public static void main(String[] args)
        throws IOException
    {
        boolean showConsole = true;
        boolean showGUI = false;

        InputFileReader filer;

        if (args.length > 0)
        {
            filer = new InputFileReader(args[0]);
        }
        else
        {
            filer = new InputFileReader("SampleInput1_2023.csv");
        }

        if (showConsole)
        {
            filer.printConsoleOutput();
        }

        if (showGUI)
        {
            // GUI for final submission
        }
    }
}
