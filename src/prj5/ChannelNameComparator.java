package prj5;

import java.util.Comparator;

/**
 * Compares channels by channel name alphabetically (ignores
 * uppercase/lowercase).
 * 
 * @authors Ahnaf Hasan, Joshua Cruz, Arhan Sethi, Raj Derangula
 * @version 4/24/26
 */
public class ChannelNameComparator
    implements Comparator<ChannelData>
{

    /**
     * Compares two ChannelData objects by channel name.
     * 
     * @param first
     *            the first channel
     * @param second
     *            the second channel
     * @return negative, zero, or positive value
     */
    public int compare(ChannelData first, ChannelData second)
    {
        return first.getChannelName()
            .compareToIgnoreCase(second.getChannelName());
    }
}
