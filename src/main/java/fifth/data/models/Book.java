package fifth.data.models;

import java.util.Objects;

public record Book(String name, String genre, int circulation) implements Comparable {

    @Override
    public String toString() {
        return String.format("\nName: %s\nGenre: %s\nCirculation: %s\n", name, genre, circulation);
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
