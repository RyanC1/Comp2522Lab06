package ca.bcit.comp2522.lab6;

/**
 * Represents a Comic Book with a title, author, and year published.
 *
 * @author Ruan Chu
 * @author Andrew Hwang
 * @author Mohammad Sadeghi
 * @version 2025
 */
class ComicBook extends Literature
{
    /**
     * Constructs a Comic Book.
     *
     * @param title         the title of the book
     * @param authorName    the name of the author
     * @param yearPublished the year the book was published (not in the future)
     */
    ComicBook(final String title,
          final String authorName,
          final int yearPublished)
    {
        super(title, authorName, yearPublished);
    }
}
