package org.example.wumpus.persist.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.wumpus.model.WumpusMapVO;
import org.slf4j.Logger;
import java.io.*;

public class JsonPersister {
    private final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JsonPersister.class);

    public void writeMap(String name, WumpusMapVO map) {
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

    public WumpusMapVO readMap(String name) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("save" + name + ".json");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            while (bufferedReader.ready()) {
                stringBuilder.append(bufferedReader.readLine());
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(stringBuilder.toString(), WumpusMapVO.class);
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found");
        } catch (IOException e) {
            LOGGER.error("Failed to read file", e);
        }
        return new WumpusMapVO(new char[1][0]);
    }
}
