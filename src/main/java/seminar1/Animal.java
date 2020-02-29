package seminar1;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Animal implements Serializable {
    private final String name;
    private final TypeOfFood type;
    private final int age;
    private final List<Food> foodList;

    public Animal(String name, TypeOfFood type, int age, List<Food> foodList) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.foodList = foodList;
    }

    public String getName() {
        return name;
    }

    public TypeOfFood getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", age=" + age +
                ", foodList=" + foodList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age &&
                Objects.equals(name, animal.name) &&
                type == animal.type &&
                Objects.equals(foodList, animal.foodList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, age, foodList);
    }

}
