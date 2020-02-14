package seminar1;

import java.io.Serializable;
import java.util.Objects;

public class Food implements Serializable {
    private String name;
    private int count;

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return count == food.count &&
                Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, count);
    }

    public Food(String name, int count) {
        this.name = name;
        this.count = count;
    }
}
