package basepackage.model;

import jakarta.persistence.*;
import lombok.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String image; // Store filename only (e.g., "pizza.jpg")
    private String description;
    private String sizes;
    private String crust;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean isVeg;

    public String getImagePath() {
        return "src/main/resources/static/images/" + image; // ✅ File path
    }

//    public void displayImage() {
//        if (image == null || image.isEmpty()) {
//            System.out.println("❌ No image available.");
//            return;
//        }
//
//        File file = new File(getImagePath()); // ✅ Get the image path
//        System.out.println("Loading: " + file.getAbsolutePath());
//
//        if (!file.exists()) {
//            System.out.println("❌ ERROR: File does not exist!");
//            return;
//        }
//
//        try {
//            BufferedImage img = ImageIO.read(file);
//            if (img == null) {
//                System.out.println("❌ ERROR: Image could not be read!");
//                return;
//            }
//
//            // ✅ Show image in a JFrame
//            JFrame frame = new JFrame("Pizza Image Viewer");
//            JLabel label = new JLabel(new ImageIcon(img));
//            frame.add(label);
//            frame.pack();
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setVisible(true);
//
//        } catch (IOException e) {
//            System.out.println("❌ ERROR: Exception while loading image!");
//            e.printStackTrace();
//        }
//    }
    
    
}
