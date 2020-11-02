import com.fasterxml.jackson.databind.JsonNode;

import java.io.BufferedReader;
import java.io.FileReader;

public class OpenFile {

    public String OpenFiles(String file){
        String json = null;
        try{
            BufferedReader br = new BufferedReader( new FileReader(file));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null){
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                json = sb.toString();
            }finally {
                br.close();
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return json;
    }
}
