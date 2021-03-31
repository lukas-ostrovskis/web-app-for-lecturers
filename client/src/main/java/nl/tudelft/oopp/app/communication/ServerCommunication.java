package nl.tudelft.oopp.app.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nl.tudelft.oopp.app.data.Question;
import nl.tudelft.oopp.app.data.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;



public class ServerCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    private static Gson gson = new Gson();
    /**
     * Creates a new room on the server.
     * @return An id of the created room.
     * @throws Exception if communication with the server fails.
     */
    public static String getRoomId() {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/room/create")).build();
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
     * Fetches all questions with the roomId provided
     * @param roomId
     * @return a list of questions with a certain roomId
     * @throws IOException if communication with the server fails
     */

    public static List<Question> fetchQuestionsByRoomId(String roomId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/question/getAllByRoomId/" + roomId)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // empty list on error
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }

        return mapper.readValue(response.body(), new TypeReference<List<Question>>(){});
    }

    /**
     * Upvotes the question with the questionId
     * @param questionId
     */

    public static void upvoteQuestionById(String questionId, String userId) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/question/upvote?questionId=" + questionId + "&userId=" + userId)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
    }

    /**
     * Downvotes the question with the questionId
     * @param questionId
     */

    public static void downvoteQuestionById(String questionId, String userId) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/question/downvote?questionId=" + questionId + "&userId=" + userId)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
    }

    /**
     * Sends a question to the server
     * @param question
     */

    public static void askQuestion(Question question){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String requestBody = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(question);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/question/add/"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> response = null;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (response.statusCode() != 200) {
                System.out.println("Status: " + response.statusCode());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a request to the server to delete the question with the questionId provided
     * @param questionId
     */

    public static void deleteQuestion(String questionId){
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/question/delete/" + questionId))
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();
            HttpResponse<String> response = null;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (response.statusCode() != 200) {
                System.out.println("Status: " + response.statusCode());
            }
    }

    /**
     * Toggles the status of the question
     * @param questionId
     */

    public static void toggleStatus(String questionId) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/question/toggleStatus/" + questionId)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
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

    public static List<User> findUsers(String email, String password) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/user/searchOrAdd?email=" + email + "&password=" + password)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // empty list on error
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }

        return gson.fromJson(response.body(), new TypeToken<List<User>>(){}.getType());
    }
}
