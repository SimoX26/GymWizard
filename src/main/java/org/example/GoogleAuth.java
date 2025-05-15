package org.example;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class GoogleAuth {
   // private static final String CREDENTIALS_FILE = "/credentials.json";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public static Userinfoplus authorizeAndGetUserInfo() throws Exception {
        InputStream in = GoogleAuth.class.getResourceAsStream("/credentials.json");
        if (in == null) {
            throw new RuntimeException("File credentials.json non trovato nel classpath!");
        }

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                clientSecrets,
                List.of("https://www.googleapis.com/auth/userinfo.email", "https://www.googleapis.com/auth/userinfo.profile")
        ).setAccessType("offline").build();

        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

        Oauth2 oauth2 = new Oauth2.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential
        ).setApplicationName("GymWizard").build();

        return oauth2.userinfo().get().execute();
    }
}
