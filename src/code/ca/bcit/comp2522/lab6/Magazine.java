package ca.bcit.comp2522.lab6;

/**
 * Represents a Magazine with a title, author, and year published.
 *
 * @author Ruan Chu
 * @author Andrew Hwang //TODO
 * @author
 * @version 2025
 */
class Magazine extends Literature
{
    /**
     * Constructs a Magazine.
     *
     * @param title         the title of the book
     * @param authorName    the name of the author
     * @param yearPublished the year the book was published (not in the future)
     */
    Magazine(final String title,
          final String authorName,
          final int yearPublished)
    {
        super(title, authorName, yearPublished);
    }
}
