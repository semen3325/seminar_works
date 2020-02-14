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
        Serialazers.hardserializer(Collections.emptyList(), "hardemptyFile");
        assertEquals(Collections.emptyList(), Serialazers.harddeserializer("hardemptyFile"));
    }

    @Test
    public void hardserializerNotEquals() throws IOException, ClassNotFoundException {
        List<Animal> testAnimalList = Arrays.asList(
                new Animal("Zebra", TypeOfFood.GRASS, 10, Arrays.asList(new Food("Grass", 100))),
                new Animal("Bug", TypeOfFood.GRASS, 1000, Arrays.asList(new Food("Grass", 50))));
        Serialazers.hardserializer(animalList, "hardanimalFile");
        Serialazers.hardserializer(testAnimalList, "hardtestanimalFile");
        assertEquals(animalList, Serialazers.harddeserializer("hardanimalFile"));
        assertNotEquals(testAnimalList, Serialazers.harddeserializer("hardanimalFile"));
        assertEquals(testAnimalList, Serialazers.harddeserializer("hardtestanimalFile"));
        assertNotEquals(animalList, Serialazers.harddeserializer("hardtestanimalFile"));
    }

    @Test
    public void hardserializerException() throws ClassNotFoundException {
        try {
            Serialazers.harddeserializer("blablabla");
            fail();
        } catch (NoSuchFileException e) {

        } catch (IOException e) {
            fail();
        }

    }
}