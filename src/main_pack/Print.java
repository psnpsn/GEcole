package main_pack;

import Models.Eleve;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Print {

    
    
    public static void print_eleve(Eleve eleve) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDFont font = PDType1Font.COURIER;

        String id  = "Identifiant               = " + eleve.getId_e();
        String nom = "Nom de l'eleve            = " + eleve.getNom() + " " + eleve.getPrenom();
        String dn  = "Date et Lieu de Naissance = " + eleve.getDateNaiss() + "  a " + eleve.getLieuNaiss();
        try {
            PDPageContentStream contents = new PDPageContentStream(document, page);
            
            contents.beginText();
            contents.setFont(font, 12);
            contents.newLineAtOffset(100, 700);
            contents.setLeading(14.5f);
            contents.showText(id);
            contents.newLine();
            contents.showText(nom);
            contents.newLine();
            contents.showText(dn);
            
            contents.endText();

            contents.close();
            document.save("test.pdf");
            document.close();
        } catch (IOException ex) {
            Logger.getLogger(Main_class.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
