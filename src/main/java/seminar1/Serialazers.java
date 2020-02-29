package seminar1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Serialazers {
    public static void serializer(List<Animal> animalList, String nameFile) throws IOException {

        Path path = Paths.get(nameFile);
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(animalList);
        }
    }

    public static List<Animal> deserializer(String nameFile) throws IOException, ClassNotFoundException {

        Path path = Paths.get(nameFile);
        List<Animal> animalList;
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(Files.newInputStream(path))) {
            animalList = (List<Animal>) inputStream.readObject();
        }

        return animalList;
    }

    public static void hardSerializer(List<Animal> animalList, String nameFile) throws IOException {
        Path path = Paths.get(nameFile);
        try (DataOutputStream outputStream =
                     new DataOutputStream(Files.newOutputStream(path))) {
            outputStream.writeInt(animalList.size());
            for (Animal animal : animalList) {
                outputStream.writeUTF(animal.getName());
                outputStream.writeUTF(animal.getType().name());
                outputStream.writeInt(animal.getAge());
                outputStream.writeInt(animal.getFoodList().size());
                for (Food food : animal.getFoodList()) {
                    outputStream.writeUTF(food.getName());
                    outputStream.writeInt(food.getCount());
                }
            }
        }
    }

    public static List<Animal> hardDeserializer(String nameFile) throws IOException {
        Path path = Paths.get(nameFile);
        List<Animal> animalList = new ArrayList<>();

        String name;
        TypeOfFood type;
        int age;

        try (DataInputStream inputStream =
                     new DataInputStream(Files.newInputStream(path))) {
            int countAnimal = inputStream.readInt();
            for (int i = 0; i < countAnimal; i++) {
                name = inputStream.readUTF();
                type = TypeOfFood.valueOf(inputStream.readUTF());
                age = inputStream.readInt();
                int countFood = inputStream.readInt();
                List<Food> foodList = new ArrayList<>();
                for (int j = 0; j < countFood; j++) {
                    String nameFood = inputStream.readUTF();
                    int count = inputStream.readInt();
                    foodList.add(new Food(nameFood, count));
                }
                animalList.add(new Animal(name, type, age, foodList));
            }
        }
        return animalList;
    }
}
