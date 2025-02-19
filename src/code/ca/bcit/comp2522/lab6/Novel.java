package ca.bcit.comp2522.lab6;

/**
 * Represents a novel with a title, author, and year published.
 *
 * @author Ruan Chu
 * @author Justin Cardas
 * @version 2025
 */
class Novel
        extends Literature
{
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
        super(title, authorName, yearPublished);
    }

}
