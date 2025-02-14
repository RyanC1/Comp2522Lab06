package ca.bcit.comp2522.lab6;

/**
 * Represents a novel with a title, author, and year published.
 *
 * @author Ruan Chu
 * @author Justin Cardas
 * @version 2025
 */
public class Novel
        implements Comparable<Novel>
{
    static final int CURRENT_YEAR = 2025;

    private final String title;
    private final String authorName;
    private final int    yearPublished;

    /**
     * Constructs a novel.
     *
     * @param title         the title of the book
     * @param authorName    the name of the author
     * @param yearPublished the year the book was published (not in the future)
     */
    Novel(final String title,
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
     * Turns a title caught by .isBlank() into a null value
     * @param title the title to check
     * @return the original or null title
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
     * Gets the title of the book.
     *
     * @return the title of the book as a String.
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
     * Gets the year the book was published.
     *
     * @return the year the book was published as an integer.
     */
    public int getYearPublished()
    {
        return yearPublished;
    }

    /**
     * Compares the given Novel title to the calling Novel
     * title based off of the String class's implementation of comparable.
     * Books with null titles are always considered lower.
     *
     * @param givenNovel the Novel to be compared to.
     * @return
     */
    @Override
    public int compareTo(final Novel givenNovel)
    {
        if (givenNovel == null)
        {
            throw new IllegalArgumentException("Given Novel is null");
        }

        String givenTitle;
        givenTitle = givenNovel.getTitle();

        if(title == null && givenTitle == null)
        {
            return 0;
        }
        else if(title == null && givenTitle != null)
        {
            return -1;
        }
        else if(title != null && givenTitle == null)
        {
            return 1;
        }
        else
        {
            return title.compareTo(givenTitle);
        }
    }

    /**
     * Returns a string representation of a novel, with its title, author, and publishing year.
     * @return a string representation of the Novel
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
