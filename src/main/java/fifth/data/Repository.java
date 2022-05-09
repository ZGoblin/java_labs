package fifth.data;

import fifth.data.models.Book;

import java.util.Collection;

public interface Repository<T> {

    public void add(T item) throws ArrayStoreException;

    public void add(Collection<T> item);

    public void delete(T item);

    public void delete(Collection<T> item);

    public void delete(int index);

    public T get(int index);

    public Collection<T> getAll();

    public void update (int index, T newItem);

    public static Repository<Book> createBookRepository(String path) {
        if (path.isEmpty()) {
            return new ListBooksRepository();
        } else {
            return new FileBooksRepository(path);
        }
    }

}
