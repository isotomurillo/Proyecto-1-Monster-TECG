import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json {

    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper getObjectMapper = new ObjectMapper();
        return getObjectMapper;
    }

    public static JsonNode parse(String jsonSource) throws JsonProcessingException {
        return objectMapper.readTree(jsonSource);
    }

    public static <A> A fromJson(JsonNode node, Class<A> aClass) throws JsonProcessingException{
        return objectMapper.treeToValue(node,aClass);
    }

    public static JsonNode toJson(Object a){
        return objectMapper.valueToTree(a);
    }

    public static String generateString(JsonNode node, boolean pretty) throws JsonProcessingException{
        ObjectWriter objectWriter = objectMapper.writer();
        if(pretty){
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(node);
    }
}
