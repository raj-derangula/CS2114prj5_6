package prj5;

import java.util.Comparator;

/**
 * Compares channels by traditional engagement rate (descending).
 * 
 * @authors Ahnaf Hasan, Joshua Cruz, Arhan Sethi, Raj Derangula
 * @version 4/24/26
 */
public class TraditionalComparator
    implements Comparator<ChannelData>
{

    /**
     * Compares two ChannelData objects by traditional rate.
     * 
     * @param first
     *            the first channel
     * @param second
     *            the second channel
     * @return negative, zero, or positive value
     */
    public int compare(ChannelData first, ChannelData second)
    {
        if (first.getTraditionalRate() > second.getTraditionalRate())
        {
            return -1;
        }
        else if (first.getTraditionalRate() < second.getTraditionalRate())
        {
            return 1;
        }

        return 0;
    }
}
