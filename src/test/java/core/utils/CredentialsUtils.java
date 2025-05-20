package core.utils;

import java.io.*;
import java.util.Properties;

public class CredentialsUtils {
    private static final String FILE_PATH = "src/test/resources/credentials.properties";
    private static Properties props = new Properties();

    static {
        loadCredentials();
    }

    private static void loadCredentials() {
        try (InputStream input = new FileInputStream(FILE_PATH)) {
            props.load(input);
        } catch (IOException e) {
            System.out.println("No credentials file found.");
        }
    }

    public static void saveCredential(String key, String value) {
        props.setProperty(key, value);
        try (OutputStream output = new FileOutputStream(FILE_PATH)) {
            props.store(output, "User Credentials");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCredential(String key) {
        return props.getProperty(key);
    }

    public static boolean hasCredentials() {
        return props.containsKey("email") && props.containsKey("password");
    }
}
