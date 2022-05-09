package fifth.services;

import fifth.data.Repository;
import fifth.data.models.Book;

import java.util.List;

public class GetBooksByCirculationAndGenreUseCase {

    private final Repository<Book> repository;

    public GetBooksByCirculationAndGenreUseCase(Repository<Book> repository) {
        this.repository = repository;
    }

    public List<Book> getBooksByCirculationAndGenre(Integer circulation, String genre) {
        return repository.getAll()
                .stream()
                .filter(book -> book.compareTo(circulation) > 0 && book.genre().equals(genre))
                .toList();
    }

}
