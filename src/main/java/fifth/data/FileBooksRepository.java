package fifth.data;

import fifth.data.models.Book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileBooksRepository implements Repository<Book> {

    private final String pathToDir;

    private final String fileNamePattern = "book-%s.txt";

    public FileBooksRepository(String pathToDir) {
        this.pathToDir = pathToDir;

        try {
            Files.createDirectory(Paths.get(pathToDir));
        } catch (FileAlreadyExistsException e) {
            // Directory exist
        } catch (IOException e) {
            throw new RuntimeException("Failed to create directory!");
        }
    }

    @Override
    public void add(Book item) throws ArrayStoreException {
        Path path = getPath(item.name());

        if (Files.exists(path)) {
            throw new ArrayStoreException("File already exists");
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write(item.toString());
        } catch (IOException e) {
            throw new ArrayStoreException(String.format("Failed to add book %s", item.name()));
        }

    }

    @Override
    public void add(Collection<Book> item) {
        item.forEach(this::add);
    }

    @Override
    public void delete(Book item) {
        try {
            Files.delete(getPath(item.name()));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to delete book %s", item.name()));
        }
    }

    @Override
    public void delete(Collection<Book> item) {
        item.forEach(this::delete);
    }

    @Override
    public void delete(int index) {
        delete(get(index));
    }

    @Override
    public Book get(int index) {
        return (Book) getAll().toArray()[index];
    }

    @Override
    public Collection<Book> getAll() {
        try {
            Path pathToDir = Paths.get(this.pathToDir);
            Stream<Path> files = Files.list(pathToDir);
            return files.map(this::readFile).filter(Objects::nonNull).toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read!");
        }
    }

    @Override
    public void update(int index, Book newItem) {
        delete(index);
        add(newItem);
    }

    private Book readFile(Path path) {
        try {
            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            String data = reader.lines().collect(Collectors.joining("\n"));
            return Book.parse(data);
        } catch (Exception e) {
            return null;
        }
    }

    private Path getPath(String name) {
        String fileName = String.format(fileNamePattern, name.toLowerCase());
        return FileSystems.getDefault().getPath(pathToDir, fileName);
    }

}
