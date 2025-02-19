package ca.bcit.comp2522.lab6;

/**
 * Defines a piece of literature be defined by a title.
 *
 * @author Ryan Chu
 * @author
 * @author
 * @version
 */
abstract class Literature
        implements Comparable<Literature>
{
    static final int CURRENT_YEAR = 2025;
    static final int EQUAL_CASE = 0;
    static final int LESSER_CASE = -1;
    static final int GREATER_CASE = 1;

    private final String title;
    private final String authorName;
    private final int    yearPublished;

    /**
     * Constructs a piece of literature.
     *
     * @param title         the title of the book
     * @param authorName    the name of the author
     * @param yearPublished the year the book was published (not in the future)
     */
    Literature(final String title,
               final String authorName,
               final int yearPublished)
    {
        validateYearPublished(yearPublished);

        this.title      = fixBlankTitle(title);
        this.authorName = fixBlankAuthorName(authorName);
        ;
        this.yearPublished = yearPublished;
    }

    /*
     * Turns a book title name caught by .isBlank() into a null value
     * @param author name the author name to check
     * @return the original or null author name
     */
    private static String fixBlankTitle(final String title)
    {
        if(title != null && title.isBlank())
        {
            return null;
        }
        return title;
    }

    /*
     * Turns an author name caught by .isBlank() into a null value
     * @param author name the author name to check
     * @return the original or null author name
     */
    private static String fixBlankAuthorName(final String authorName)
    {
        if(authorName != null && authorName.isBlank())
        {
            return null;
        }
        return authorName;
    }

    /*
     * Validates the year isn't past CURRENT_YEAR
     * @param yearPublished the year to check
     */
    private static void validateYearPublished(final int yearPublished)
    {
        if(yearPublished > CURRENT_YEAR)
        {
            throw new IllegalArgumentException("Year given is past " + CURRENT_YEAR);
        }
    }

    /**
     * Gets the title of the literature.
     *
     * @return the title of the literature as a String.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Gets the author's name.
     *
     * @return the author's name as a String.
     */
    public String getAuthorName()
    {
        return authorName;
    }

    /**
     * Gets the year the literature was published.
     *
     * @return the year the literature was published as an integer.
     */
    public int getYearPublished()
    {
        return yearPublished;
    }

    /**
     * Compares the given literature title to the calling literature
     * title based off of the String class's implementation of comparable.
     * Books with null titles are always considered lower.
     *
     * @param givenItem the Novel to be compared to.
     * @return EQUAL_CASE if both titles are null,
     *         LESSER_CASE if the calling title is null,
     *         GREATER_CASE if the given title is null,
     *         or String.compareTo() result of the two titles
     */
    @Override
    public int compareTo(final Literature givenItem)
    {
        if (givenItem == null)
        {
            throw new IllegalArgumentException("Given Item is null");
        }

        final String givenTitle;
        givenTitle = givenItem.getTitle();

        if(title == null && givenTitle == null)
        {
            return EQUAL_CASE;
        }
        else if(title == null && givenTitle != null)
        {
            return LESSER_CASE;
        }
        else if(title != null && givenTitle == null)
        {
            return GREATER_CASE;
        }
        else
        {
            return title.compareTo(givenTitle);
        }
    }

    /**
     * Returns a string representation of the literature, with its title, author, and publishing year.
     * @return a string representation of the literature
     */
    @Override
    public String toString()
    {
        StringBuilder details;
        details = new StringBuilder();

        details.append("\"");
        details.append(title);
        details.append("\"");
        details.append(" by ");
        details.append(authorName);
        details.append(" (");
        details.append(yearPublished);
        details.append(")");

        return details.toString();
    }
}
