package fifth.data;

import fifth.data.models.Book;

import java.io.File;
import java.util.Collection;

public class FileBooksRepository implements Repository<Book> {

    private final File folder;

    public FileBooksRepository(String path) {
        folder = new File(path);
    }

    @Override
    public void add(Book item) throws ArrayStoreException {

    }

    @Override
    public void add(Collection<Book> item) {

    }

    @Override
    public void delete(Book item) {

    }

    @Override
    public void delete(Collection<Book> item) {

    }

    @Override
    public void delete(int index) {

    }

    @Override
    public Book get(int index) {
        return null;
    }

    @Override
    public Collection<Book> getAll() {
        return null;
    }

    @Override
    public void update(int index, Book newItem) {

    }

}
