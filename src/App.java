import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address  = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair apenas os dados que interessam (título, poster, classificação)
        JsonParser jParser = new JsonParser();
        List<Map<String, String>> listOfMovies = jParser.parse(body);

        // manipular e exibir os dados

        StickerGenerator stickerGen = new StickerGenerator();

        for (Map<String,String> movie : listOfMovies)
        {
            String imageURL = movie.get("image");
            InputStream image = new URL(imageURL).openStream();
            
            String title = movie.get("title");
            String fileName = title + ".png";

            stickerGen.generate(image, fileName);

            System.out.println(title);
        }
    }
}