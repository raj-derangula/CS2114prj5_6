package prj5;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import student.IOHelper;

/**
 * Reads influencer input files and stores the valid data.
 * 
 * @authors Ahnaf Hasan, Joshua Cruz, Arhan Sethi, Raj Derangula
 * @version 4/28/26
 */
public class InputFileReader
{
    private SinglyLinkedList<InfluencerData> data;

    /**
     * Creates a new InputFileReader.
     * 
     * @param fileName
     *            the input file name
     */
    public InputFileReader(String fileName)
    {
        data = new SinglyLinkedList<InfluencerData>();
        readFile(fileName);
    }


    /**
     * Reads the input file.
     * 
     * @param fileName
     *            the input file name
     */
    private void readFile(String fileName)
    {
        Scanner inStream = IOHelper.createScanner(fileName);
        inStream.nextLine();

        while (inStream.hasNextLine())
        {
            String line = inStream.nextLine().replaceAll(" ", "");
            String[] values = line.split(",");

            if (values.length == 10 && isFirstQuarter(values[0]))
            {
                InfluencerData info = new InfluencerData(
                    values[0],
                    values[1],
                    values[2],
                    values[3],
                    values[4],
                    toInt(values[5]),
                    toInt(values[6]),
                    toInt(values[7]),
                    toInt(values[8]),
                    toInt(values[9]));

                data.add(info);
            }
        }
    }


    /**
     * Gets the stored data.
     * 
     * @return the data list
     */
    public SinglyLinkedList<InfluencerData> getData()
    {
        return data;
    }


    /**
     * Prints the console output.
     */
    public void printConsoleOutput()
    {
        SinglyLinkedList<ChannelData> totals = getQuarterTotals();
        totals.insertionSort(new ChannelNameComparator());

        printTraditional(totals);

        System.out.println("**********");
        System.out.println("**********");

        totals.insertionSort(new ReachComparator());
        printReach(totals);
    }


    /**
     * Gets the first quarter totals by channel.
     * 
     * @return the channel totals
     */
    private SinglyLinkedList<ChannelData> getQuarterTotals()
    {
        SinglyLinkedList<ChannelData> totals =
            new SinglyLinkedList<ChannelData>();

        for (int i = 0; i < data.size(); i++)
        {
            InfluencerData current = data.get(i);
            ChannelData channel = findChannel(totals, current.getChannelName());

            if (channel == null)
            {
                channel = new ChannelData(current.getChannelName());
                totals.add(channel);
            }

            channel.addData(current);
        }

        return totals;
    }


    /**
     * Finds a channel in the list.
     * 
     * @param totals
     *            the totals list
     * @param channelName
     *            the channel name
     * @return the matching channel, or null
     */
    private
        ChannelData
        findChannel(SinglyLinkedList<ChannelData> totals, String channelName)
    {
        for (int i = 0; i < totals.size(); i++)
        {
            if (totals.get(i).getChannelName().equals(channelName))
            {
                return totals.get(i);
            }
        }

        return null;
    }


    /**
     * Prints traditional engagement output.
     * 
     * @param totals
     *            the channel totals
     */
    private void printTraditional(SinglyLinkedList<ChannelData> totals)
    {
        DecimalFormat formatter = new DecimalFormat("#.#");

        for (int i = 0; i < totals.size(); i++)
        {
            ChannelData current = totals.get(i);

            System.out.println(current.getChannelName());
            System.out.println(
                "traditional: "
                    + formatter.format(current.getTraditionalRate()));
            System.out.println("==========");
        }
    }


    /**
     * Prints reach engagement output.
     * 
     * @param totals
     *            the channel totals
     */
    private void printReach(SinglyLinkedList<ChannelData> totals)
    {
        DecimalFormat formatter = new DecimalFormat("#.#");

        for (int i = 0; i < totals.size(); i++)
        {
            ChannelData current = totals.get(i);

            System.out.println(current.getChannelName());

            if (current.getReachRate() == -1)
            {
                System.out.println("reach: N/A");
            }
            else
            {
                System.out.println(
                    "reach: " + formatter.format(current.getReachRate()));
            }

            System.out.println("==========");
        }
    }


    /**
     * Checks if the month is in the first quarter.
     * 
     * @param month
     *            the month
     * @return true if the month is January, February, or March
     */
    private boolean isFirstQuarter(String month)
    {
        return month.equals("January") || month.equals("February")
            || month.equals("March");
    }


    /**
     * Converts a string to an integer.
     * 
     * @param str
     *            the string value
     * @return the integer value, or 0
     */
    private int toInt(String str)
    {
        try
        {
            return Integer.parseInt(str);
        }
        catch (Exception exception)
        {
            return 0;
        }
    }
}
