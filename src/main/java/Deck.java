import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Random;
public class Deck {

    OpenFile open = new OpenFile();

    public Stack Deck1() throws JsonProcessingException {
        int a = 0;
        JsonNode node;
        Card card;
        Stack stack = new Stack();
        Random random = new Random();
        while (a < 16) {
            int cardRandom = random.nextInt(16);
            switch (cardRandom){
                case 0:
                    node = Json.parse(open.OpenFiles("Esbirro_lvl1.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 1:
                    node = Json.parse(open.OpenFiles("Esbirro_lvl2.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 2:
                    node = Json.parse(open.OpenFiles("Esbirro_lvl3.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 3:
                    node = Json.parse(open.OpenFiles("Esbirro_lvl4.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 4:
                    node = Json.parse(open.OpenFiles("Esbirro_lvl5.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 5:
                    node = Json.parse(open.OpenFiles("Evento_Ataque.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 6:
                    node = Json.parse(open.OpenFiles("Evento_Curar.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 7:
                    node = Json.parse(open.OpenFiles("Evento_Mana.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 8:
                    node = Json.parse(open.OpenFiles("Evento_Proteccion.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 9:
                    node = Json.parse(open.OpenFiles("Evento_Robar.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 10:
                    node = Json.parse(open.OpenFiles("Hechizo_Congelar.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 11:
                    node = Json.parse(open.OpenFiles("Hechizo_Intercambio.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 12:
                    node = Json.parse(open.OpenFiles("Hechizo_PoderSupremo.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 13:
                    node = Json.parse(open.OpenFiles("Hechizo_Curar.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 14:
                    node = Json.parse(open.OpenFiles("Hechizo_CurarPlus.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 15:
                    node = Json.parse(open.OpenFiles("Hechizo_Duplicar.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
                case 16:
                    node = Json.parse(open.OpenFiles("Hechizo_Robar.json"));
                    card = Json.fromJson(node.get("Attributes"),Card.class);
                    stack.insert(card);
                    a++;
                    break;
            }
        }
        return stack;
    }
}
