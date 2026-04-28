package prj5;

/**
 * Stores information about an influencer.
 * 
 * @authors Ahnaf Hasan, Joshua Cruz, Arhan Sethi, Raj Derangula
 * @version 4/24/26
 */
public class InfluencerData
{
    private String username;
    private String channelName;
    private String country;
    private String mainTopic;
    private String month;
    private int likes;
    private int posts;
    private int followers;
    private int comments;
    private int views;

    /**
     * Creates a new InfluencerData object.
     * 
     * @param month
     *            the month of the data
     * @param username
     *            the username of the influencer
     * @param channelName
     *            the channel name
     * @param country
     *            the country of the influencer
     * @param mainTopic
     *            the main topic of the channel
     * @param likes
     *            the number of likes
     * @param posts
     *            the number of posts
     * @param followers
     *            the number of followers
     * @param comments
     *            the number of comments
     * @param views
     *            the number of views
     */
    public InfluencerData(
        String month,
        String username,
        String channelName,
        String country,
        String mainTopic,
        int likes,
        int posts,
        int followers,
        int comments,
        int views)
    {
        this.month = month;
        this.username = username;
        this.channelName = channelName;
        this.country = country;
        this.mainTopic = mainTopic;
        this.likes = likes;
        this.posts = posts;
        this.followers = followers;
        this.comments = comments;
        this.views = views;
    }


    /**
     * Gets the channel name.
     * 
     * @return channel name
     */
    public String getChannelName()
    {
        return channelName;
    }


    /**
     * Gets the month.
     * 
     * @return month
     */
    public String getMonth()
    {
        return month;
    }


    /**
     * Gets likes.
     * 
     * @return likes
     */
    public int getLikes()
    {
        return likes;
    }


    /**
     * Gets comments.
     * 
     * @return comments
     */
    public int getComments()
    {
        return comments;
    }


    /**
     * Gets views.
     * 
     * @return views
     */
    public int getViews()
    {
        return views;
    }


    /**
     * Gets followers.
     * 
     * @return followers
     */
    public int getFollowers()
    {
        return followers;
    }


    /**
     * Gets traditional engagement.
     * 
     * @return traditional engagement rate
     */
    public double getTraditionalEngagement()
    {
        if (followers == 0)
        {
            return 0;
        }
        return (likes + comments) * 100.0 / followers;
    }


    /**
     * Gets reach engagement.
     * 
     * @return reach engagement rate
     */
    public double getReachEngagement()
    {
        if (views == 0)
        {
            return -1;
        }
        return (likes + comments) / views * 100.0;
    }
}
