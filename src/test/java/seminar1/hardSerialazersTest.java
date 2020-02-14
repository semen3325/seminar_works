package seminar1;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class hardSerialazersTest {
    List<Animal> animalList = Arrays.asList(
            new Animal("Lion", TypeOfFood.HUMAN, 32, Arrays.asList(new Food("Misha", 1))),
            new Animal("Tiger", TypeOfFood.HUMAN, 23, Arrays.asList(new Food("Atmir", 1))));

    @Test
    public void hardserializer() throws IOException, ClassNotFoundException {
        Serialazers.hardserializer(animalList, "hardanimalFile");
        assertEquals(animalList, Serialazers.harddeserializer("hardanimalFile"));
    }

    @Test
    public void hardserializerEmpty() throws IOException, ClassNotFoundException {
        Serialazers.serializer(Collections.emptyList(), "hardemptyFile");
        assertEquals(Collections.emptyList(), Serialazers.deserializer("hardemptyFile"));
    }

    @Test
    public void hardserializerNotEquals() throws IOException, ClassNotFoundException {
        List<Animal> testAnimalList = Arrays.asList(
                new Animal("Zebra", TypeOfFood.GRASS, 10, Arrays.asList(new Food("Grass", 100))),
                new Animal("Bug", TypeOfFood.GRASS, 1000, Arrays.asList(new Food("Grass", 50))));
        Serialazers.serializer(animalList, "hardanimalFile");
        Serialazers.serializer(testAnimalList, "hardtestanimalFile");
        assertEquals(animalList, Serialazers.deserializer("hardanimalFile"));
        assertNotEquals(testAnimalList, Serialazers.deserializer("hardanimalFile"));
        assertEquals(testAnimalList, Serialazers.deserializer("hardtestanimalFile"));
        assertNotEquals(animalList, Serialazers.deserializer("hardtestanimalFile"));
    }

    @Test
    public void hardserializerException() throws ClassNotFoundException {
        try {
            Serialazers.deserializer("blablabla");
            fail();
        } catch (NoSuchFileException e) {

        } catch (IOException e) {
            fail();
        }

    }
}