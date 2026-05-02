// Project 5 Spring 2026
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Arhan Sethi (906784733)

package prj5;

/**
 * Stores aggregated totals for one channel across a time period.
 *
 * @authors Ahnaf Hasan, Joshua Cruz, Arhan Sethi, Raj Derangula
 * @version 5/1/26
 */
public class ChannelData
{
    private String channelName;
    private int likes;
    private int comments;
    private int views;
    private int latestFollowers;

    /**
     * Creates a new ChannelData object.
     *
     * @param channelName
     *            the channel name
     */
    public ChannelData(String channelName)
    {
        this.channelName = channelName;
        likes = 0;
        comments = 0;
        views = 0;
        latestFollowers = 0;
    }


    /**
     * Adds data from one month. Always updates the follower count so that
     * single-month views (January, February) work correctly, not just Q1
     * aggregated views.
     *
     * @param data
     *            the influencer data for one month
     */
    public void addData(InfluencerData data)
    {
        likes += data.getLikes();
        comments += data.getComments();
        views += data.getViews();
        latestFollowers = data.getFollowers();
    }


    /**
     * Gets the channel name.
     *
     * @return the channel name
     */
    public String getChannelName()
    {
        return channelName;
    }


    /**
     * Gets the traditional engagement rate. Uses the follower count from the
     * most recent month of data added.
     *
     * @return traditional engagement rate, or 0 if no followers
     */
    public double getTraditionalRate()
    {
        if (latestFollowers == 0)
        {
            return 0;
        }

        return (comments + likes) * 100.0 / latestFollowers;
    }


    /**
     * Gets the reach engagement rate.
     *
     * @return reach engagement rate, or -1 if views are zero (N/A)
     */
    public double getReachRate()
    {
        if (views == 0)
        {
            return -1;
        }

        return (comments + likes) * 100.0 / views;
    }
}
