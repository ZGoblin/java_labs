package fifth.di;

import fifth.data.FileBooksRepository;
import fifth.data.ListBooksRepository;
import fifth.data.Repository;
import fifth.data.models.Book;
import fifth.services.GetBooksByCirculationAndGenreUseCase;
import fifth.services.GetBooksByCirculationUseCase;
import fifth.services.GetBooksSortedByUseCase;
import fifth.services.GetSortedBooksUseCase;
import fifth.ui.UserController;

public class Component {

    private static Repository<Book> repository;

    public static void init(String path) {
        if (path.isEmpty()) {
            repository = new ListBooksRepository();
        } else {
            repository = new FileBooksRepository(path);
        }

        try {
            repository.add(new Book("Taras Shevchenko Collections", "Novel", 30));
            repository.add(new Book("Attack on Titans", "Manga", 56));
            repository.add(new Book("Ice and Fire", "Fantasy", 4));
            repository.add(new Book("The Witcher", "Fantasy", 12));
            repository.add(new Book("Handmades tale", "Drama", 25));
            repository.add(new Book("Green Mile", "Triller", 34));
        } catch (Exception e) {  }
    }

    private static GetBooksByCirculationAndGenreUseCase getBooksByCirculationAndNameUseCase() {
        return new GetBooksByCirculationAndGenreUseCase(repository);
    }

    private static GetBooksByCirculationUseCase getBooksByCirculationUseCase() {
        return new GetBooksByCirculationUseCase(repository);
    }

    private static GetBooksSortedByUseCase getBooksSortedByUseCase() {
        return new GetBooksSortedByUseCase(repository);
    }

    private static GetSortedBooksUseCase getSortedBooksUseCase() {
        return new GetSortedBooksUseCase(repository);
    }

    public static UserController getUserController() {
        return new UserController(
                repository,
                getSortedBooksUseCase(),
                getBooksSortedByUseCase(),
                getBooksByCirculationAndNameUseCase(),
                getBooksByCirculationUseCase()
        );
    }

}
