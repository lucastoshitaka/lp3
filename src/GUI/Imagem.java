package GUI;

import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import javax.swing.ImageIcon;

public class Imagem {

    public String MoverImagem(String origem, String destino, String id) throws Exception {
        try {
            File source = new File(origem);
            File dest = new File("src/GUI/" + origem.substring(origem.lastIndexOf("\\")));
            Files.copy(source.toPath(), dest.toPath());

            File name = new File(String.valueOf(dest));
            String Dest = String.valueOf(dest);
            File rename = new File(Dest.substring(0, Dest.lastIndexOf("\\")) + "/" + id + Dest.substring(Dest.lastIndexOf(".")));
            name.renameTo(rename);

            Dest = String.valueOf(rename);
            source = new File(Dest);
            dest = new File(destino + Dest.substring(Dest.lastIndexOf("\\")));
            Files.copy(source.toPath(), dest.toPath());
            Dest = String.valueOf(dest);

            DeletarImagem(String.valueOf(rename));

            return Dest;
        } catch (Exception img) {
            System.out.println("MoverImagem Error: " + img);
            return null;
        }
    }

    public void DeletarImagem(String origem) {
        try {
            File img = new File(origem);
            Files.delete(img.toPath());
        } catch (Exception img) {
            System.out.println("DeletarImagem Error: " + img);
        }
    }

    public Image GetImg(String origem, int x, int y) {
        ImageIcon imgIcone = new ImageIcon(origem);
        Image iIcone = imgIcone.getImage();
        Image Icone = iIcone.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
        return Icone;
    }
}

