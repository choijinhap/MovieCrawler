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
	FirebaseApp fa;
	public Firebase(String serviceAccountPath) throws Exception{
	this.serviceAccount = new FileInputStream(serviceAccountPath);
	GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
	FirebaseOptions options = new FirebaseOptions.Builder()
	    .setCredentials(credentials)
	    .build();
	fa=FirebaseApp.initializeApp(options);
	
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
	public void push(NaverCrawler naver) throws Exception {
		Firestore db = FirestoreClient.getFirestore();
		Map<String,Object> pd=new HashMap<>();
		pd.put("네이",123);
		pd.put("플레이스토어", 1234);
		for(int i=0;i<naver.size;i++) {
		DocumentReference docref=db.collection("movie").document(naver.getTitle()[i]);
		Map<String,Object> data=new HashMap<>();
		data.put("rank", i+1);
		data.put("title", naver.getTitle()[i]);
		data.put("imageUri", naver.getImgURI()[i]);
		data.put("genre", naver.getGenre()[i]);
		data.put("summary", naver.getContent()[i]);
		data.put("price",pd);
		ApiFuture<WriteResult> result=docref.set(data);
		result.get();
		}
		db.close();
	}
	public void end() {
		fa.delete();
	}
}
