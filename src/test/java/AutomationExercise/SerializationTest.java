package AutomationExercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import pojo.PetStoreCategoryPojo;
import pojo.PetStorePojo;

import java.io.File;
import java.io.IOException;

public class SerializationTest {

    @Test
    public void serializationPractice() throws IOException {

        PetStorePojo petStorePojo=new PetStorePojo();

        petStorePojo.setName("Rain");
        petStorePojo.setStatus("In Progress");
        petStorePojo.setId(991);

        PetStoreCategoryPojo petStoreCategoryPojo=new PetStoreCategoryPojo();

        petStoreCategoryPojo.setId(111);
        petStoreCategoryPojo.setName("Animals");

        //*********
        ObjectMapper objectMapper=new ObjectMapper();//serialization happens here

        File file=new File("src/test/resources/pet.json");

        objectMapper.writeValue(file,petStorePojo);
    }
}
