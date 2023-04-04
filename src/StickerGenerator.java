import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator
{
    public void generate(InputStream input, String fileName) throws Exception
    {
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg";
        // InputStream input = new URL(url).openStream();
        
        // ler a imagem
        BufferedImage originalImage = ImageIO.read(input);

        // criar nova imagem redimensionada e com transparência
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 80;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // escrever uma frase na nova imagem
        var phrase = "MOZINHA, VAMOS ASSISTIR A ESSE FILME!";
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 32);
        var color = Color.YELLOW;
        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(phrase, 0, newHeight - 30);

        // escrever a nova imagem em um arquivo
        String figFilePath = "fig/" + fileName;
        ImageIO.write(newImage, "png", new File(figFilePath));
    }
}