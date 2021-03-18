package nl.tudelft.oopp.demo.communication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServerCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    /**
     * Creates a new room on the server.
     * @return An id of the created room.
     * @throws Exception if communication with the server fails.
     */
    public static String getRoomId() {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/room")).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response.body();
    }

    public static String joinRoom(String id) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/room/" + id)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response.body();
    }
}
