//package basepackage.configuration;
//
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.storage.Blob;
//import com.google.cloud.storage.Bucket;
//import com.google.cloud.storage.BucketInfo.LifecycleRule.SetStorageClassLifecycleAction;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.cloud.StorageClient;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.UUID;
//
//@Configuration
//public class FirebaseConfig {
//
//	
//	
//	private static final String bucketName= "";
//	
//	
//	
//	//Initialze the firebase admin sdk 
//	public  static void  initializeFireBase() throws IOException{
//		
//		
//		//opens an intput stream to read the service account JSON File . 
//
//		
//		InputStream  serviceAccount=  new FileInputStream("path/to/your/serviceAccountKey.json");
//		
//		
//		
//		FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
//				.setStorageBucket(bucketName).build();
//		
//		
//		
//	}
//	
//	
//	
//		//uploading the images to fireBase
//		public static String uploadImage(String filepath) throws IOException {
//			
//			//Gets a refernce3 of your cloud storage bucket 
//Bucket bucket= StorageClient.getInstance().bucket();
//			
//			//Generates a uniquer file name 
//			
//			String fileName= UUID.randomUUID().toString()+".jpg";
//			
//			//        // Reads the image file from the specified path, uploads it to Cloud Storage with the generated filename, and returns a Blob object.
//
//			Blob blob= bucket.create(fileName,Files.readAllBytes(Paths.get(filepath)));
//			
//
//			// Returns the public URL of the uploaded image, which can be used to access the image.
//			return blob.getMediaLink();
//			
//		}
//		
//	
//		
//		public static byte[] downloadImage(String fileName) throws IOException {
//			
//			
//			initializeFireBase();
//			
//			Bucket  bucket = StorageClient.getInstance().bucket();
//			
//			Blob blob= bucket.get(fileName);
//			
//			if(blob!=null) {
//				
//				return blob.getContent();
//			} else {
//				
//				return null;
//			}
//			
//		}
//	
//
//
//
//    @Bean
//    public FirebaseApp firebaseApp() throws IOException {
//        FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase-adminsdk.json"); //Path to your JSON file.
//
//        FirebaseOptions options = FirebaseOptions.builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setStorageBucket("YOUR_STORAGE_BUCKET_NAME") // Replace with your bucket name.
//                .build();
//
//        return FirebaseApp.initializeApp(options);
//    }
//}
