// Project 5 Spring 2026
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// -- Arhan Sethi (906784733)


package prj5;

import cs2.Button;
import cs2.Shape;
import cs2.TextShape;
import cs2.Window;
import cs2.WindowSide;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * GUI front-end for the Social Media Visualization project.
 * Displays a bar chart of influencer engagement rates with
 * buttons to control time period, sort order, and formula.
 *
 * @authors Ahnaf Hasan, Joshua Cruz, Arhan Sethi, Raj Derangula
 * @version 5/1/26
 */
public class GUIVisualization
{
    // ---------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------
    private static final int WINDOW_WIDTH = 1100;
    private static final int WINDOW_HEIGHT = 600;

    // The graph area sits inside the window content pane.
    // We use a narrower effective width to stay away from the
    // east-side buttons panel (~180px) and leave left margin.
    private static final int GRAPH_LEFT = 60;
    private static final int GRAPH_TOP = 70;
    private static final int GRAPH_HEIGHT = 360;

    private static final int BAR_WIDTH = 80;

    private static final DecimalFormat FORMATTER = new DecimalFormat("#.#");

    // Bar colors for each channel (up to 4)
    private static final Color[] BAR_COLORS = {
        new Color(70, 130, 180),   // steel blue
        new Color(220, 120, 60),   // burnt orange
        new Color(80, 160, 100),   // muted green
        new Color(180, 80, 120)    // raspberry
    };

    // ---------------------------------------------------------------
    // State
    // ---------------------------------------------------------------
    private Window window;
    private SinglyLinkedList<InfluencerData> allData;

    // Current selections (persist until changed)
    private String currentMonth;      // "January", "February", "March", "First Quarter"
    private String currentSort;       // "Channel Name", "Engagement Rate"
    private String currentFormula;    // "Traditional", "Reach"

    // Shapes currently drawn so we can clear them
    private ArrayList<Shape> drawnBars;
    private ArrayList<TextShape> drawnText;

    // ---------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------

    /**
     * Creates a new GUIVisualization.
     *
     * @param data the influencer data list read from the input file
     */
    public GUIVisualization(SinglyLinkedList<InfluencerData> data)
    {
        this.allData = data;
        this.currentMonth = "January";
        this.currentSort = "Channel Name";
        this.currentFormula = "Traditional";
        drawnBars = new ArrayList<Shape>();
        drawnText = new ArrayList<TextShape>();

        buildWindow();
        updateChart();
    }


    // ---------------------------------------------------------------
    // Window / Button setup
    // ---------------------------------------------------------------

    /**
     * Builds the window and adds all buttons to their sides.
     */
    private void buildWindow()
    {
        window = new Window(
            "arhans ahnaf_hasan joshuacruz rajderangula - Social Media Viz");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // --- Time period buttons (left side) ---
        Button janBtn = new Button("January");
        Button febBtn = new Button("February");
        Button marBtn = new Button("March");
        Button q1Btn  = new Button("First Quarter");

        window.addButton(janBtn, WindowSide.NORTH);
        window.addButton(febBtn, WindowSide.NORTH);
        window.addButton(marBtn, WindowSide.NORTH);
        window.addButton(q1Btn,  WindowSide.NORTH);

        // --- Sort buttons (west side) ---
        Button sortNameBtn = new Button("Sort by Channel Name");
        Button sortRateBtn = new Button("Sort by Engagement Rate");

        window.addButton(sortNameBtn, WindowSide.WEST);
        window.addButton(sortRateBtn, WindowSide.WEST);

        // --- Formula buttons (east side) ---
        Button tradBtn  = new Button("Traditional Engagement Rate");
        Button reachBtn = new Button("Reach Engagement Rate");

        window.addButton(tradBtn,  WindowSide.EAST);
        window.addButton(reachBtn, WindowSide.EAST);

        // --- Wire up listeners ---
        janBtn.onClick(this, "clickJanuary");
        febBtn.onClick(this, "clickFebruary");
        marBtn.onClick(this, "clickMarch");
        q1Btn.onClick(this,  "clickFirstQuarter");

        sortNameBtn.onClick(this, "clickSortName");
        sortRateBtn.onClick(this, "clickSortRate");

        tradBtn.onClick(this,  "clickTraditional");
        reachBtn.onClick(this, "clickReach");
    }


    // ---------------------------------------------------------------
    // Button handlers
    // ---------------------------------------------------------------

    /**
     * Handles the January button click.
     *
     * @param btn the button that was clicked
     */
    public void clickJanuary(Button btn)
    {
        currentMonth = "January";
        updateChart();
    }


    /**
     * Handles the February button click.
     *
     * @param btn the button that was clicked
     */
    public void clickFebruary(Button btn)
    {
        currentMonth = "February";
        updateChart();
    }


    /**
     * Handles the March button click.
     *
     * @param btn the button that was clicked
     */
    public void clickMarch(Button btn)
    {
        currentMonth = "March";
        updateChart();
    }


    /**
     * Handles the First Quarter button click.
     *
     * @param btn the button that was clicked
     */
    public void clickFirstQuarter(Button btn)
    {
        currentMonth = "First Quarter";
        updateChart();
    }


    /**
     * Handles the Sort by Channel Name button click.
     *
     * @param btn the button that was clicked
     */
    public void clickSortName(Button btn)
    {
        currentSort = "Channel Name";
        updateChart();
    }


    /**
     * Handles the Sort by Engagement Rate button click.
     *
     * @param btn the button that was clicked
     */
    public void clickSortRate(Button btn)
    {
        currentSort = "Engagement Rate";
        updateChart();
    }


    /**
     * Handles the Traditional Engagement Rate button click.
     *
     * @param btn the button that was clicked
     */
    public void clickTraditional(Button btn)
    {
        currentFormula = "Traditional";
        updateChart();
    }


    /**
     * Handles the Reach Engagement Rate button click.
     *
     * @param btn the button that was clicked
     */
    public void clickReach(Button btn)
    {
        currentFormula = "Reach";
        updateChart();
    }


    // ---------------------------------------------------------------
    // Chart rendering
    // ---------------------------------------------------------------

    /**
     * Clears and redraws the bar chart based on current selections.
     */
    private void updateChart()
    {
        clearChart();

        SinglyLinkedList<ChannelData> channels = buildChannelData();

        // Sort according to current setting
        if (currentSort.equals("Channel Name"))
        {
            channels.insertionSort(new ChannelNameComparator());
        }
        else
        {
            if (currentFormula.equals("Traditional"))
            {
                channels.insertionSort(new TraditionalComparator());
            }
            else
            {
                channels.insertionSort(new ReachComparator());
            }
        }

        drawBars(channels);
        drawTitle();
    }


    /**
     * Removes all previously drawn shapes from the window.
     */
    private void clearChart()
    {
        for (Shape s : drawnBars)
        {
            window.removeShape(s);
        }
        for (TextShape t : drawnText)
        {
            window.removeShape(t);
        }
        drawnBars.clear();
        drawnText.clear();
    }


    /**
     * Builds a ChannelData list for the currently selected time period.
     *
     * @return list of channel data aggregated for the selected period
     */
    private SinglyLinkedList<ChannelData> buildChannelData()
    {
        SinglyLinkedList<ChannelData> channels =
            new SinglyLinkedList<ChannelData>();

        for (int i = 0; i < allData.size(); i++)
        {
            InfluencerData row = allData.get(i);

            boolean include = currentMonth.equals("First Quarter")
                || row.getMonth().equals(currentMonth);

            if (!include)
            {
                continue;
            }

            ChannelData existing = findChannel(channels,
                row.getChannelName());

            if (existing == null)
            {
                existing = new ChannelData(row.getChannelName());
                channels.add(existing);
            }

            existing.addData(row);
        }

        return channels;
    }


    /**
     * Finds an existing ChannelData in the list by name.
     *
     * @param list        the list to search
     * @param channelName the name to find
     * @return the matching ChannelData, or null if not found
     */
    private ChannelData findChannel(
        SinglyLinkedList<ChannelData> list,
        String channelName)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getChannelName().equals(channelName))
            {
                return list.get(i);
            }
        }
        return null;
    }


    /**
     * Draws bars and labels for each channel.
     *
     * @param channels the sorted channel data list
     */
    private void drawBars(SinglyLinkedList<ChannelData> channels)
    {
        int count = channels.size();
        if (count == 0)
        {
            return;
        }

        // Find max rate to scale bars
        double maxRate = 0;
        for (int i = 0; i < count; i++)
        {
            double rate = getRate(channels.get(i));
            if (rate > maxRate)
            {
                maxRate = rate;
            }
        }
        if (maxRate == 0)
        {
            maxRate = 1; // avoid division by zero in scaling
        }

        int graphBottom = GRAPH_TOP + GRAPH_HEIGHT;

        // Use the actual panel width to center bars
        int panelWidth = window.getGraphPanelWidth();
        final int BAR_SPACING = 160;
        int groupWidth = (count - 1) * BAR_SPACING + BAR_WIDTH;
        int startX = (panelWidth - groupWidth) / 2;

        for (int i = 0; i < count; i++)
        {
            ChannelData channel = channels.get(i);
            double rate = getRate(channel);
            String rateLabel;

            if (rate < 0)
            {
                rateLabel = "N/A";
            }
            else
            {
                rateLabel = FORMATTER.format(rate);
            }

            int barX = startX + i * BAR_SPACING;
            int barHeight = (rate > 0)
                ? (int)(rate / maxRate * (GRAPH_HEIGHT - 40))
                : 0;
            int barY = graphBottom - barHeight;

            Color barColor = BAR_COLORS[i % BAR_COLORS.length];

            // Draw the bar (only if there's a valid positive rate)
            if (rate > 0)
            {
                Shape bar = new Shape(barX, barY, BAR_WIDTH, barHeight,
                    barColor);
                window.addShape(bar);
                drawnBars.add(bar);
            }

            // Channel name label below bar
            TextShape nameLabel = new TextShape(0, 0,
                channel.getChannelName(), Color.BLACK);
            int nameLabelX = barX + BAR_WIDTH / 2
                - nameLabel.getWidth() / 2;
            nameLabel.setX(nameLabelX);
            nameLabel.setY(graphBottom + 8);
            window.addShape(nameLabel);
            drawnText.add(nameLabel);

            // Engagement rate label below channel name
            TextShape rateText = new TextShape(0, 0, rateLabel, Color.DARK_GRAY);
            int rateLabelX = barX + BAR_WIDTH / 2
                - rateText.getWidth() / 2;
            rateText.setX(rateLabelX);
            rateText.setY(graphBottom + 26);
            window.addShape(rateText);
            drawnText.add(rateText);
        }
    }


    /**
     * Draws a title above the chart showing current settings.
     */
    private void drawTitle()
    {
        String title = currentMonth + "  |  "
            + currentFormula + "  |  Sort: " + currentSort;
        TextShape titleShape = new TextShape(0, 0, title, Color.BLACK);
        int titleX = (window.getGraphPanelWidth() - titleShape.getWidth()) / 2;
        titleShape.setX(titleX);
        titleShape.setY(GRAPH_TOP - 30);
        window.addShape(titleShape);
        drawnText.add(titleShape);
    }


    /**
     * Returns the engagement rate for a channel based on the current formula.
     * Returns -1 if the rate is N/A (divide by zero / no views).
     *
     * @param channel the channel to evaluate
     * @return the rate, or -1 for N/A
     */
    private double getRate(ChannelData channel)
    {
        if (currentFormula.equals("Traditional"))
        {
            return channel.getTraditionalRate();
        }
        else
        {
            return channel.getReachRate();
        }
    }
}