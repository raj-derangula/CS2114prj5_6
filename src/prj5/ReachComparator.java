package prj5;

import java.util.Comparator;

/**
 * Compares channels by reach engagement rate (descending).
 * 
 * @authors Ahnaf Hasan, Joshua Cruz, Arhan Sethi, Raj Derangula
 * @version 4/24/26
 */
public class ReachComparator
    implements Comparator<ChannelData>
{

    /**
     * Compares two ChannelData objects by reach rate.
     * 
     * @param first
     *            the first channel
     * @param second
     *            the second channel
     * @return negative, zero, or positive value
     */
    public int compare(ChannelData first, ChannelData second)
    {
        if (first.getReachRate() > second.getReachRate())
        {
            return -1;
        }
        else if (first.getReachRate() < second.getReachRate())
        {
            return 1;
        }

        return 0;
    }
}
