package fifth.data.models;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record Book(String name, String genre, int circulation) implements Comparable {

    private static final String namePattern = "Name: ";

    private static final String genrePattern = "Genre: ";

    private static final String circulationPattern = "Circulation: ";

    @Override
    public String toString() {
        return String.format("\n%s%s\n%s%s\n%s%s\n", namePattern, name, genrePattern, genre, circulationPattern, circulation);
    }

    public static Book parse(String data) {
        try {
            List<String> fields = Arrays.stream(data.split("\n")).filter(str -> !str.isEmpty()).toList();
            String name = fields.get(0).split(namePattern)[1];
            String genre = fields.get(1).split(genrePattern)[1];
            int circulation = Integer.parseInt(fields.get(2).split(circulationPattern)[1]);

            return new Book(name, genre, circulation);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed parse to Book: %s", data));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            return Objects.equals(((Book) obj).name, this.name);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + genre.hashCode() + circulation + 30;
    }

    @Override
    public int compareTo(Object obj) {
        if (obj.getClass() == this.getClass()) {
            int objCirculation = ((Book) obj).circulation;
            return Integer.compare(objCirculation, this.circulation);
        }

        if (obj.getClass() == Integer.class) {
            return Integer.compare(((Integer) obj), this.circulation);
        }

        throw new IllegalArgumentException("Book.compareTo accepts only Book and Integer");
    }
}
