package fifth.ui;

import common.Common;
import fifth.data.Repository;
import fifth.data.models.Book;
import fifth.services.GetBooksByCirculationAndGenreUseCase;
import fifth.services.GetBooksByCirculationUseCase;
import fifth.services.GetBooksSortedByUseCase;
import fifth.services.GetSortedBooksUseCase;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class UserController {

    private final GetSortedBooksUseCase getSortedBooksUseCase;

    private final GetBooksSortedByUseCase getBooksSortedByUseCase;

    private final GetBooksByCirculationAndGenreUseCase getBooksByCirculationAndGenreUseCase;

    private final GetBooksByCirculationUseCase getBooksByCirculationUseCase;

    private final Repository<Book> repository;

    public UserController(
            Repository<Book> repository,
            GetSortedBooksUseCase getSortedBooksUseCase,
            GetBooksSortedByUseCase getBooksSortedByUseCase,
            GetBooksByCirculationAndGenreUseCase getBooksByCirculationAndGenreUseCase,
            GetBooksByCirculationUseCase getBooksByCirculationUseCase
    ) {
        this.repository = repository;
        this.getSortedBooksUseCase = getSortedBooksUseCase;
        this.getBooksSortedByUseCase = getBooksSortedByUseCase;
        this.getBooksByCirculationUseCase = getBooksByCirculationUseCase;
        this.getBooksByCirculationAndGenreUseCase = getBooksByCirculationAndGenreUseCase;
    }

    public void start() {
        while (true) {
            System.out.flush();
            Common.print("\n8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8");
            Common.print("1: Show all books");
            Common.print("2: Show sorted books");
            Common.print("3: Show sorted books by ...");
            Common.print("4: Show books by circulation");
            Common.print("5: Show books by circulation and name");
            Common.print("6: Add book");
            Common.print("7: Delete book");
            Common.print("0: Exit");

            switch (Common.inputInt("Type variant", "From 0 to 5", value -> value >= 0 && value <= 7)) {
                case 0: return;
                case 1: showAll(); break;
                case 2: getSortedBooksController(); break;
                case 3: getSortedBooksByController(); break;
                case 4: getBooksByCirculationController(); break;
                case 5: getBooksByCirculationAndNameController(); break;
                case 6: addNewBook(); break;
                case 7: deleteBook(); break;
            }

            try {
                Common.print("Enter for continue");
                System.in.read();
            } catch (IOException e) {
                Common.print("Oops! Something went wrong");
                return;
            }
        }
    }

    private void showAll() {
        Common.print(Arrays.toString(repository.getAll().toArray(new Book[0])));
    }

    private void getSortedBooksController() {
        Common.print(Arrays.toString(getSortedBooksUseCase.getSortedBooks().toArray(new Book[0])));
    }

    private void getSortedBooksByController() {
        int count = Common.inputInt("Type a count of books", "Must be bigger then 0", value -> value > 0);
        String field = Common.input("Type a field for sort - 'name' or 'genre'", "Must be 'name' or 'genre'", value -> value.equals("name") || value.equals("genre"));
        if (field.equals("name")) {
            Common.print(Arrays.toString(getBooksSortedByUseCase.getSortedBooksBy(count, Book::name).toArray(new Book[0])));
        } else {
            Common.print(Arrays.toString(getBooksSortedByUseCase.getSortedBooksBy(count, Book::genre).toArray(new Book[0])));
        }
    }

    private void getBooksByCirculationController() {
        int circulation = Common.inputInt("Type a circulation", "Must be bigger then 0", value -> value > 0);
        Common.print(Arrays.toString(getBooksByCirculationUseCase.getBooksByCirculation(circulation).toArray(new Book[0])));
    }

    private void getBooksByCirculationAndNameController() {
        int circulation = Common.inputInt("Type a circulation", "Must be bigger then 0", value -> value > 0);
        String genre = Common.input("Type a genre", "Length must be bigger then 0", value -> value.length() > 0);
        Common.print(Arrays.toString(getBooksByCirculationAndGenreUseCase.getBooksByCirculationAndGenre(circulation, genre).toArray(new Book[0])));
    }

    private void addNewBook() {
        String genre = Common.input("Type a genre", "Length must be bigger then 3", value -> value.length() > 3);
        String name = Common.input("Type a name", "Length must be bigger then 3", value -> value.length() > 3);
        int circulation = Common.inputInt("Type a circulation", "Must be bigger then 0", value -> value > 0);

        try {
            repository.add(new Book(name, genre, circulation));
        } catch (ArrayStoreException e) {
            Common.print("This book is already in repository");
        }
    }

    private void deleteBook() {
        String name = Common.input("Type a name", "Length must be bigger then 3", value -> value.length() > 3);

        Optional<Book> book = getSortedBooksUseCase.getSortedBooks().stream().filter(item -> Objects.equals(item.name(), name)).findFirst();
        if (book.isPresent()) {
                repository.delete(book.get());
        } else {
            Common.print("Can not find a book with name %s", name);
        }
    }

}
