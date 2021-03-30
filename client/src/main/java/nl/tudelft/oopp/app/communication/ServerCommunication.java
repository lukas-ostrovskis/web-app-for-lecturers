package nl.tudelft.oopp.app.communication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.Alert;
import nl.tudelft.oopp.app.data.User;
import nl.tudelft.oopp.app.views.MainView;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServerCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new room on the server.
     * @return ID of the created room.
     */
    public static String createRoom(String password) throws RoomNotAddedException {

        HttpRequest request = HttpRequest.newBuilder().GET()
                .uri(URI.create(String.format("http://localhost:8080/room/create?userId=%s&password=%s",
                        MainView.getUser().getId(), password)))
                .build();

        String response = sendRequest(request);

        if (response == null) {
            throw new RoomNotAddedException("Server returned null, meaning room wasn't added.");
        }

        return response;
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
            System.out.println("Status: " + response.statusCode() + " from joinRoom method.");
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
            return new User(null, null);
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode() + " from findUsers method.");
        }
        System.out.println(response.body());
        return gson.fromJson(response.body(), User.class);
    }

    /**
     * Method for finding rooms for the student user.
     * @param user to be added
     * @return the response of the body to communicate between the server and the client
     */
    public static User addUser(User user, String password) throws UserNotAddedException {

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .uri(URI.create("http://localhost:8080/user/add?password=" + password))
                .build();

        String response = sendRequest(request);
        if (response == null) {
            throw new UserNotAddedException("User not added, password may be wrong.");
        }

        return gson.fromJson(response, User.class);
    }

    public static void deleteRoom() throws RoomNotDeletedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET().uri(URI.create(
                        String.format("http://localhost:8080/room/delete?userId=%s",
                                MainView.getUser().getId())))
                .build();

        String response = sendRequest(request);
        if (response == null) {
            throw new RoomNotDeletedException("Room not deleted.");
        }
    }

    /**
     * Helper method for sending requests.
     * @param request to be sent
     * @return request body if nonempty, null if empty
     */
    public static String sendRequest(HttpRequest request) {
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        System.out.println(" < Server response: " + response.body());
        return response.body().equals("") ? null : response.body();
    }

    /**
     * Exception indicating miscommunication when adding user server-side.
     */
    public static class UserNotAddedException extends Exception {
        public UserNotAddedException(String message) {
            super(message);
        }
    }

    /**
     * Exception indicating miscommunication when adding room server-side.
     */
    public static class RoomNotAddedException extends Exception {
        public RoomNotAddedException(String message) {
            super(message);
        }
    }

    /**
     * Exception indicating miscommunication when deleting room server-side.
     */
    public static class RoomNotDeletedException extends Exception {
        public RoomNotDeletedException(String message) {
            super(message);
        }
    }
}
