package ca.bcit.comp2522.lab6;

import java.util.*;

/**
 * Represents a BookStore with a name, and a list of Books.
 *
 * @author Ruan Chu
 * @author Justin Cardas
 * @author Mohammad Sadeghi
 * @version 2025
 */
class BookStore<T extends Literature>
{

    private static final int DECADE_DIFFERENCE = 10;
    private static final int FIRST_BOOK        = 0;
    private static final int PERCENT_CONSTANT  = 100;
    private static final int NOTHING           = 0;

    private final String  name;
    private final List<T> items;

    /**
     * Constructs a BookStore with a given name and list books.
     *
     * @param name the name of the bookstore
     */
    BookStore(final String name)
    {
        validateName(name);
        this.name = name;
        items     = new ArrayList<>();
    }

    /**
     * Validates the bookstore's name.
     *
     * @param name the name to validate
     * @throws IllegalArgumentException if the name is null or empty
     */
    private void validateName(final String name)
    {
        if(name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }

    /**
     * Returns the name of the bookstore
     *
     * @return the name of the bookstore as a String
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the number of books in the bookstore
     *
     * @return the number of books
     */
    public int getNumBooks()
    {
        return items.size();
    }

    /**
     * Holds a function to display a BookStore's info.
     */
    static class BookStoreInfo
    {
        /**
         * Prints out a given bookstore's name and number of books.
         *
         * @param bookStore the bookstore who details to print
         */
        public void displayInfo(BookStore<? extends Literature> bookStore)
        {
            System.out.println("BookStore: " +
                               bookStore.getName() +
                               ", Items: " +
                               bookStore.getNumBooks());
        }
    }

    /**
     * Holds functions to find the average title length and the most common year
     * published for this bookstore.
     */
    class NovelStatistics
    {
        /**
         * Returns the average title length of the bookstore.
         *
         * @return the average title length
         */
        public double averageTitleLength()
        {
            int totalLength;
            totalLength = NOTHING;
            for(final T item : items)
            {
                totalLength += item.getTitle()
                                   .length();
            }
            return (double) totalLength / items.size();
        }

        /**
         * Returns the most common publishing year for this bookstore.
         *
         * @return the most common publishing year
         */
        public int findMostCommonPublishingYear()
        {

            final Map<Integer, Integer> yearCounts;
            yearCounts = new HashMap<>();

            for(Literature item : items)
            {
                final Integer yearPublished;
                yearPublished = item.getYearPublished();


                if(!yearCounts.containsKey(yearPublished))
                {
                    yearCounts.put(yearPublished,
                                   0);
                } else
                {
                    final int currentCount;
                    currentCount = yearCounts.get(yearPublished);
                    yearCounts.put(yearPublished,
                                   currentCount + 1);
                }
            }

            final Set<Integer>      yearKeySet;
            final Iterator<Integer> yearCountsIterator;
            int                     mostCommonYear;

            yearKeySet         = yearCounts.keySet();
            yearCountsIterator = yearKeySet.iterator();
            mostCommonYear     = yearCountsIterator.next();

            while(yearCountsIterator.hasNext())
            {
                final Integer year;
                final Integer yearFrequency;

                year          = yearCountsIterator.next();
                yearFrequency = yearCounts.get(year);

                if(yearFrequency > mostCommonYear)
                {
                    mostCommonYear = year;
                }
            }

            return mostCommonYear;
        }
    }

    /**
     * Adds an item to the bookstore.
     *
     * @param item an object which extends literature to add
     */
    public void addItem(T item)
    {
        items.add(item);
    }

    /**
     * Prints out each item in the bookstore
     */
    public void printItems()
    {
        for(T item : items)
        {
            System.out.println(item);
        }
    }

    /**
     * Prints the title of each item in this bookstore that contains the specific parameter.
     *
     * @param title the title of each book in the bookstore.
     */
    public void printBookTitle(final String title)
    {
        if(items.isEmpty())
        {
            System.out.println("No items found");
        } else
        {

            items.forEach(item ->
                          {
                              if(item != null &&
                                 item.getTitle() != null &&
                                 item.getTitle()
                                     .toLowerCase()
                                     .contains(title.toLowerCase()))
                              {
                                  System.out.println(item.getTitle());
                              }
                          });
        }

    }

    /**
     * Prints all Literature titles in alphabetical order. If no items are
     * found, prints "No items found".
     */
    public void printTitlesInAlphaOrder()
    {
        if(items.isEmpty())
        {
            System.out.println("No items found");
        } else
        {
            final List<Literature> sortedItemArray;
            sortedItemArray = new ArrayList<>();

            items.forEach(item ->
                          {
                              if(item != null)
                              {
                                  sortedItemArray.add(item);
                              }
                          });

            sortedItemArray.sort(Comparator.comparing(Literature::getTitle, String::compareToIgnoreCase));
            sortedItemArray.forEach(item -> System.out.println(item.getTitle()));
        }
    }

    /**
     * Prints the titles of items from a specific decade.
     *
     * @param decade the decade to filter items by (e.g., 1990 for the 1990s)
     */
    public void printGroupByDecade(final int decade)
    {
        if(items.isEmpty())
        {
            System.out.println("No items found");
        } else
        {
            final List<Literature> decadeItems = new ArrayList<>();

            for(final Literature item : items)
            {
                if(item != null)
                {
                    int year = item.getYearPublished();
                    if(year >= decade && year < decade + DECADE_DIFFERENCE)
                    {
                        decadeItems.add(item);
                    }
                }
            }

            if(decadeItems.isEmpty())
            {
                System.out.println("No items found from the " + decade + "s.");
            } else
            {
                for(Literature item : decadeItems)
                {
                    System.out.println(item.getTitle());
                }
            }
        }
    }

    /**
     * Prints the title of the items with the longest title. If no items are
     * found, prints "No items found".
     */
    public void getLongest()
    {
        if(items.isEmpty())
        {
            System.out.println("No items found");
        } else
        {
            Literature longestItem = items.getFirst(); // Get the first item as the initial longest
            for(final Literature item : items)
            {
                if(item != null &&
                   item.getTitle() != null &&
                   item.getTitle()
                       .length() >
                   longestItem.getTitle()
                              .length())
                {
                    longestItem = item; // Update if a longer title is found
                }
            }
            System.out.println(longestItem.getTitle());
        }
    }

    /**
     * Checks if the bookstore has a book published in the given year.
     *
     * @param year the year to check
     * @return if a book with the given publishing year is in the system
     */
    public boolean isThereABookWrittenIn(final int year)
    {
        boolean result;

        result = false;

        for(final Literature item : items)
        {
            if(item != null && item.getYearPublished() == year)
            {
                result = true;
            }
        }

        return result;
    }

    /**
     * Returns the amount of Books that contain that have the given word in
     * their title.
     *
     * @param word the word to check
     * @return the amount of books with the given word as an int
     */
    public int howManyBooksContain(final String word)
    {
        int result;

        result = NOTHING;


        for(final Literature item : items)
        {
            if(item != null &&
               item.getTitle() != null &&
               item.getTitle()
                   .toLowerCase()
                   .contains(word.toLowerCase()))
            {
                result++;
            }
        }

        return result;
    }

    /**
     * Returns the percent of books in the bookstore which were published
     * between the given years (inclusive).
     *
     * @param first the lower bound
     * @param last  the upper bound
     * @return the percent of books as a double
     */
    public double whichPercentWrittenBetween(final int first,
                                             final int last)
    {
        int result;

        result = NOTHING;


        for(final Literature item : items)
        {
            if(item != null &&
               item.getYearPublished() >= first &&
               item.getYearPublished() <= last)
            {
                result++;
            }
        }

        return (double) result / items.size() * PERCENT_CONSTANT;
    }

    /**
     * Returns the oldest book by publishing year.
     *
     * @return the oldest item by publishing year.
     */
    public Literature getOldestBook()
    {
        Literature oldestBook;

        oldestBook = items.get(FIRST_BOOK);

        for(final Literature item : items)
        {
            if(item != null &&
               item.getYearPublished() < oldestBook.getYearPublished())
            {
                oldestBook = item;
            }
        }

        return oldestBook;
    }

    /**
     * returns a list containing all the books with titles of the given length.
     *
     * @param titleLength the title length to check for
     * @return a list of items with titles of the specified length
     */
    public List<Literature> getBooksThisLength(int titleLength)
    {
        final List<Literature> booksThisLength;
        booksThisLength = new ArrayList<>();

        for(final Literature item : items)
        {
            if(item != null &&
               item.getTitle()
                   .length() == titleLength)
            {
                booksThisLength.add(item);
            }
        }

        return booksThisLength;
    }

    /**
     * Drives the program.
     *
     * @param args unused
     */
    public static void main(final String[] args)
    {
        final BookStore<Literature> store;
        store = new BookStore<>("Test Store");
        store.addItem(new Novel("War and Peace",
                                "Leo Tolstoy",
                                1869));
        store.addItem(new ComicBook("Spider-Man",
                                    "Stan Lee",
                                    1962));
        store.addItem(new Magazine("National Geographic",
                                   "Gilbert Hovey Grosvenor",
                                   1888));
        store.addItem(new Novel("1984",
                                "George Orwell",
                                1949));

        store.addItem(new ComicBook("Batman",
                                    "Bob Kane",
                                    1939));

        store.addItem(new Magazine("Time",
                                   "Henry Luce",
                                   1923));

        store.addItem(new Novel("Moby-Dick",
                                "Herman Melville",
                                1851));

        store.addItem(new ComicBook("Wonder Woman",
                                    "William Moulton Marston",
                                    1941));

        store.addItem(new Magazine("Vogue",
                                   "Arthur Baldwin Turnure",
                                   1892));

        store.addItem(new Novel("Pride and Prejudice",
                                "Jane Austen",
                                1813));

        store.addItem(new ComicBook("Superman",
                                    "Jerry Siegel and Joe Shuster",
                                    1938));

        store.addItem(new Magazine("The New Yorker",
                                   "Harold Ross",
                                   1925));

        store.addItem(new Novel("The Great Gatsby",
                                "F. Scott Fitzgerald",
                                1925));
        store.printItems(); // Should print titles from different item types

        // prints all titles in alphabetical order using the printTitlesInAlphaOrder method with method reference and lambda expression
        store.printTitlesInAlphaOrder();
    }
}

