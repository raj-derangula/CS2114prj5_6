package prj5;

/**
 * Stores first quarter totals for one channel.
 * 
 * @authors Ahnaf Hasan, Joshua Cruz, Arhan Sethi, Raj Derangula
 * @version 4/24/26
 */
public class ChannelData
{
    private String channelName;
    private int likes;
    private int comments;
    private int views;
    private int marchFollowers;

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
        marchFollowers = 0;
    }


    /**
     * Adds data from one month.
     * 
     * @param data
     *            the influencer data
     */
    public void addData(InfluencerData data)
    {
        likes += data.getLikes();
        comments += data.getComments();
        views += data.getViews();

        if (data.getMonth().equals("March"))
        {
            marchFollowers = data.getFollowers();
        }
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
     * Gets the traditional engagement rate.
     * 
     * @return traditional engagement rate
     */
    public double getTraditionalRate()
    {
        if (marchFollowers == 0)
        {
            return 0;
        }

        return (comments + likes) * 100.0 / marchFollowers;
    }


    /**
     * Gets the reach engagement rate.
     * 
     * @return reach engagement rate or -1 if N/A
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
