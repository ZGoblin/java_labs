package fourth.services;

import fourth.data.Repository;
import fourth.data.models.Book;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class GetBooksSortedByUseCase {

    private final Repository<Book> repository;

    public GetBooksSortedByUseCase(Repository<Book> repository) {
        this.repository = repository;
    }

    public List<Book> getSortedBooksBy(int count, Function<Book, String> field) {
        return repository.getAll().stream().sorted(Comparator.comparing(field)).limit(count).toList();
    }

}
