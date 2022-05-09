package fourth.data;

import fourth.data.models.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListBooksRepository implements Repository<Book> {

    private final List<Book> books = new ArrayList<>();

    @Override
    public void add(Book item) throws ArrayStoreException {
        if (books.contains(item)) {
            throw new ArrayStoreException("Book with the same name also included");
        }

        books.add(item);
    }

    @Override
    public void add(Collection<Book> items) throws ArrayStoreException {
        items.forEach(this::add);
    }

    @Override
    public void delete(Book item) {
        books.remove(item);
    }

    @Override
    public void delete(Collection<Book> items) {
        books.removeAll(items);
    }

    @Override
    public void delete(int index) {
        checkIndex(index);
        books.remove(index);
    }

    @Override
    public Book get(int index) {
        checkIndex(index);
        return books.get(index);
    }

    @Override
    public Collection<Book> getAll() {
        return books;
    }

    @Override
    public void update(int index, Book newItem) {
        checkIndex(index);
        books.remove(index);
        books.add(index, newItem);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= books.size()) {
            throw new IllegalArgumentException("Index out of bound");
        }
    }

}
