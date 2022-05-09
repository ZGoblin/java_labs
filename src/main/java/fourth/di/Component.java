package fourth.di;

import fourth.data.ListBooksRepository;
import fourth.data.Repository;
import fourth.data.models.Book;
import fourth.services.GetBooksByCirculationAndGenreUseCase;
import fourth.services.GetBooksByCirculationUseCase;
import fourth.services.GetBooksSortedByUseCase;
import fourth.services.GetSortedBooksUseCase;
import fourth.ui.UserController;

public class Component {

    private static Repository<Book> repository;

    public static void init() {
        repository = new ListBooksRepository();

        repository.add(new Book("Taras Shevchenko Collections", "Novel", 30));
        repository.add(new Book("Attack on Titans", "Manga", 56));
        repository.add(new Book("Ice and Fire", "Fantasy", 4));
        repository.add(new Book("The Witcher", "Fantasy", 12));
        repository.add(new Book("Handmades tale", "Drama", 25));
        repository.add(new Book("Green Mile", "Triller", 34));
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
