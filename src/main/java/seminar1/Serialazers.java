package seminar1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public static void hardserializer(List<Animal> animalList, String nameFile) throws IOException {
        Path path = Paths.get(nameFile);
        try (DataOutputStream outputStream =
                     new DataOutputStream(Files.newOutputStream(path))) {
            outputStream.
        }


    }

}
