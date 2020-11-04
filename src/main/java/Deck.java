import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.BufferedReader;
import java.io.FileReader;

public class Deck {

    OpenFile enemy_1 = new OpenFile();
    OpenFile enemy_2 = new OpenFile();
    OpenFile enemy_3 = new OpenFile();
    OpenFile enemy_4 = new OpenFile();
    OpenFile enemy_5 = new OpenFile();

    public void Deck1() throws JsonProcessingException {

        JsonNode node1 = Json.parse(enemy_1.OpenFiles("Esbirro_lvl1.json"));
        JsonNode node2 = Json.parse(enemy_2.OpenFiles("Esbirro_lvl2.json"));
        JsonNode node3 = Json.parse(enemy_3.OpenFiles("Esbirro_lvl3.json"));
        JsonNode node4 = Json.parse(enemy_4.OpenFiles("Esbirro_lvl4.json"));
        JsonNode node5 = Json.parse(enemy_5.OpenFiles("Esbirro_lvl5.json"));

        Card card1 = Json.fromJson(node1.get("Attributes"),Card.class);
        Stack stack1 = new Stack();
        stack1.insert(card1);
        Card hola = stack1.get();
        System.out.println(hola.type);


    }
}
