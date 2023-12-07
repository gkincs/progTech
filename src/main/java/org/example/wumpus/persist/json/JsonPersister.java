package org.example.wumpus.persist.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.wumpus.model.WumpusMapVO;
import org.slf4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class JsonPersister {
    private final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JsonPersister.class);
    public void writeMap(String name, WumpusMapVO map){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String mapForSave = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
            FileWriter fileWriter = new FileWriter("save" + name + ".json");
            fileWriter.write(mapForSave);
            fileWriter.close();
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to save");
        } catch (IOException e) {
            LOGGER.error("Failed to save", e);
        }
    }
}
