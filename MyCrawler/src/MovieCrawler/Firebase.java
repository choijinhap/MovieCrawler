package MovieCrawler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class Firebase {
	InputStream serviceAccount;
	public Firebase(String serviceAccountPath) throws Exception{
	this.serviceAccount = new FileInputStream(serviceAccountPath);
	GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
	FirebaseOptions options = new FirebaseOptions.Builder()
	    .setCredentials(credentials)
	    .build();
	FirebaseApp.initializeApp(options);

	
	}
	
	public void push() throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		DocumentReference docref=db.collection("naver").document("doc");
		Map<String,Object> data=new HashMap<>();
		data.put("a", "sada");
		data.put("s", "vbb");
		data.put("d", "asa");
		ApiFuture<WriteResult> result=docref.set(data);
		System.out.println("Update time : " + result.get().getUpdateTime());
	}
}
