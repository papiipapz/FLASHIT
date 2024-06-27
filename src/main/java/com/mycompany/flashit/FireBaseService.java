package com.mycompany.flashit;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class FireBaseService {

    private static Firestore db;

    // Private constructor to prevent instantiation
    private FireBaseService() {}

    public static void initialize() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) { // Check if FirebaseApp has already been initialized
            // Path to your service account key
            String serviceAccountPath = "src/main/resources/key.json";

            FileInputStream serviceAccount = new FileInputStream(serviceAccountPath);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://flashit-6319d.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println("Firebase initialized successfully!");
        } else {
            System.out.println("FirebaseApp already initialized!");
        }

        // Initialize Firestore
        db = FirestoreClient.getFirestore();
    }

    public static Firestore getFirestore() {
        return db;
    }
}
