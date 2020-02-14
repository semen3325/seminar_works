package seminar1;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SerialazersTest {

    List<Animal> animalList = Arrays.asList(
            new Animal("Lion", TypeOfFood.HUMAN, 32, Arrays.asList(new Food("Misha", 1))),
            new Animal("Tiger", TypeOfFood.HUMAN, 23, Arrays.asList(new Food("Atmir", 1))));

    @Test
    public void serializer() throws IOException, ClassNotFoundException {
        Serialazers.serializer(animalList, "animalFile");
        assertEquals(animalList, Serialazers.deserializer("animalFile"));
    }

    @Test
    public void serializerEmpty() throws IOException, ClassNotFoundException {
        Serialazers.serializer(Collections.emptyList(), "emptyFile");
        assertEquals(Collections.emptyList(), Serialazers.deserializer("emptyFile"));
    }

    @Test
    public void serializerNotEquals() throws IOException, ClassNotFoundException {
        List<Animal> testAnimalList = Arrays.asList(
                new Animal("Zebra", TypeOfFood.GRASS, 10, Arrays.asList(new Food("Grass", 100))),
                new Animal("Bug", TypeOfFood.GRASS, 1000, Arrays.asList(new Food("Grass", 50))));
        Serialazers.serializer(animalList, "animalFile");
        Serialazers.serializer(testAnimalList,"testanimalFile");
        assertEquals(animalList, Serialazers.deserializer("animalFile"));
        assertNotEquals(testAnimalList,Serialazers.deserializer("animalFile"));
        assertEquals(testAnimalList, Serialazers.deserializer("testanimalFile"));
        assertNotEquals(animalList,Serialazers.deserializer("testanimalFile"));
    }

    @Test
    public void serializerException() throws ClassNotFoundException {
        try {
            Serialazers.deserializer("blablabla");
            fail();
        } catch (NoSuchFileException e) {

        } catch (IOException e) {
            fail();
        }
    }

}