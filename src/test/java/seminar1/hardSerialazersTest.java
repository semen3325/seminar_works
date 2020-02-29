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
    public void hardSerializer() throws IOException, ClassNotFoundException {
        Serialazers.hardSerializer(animalList, "hardanimalFile");
        assertEquals(animalList, Serialazers.hardDeserializer("hardanimalFile"));
    }

    @Test
    public void hardSerializerEmpty() throws IOException, ClassNotFoundException {
        Serialazers.hardSerializer(Collections.emptyList(), "hardemptyFile");
        assertEquals(Collections.emptyList(), Serialazers.hardDeserializer("hardemptyFile"));
    }

    @Test
    public void hardSerializerNotEquals() throws IOException, ClassNotFoundException {
        List<Animal> testAnimalList = Arrays.asList(
                new Animal("Zebra", TypeOfFood.GRASS, 10, Arrays.asList(new Food("Grass", 100))),
                new Animal("Bug", TypeOfFood.GRASS, 1000, Arrays.asList(new Food("Grass", 50))));
        Serialazers.hardSerializer(animalList, "hardanimalFile");
        Serialazers.hardSerializer(testAnimalList, "hardtestanimalFile");
        assertEquals(animalList, Serialazers.hardDeserializer("hardanimalFile"));
        assertNotEquals(testAnimalList, Serialazers.hardDeserializer("hardanimalFile"));
        assertEquals(testAnimalList, Serialazers.hardDeserializer("hardtestanimalFile"));
        assertNotEquals(animalList, Serialazers.hardDeserializer("hardtestanimalFile"));
    }

    @Test
    public void hardSerializerException() throws ClassNotFoundException {
        try {
            Serialazers.hardDeserializer("blablabla");
            fail();
        } catch (NoSuchFileException e) {

        } catch (IOException e) {
            fail();
        }

    }
}