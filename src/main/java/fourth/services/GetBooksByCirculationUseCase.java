package fourth.services;

import fourth.data.Repository;
import fourth.data.models.Book;

import java.util.List;

public class GetBooksByCirculationUseCase {

    private final Repository<Book> repository;

    public GetBooksByCirculationUseCase(Repository<Book> repository) {
        this.repository = repository;
    }

    public List<Book> getBooksByCirculation(int circulation) {
        return repository.getAll().stream().filter(book -> book.compareTo(circulation) > 0).toList();
    }

}
