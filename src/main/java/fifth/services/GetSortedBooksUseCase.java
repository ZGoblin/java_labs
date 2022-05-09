package fifth.services;

import fifth.data.Repository;
import fifth.data.models.Book;

import java.util.Comparator;
import java.util.List;

public class GetSortedBooksUseCase {

    private final Repository<Book> repository;

    public GetSortedBooksUseCase(Repository<Book> repository) {
        this.repository = repository;
    }

    public List<Book> getSortedBooks() {
        return repository.getAll().stream().sorted(Comparator.comparing(Book::name)).toList();
    }

}
