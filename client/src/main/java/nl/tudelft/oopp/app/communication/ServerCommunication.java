package nl.tudelft.oopp.app.communication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nl.tudelft.oopp.app.data.User;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServerCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    private static Gson gson = new Gson();
    /**
     * Creates a new room on the server.
     * @return An id of the created room.
     * @throws Exception if communication with the server fails.
     */
    public static String getRoomId(String userID) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/room/create?userId=" + userID)).build();
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

    /**
     * Allows to join into a room
     * @param id his id
     * @param user the user that is trying to enter
     * @return the response of the body to communicate between the server and the client
     */
    public static String joinRoom(String id, User user) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET().uri(URI.create("http://localhost:8080/room/" + id + "&user=" + user))
                .build();
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

        return response.body().equals("") ? null : response.body();
    }

    /**
     * 
     * @param email the E-Mail of the lecturer inputed in the textField
     * @param password the password of the lecturer inputed in the textField
     * @param role is to check what type of user is
     * @param roomId the id of the room
     * @return the response of the body to communicate between the server and the client
     */
    public static User findUsers(String email, String password, int role, String roomId) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/user/searchOrAdd?email=" + email + "&password=" + password + "&role=" + role + "&roomId=" + roomId)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return new User(null,null,null,null);
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        System.out.println(response.body());
        return gson.fromJson(response.body(), User.class);
    }

    /**
     * Method for finding rooms for the student user.
     * @param email is the email of the student that is inputed in the textField
     * @param roomId the id of the room they want to enter
     * @return the response of the body to communicate between the server and the client
     */
    public static User addUser(String email, String roomId) {

        HttpRequest request = HttpRequest.newBuilder()
                .GET().uri(URI.create(
                        String.format("http://localhost:8080/user/add?email=%s&roomid=%s", email, roomId)))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return new User(null,null,null,null);
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }

        System.out.println(response.body());
        return gson.fromJson(response.body(), User.class);
    }
}
