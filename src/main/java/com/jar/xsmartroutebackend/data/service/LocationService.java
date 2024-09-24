package com.jar.xsmartroutebackend.data.service;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.jar.xsmartroutebackend.data.model.Location;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class LocationService {
    private final Firestore firestore;

    public LocationService() throws IOException {
        FileInputStream serviceAccountStream = new FileInputStream("src/main/resources/xsmartroute-adminskd.json");

        this.firestore = FirestoreOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                .build()
                .getService();
    }

    public void addLocation(Location location) {
        CollectionReference locations = firestore.collection("locations");
        locations.add(location);
    }

    public List<Location> getAllLocations() throws ExecutionException, InterruptedException {
        CollectionReference locations = firestore.collection("locations");
        ApiFuture<QuerySnapshot> future = locations.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents.stream()
                .map(doc -> doc.toObject(Location.class))
                .toList();
    }
}
