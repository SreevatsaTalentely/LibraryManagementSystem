import java.util.*;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrow() {
        this.isAvailable = false;
    }

    public void returnBook() {
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + (isAvailable ? " [Available]" : " [Borrowed]");
    }
}

class Library {
    private Map<String, Book> bookCollection;

    public Library() {
        this.bookCollection = new HashMap<>();
    }

    public void addBook(Book book) {
        bookCollection.put(book.getIsbn(), book);
        System.out.println("Book added: " + book);
    }

    public void removeBook(String isbn) {
        if (bookCollection.containsKey(isbn)) {
            Book book = bookCollection.remove(isbn);
            System.out.println("Book removed: " + book);
        } else {
            System.out.println("No book found with ISBN: " + isbn);
        }
    }

    public Book searchByTitle(String title) {
        for (Book book : bookCollection.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Book searchByAuthor(String author) {
        for (Book book : bookCollection.values()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null;
    }

    public void displayBooks() {
        if (bookCollection.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        for (Book book : bookCollection.values()) {
            System.out.println(book);
        }
    }

    public boolean borrowBook(String isbn) {
        if (bookCollection.containsKey(isbn)) {
            Book book = bookCollection.get(isbn);
            if (book.isAvailable()) {
                book.borrow();
                System.out.println("You have borrowed: " + book);
                return true;
            } else {
                System.out.println("The book is already borrowed.");
                return false;
            }
        } else {
            System.out.println("No book found with ISBN: " + isbn);
            return false;
        }
    }

    public boolean returnBook(String isbn) {
        if (bookCollection.containsKey(isbn)) {
            Book book = bookCollection.get(isbn);
            if (!book.isAvailable()) {
                book.returnBook();
                System.out.println("You have returned: " + book);
                return true;
            } else {
                System.out.println("The book wasn't borrowed.");
                return false;
            }
        } else {
            System.out.println("No book found with ISBN: " + isbn);
            return false;
        }
    }
}

public class LibraryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add a Book");
            System.out.println("2. Remove a Book");
            System.out.println("3. Search for a Book");
            System.out.println("4. Borrow a Book");
            System.out.println("5. Return a Book");
            System.out.println("6. Display All Books");
            System.out.println("7. Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    displayBooks();
                    break;
                case 7:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        Book book = new Book(title, author, isbn);
        library.addBook(book);
    }

    private static void removeBook() {
        System.out.print("Enter the ISBN of the book to remove: ");
        String isbn = scanner.nextLine();
        library.removeBook(isbn);
    }

    private static void searchBook() {
        System.out.println("Search by: ");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            System.out.print("Enter book title to search: ");
            String title = scanner.nextLine();
            Book book = library.searchByTitle(title);
            if (book != null) {
                System.out.println("Found: " + book);
            } else {
                System.out.println("No book found with title: " + title);
            }
        } else if (choice == 2) {
            System.out.print("Enter author name to search: ");
            String author = scanner.nextLine();
            Book book = library.searchByAuthor(author);
            if (book != null) {
                System.out.println("Found: " + book);
            } else {
                System.out.println("No book found by author: " + author);
            }
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void borrowBook() {
        System.out.print("Enter the ISBN of the book to borrow: ");
        String isbn = scanner.nextLine();
        library.borrowBook(isbn);
    }

    private static void returnBook() {
        System.out.print("Enter the ISBN of the book to return: ");
        String isbn = scanner.nextLine();
        library.returnBook(isbn);
    }

    private static void displayBooks() {
        library.displayBooks();
    }
}
