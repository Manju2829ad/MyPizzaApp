package basepackage.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String image; // Updated to String for image URL or path
    private String description;
    private String sizes;
    private String crust;

    
     
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean isVeg;

    @JsonManagedReference(value = "toppings-pizza")  // Starts serialization for Toppings associated with Pizza
    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topping> toppings;


    @JsonManagedReference(value = "prices-pizza")  // Starts serialization for Prices associated with Pizza
    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
    private List<Price> prices;
    
    
    @Override
    public String toString() {
        return "Pizza{" +
               "id=" + id +
               ", name='" + name + '\'' +
               // Exclude price list or use a simplified format here
               '}';
    }
    
   

    public String getImage() {
        if (image != null) {
            return "/images/" + image;  // ✅ Return path only
        }
        return null;
    }

    
//    {
//    	
//    	ImageDisplay();
//    	System.out.println("Method called ");
//    }
//    
//    public class ImageDisplay {
//        public static void main(String[] args) {
//            try {
//                File file = new File("src/main/resources/static/images/test.jpg");  // Update with actual image path
//                System.out.println("Loading: " + file.getAbsolutePath());
//
//                if (!file.exists()) {
//                    System.out.println("❌ ERROR: File does not exist!");
//                    return;
//                }
//
//                BufferedImage image = ImageIO.read(file);
//                if (image == null) {
//                    System.out.println("❌ ERROR: Image could not be read!");
//                    return;
//                }
//
//                // Convert to Base64 String
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                ImageIO.write(image, "jpg", baos);
//                String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());
//
//                // Print Base64 String (Can be copied and viewed online)
//                System.out.println("Base64 Image Data: " + base64Image);
//
//            } catch (IOException e) {
//                System.out.println("❌ ERROR: Exception while loading image!");
//                e.printStackTrace();
//            }
//        }
//    }
//
//	private void ImageDisplay() {
//		// TODO Auto-generated method stub
//		
//	}
    
}
