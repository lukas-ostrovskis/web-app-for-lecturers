package nl.tudelft.oopp.app.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nl.tudelft.oopp.app.data.Question;
import nl.tudelft.oopp.app.data.User;
import nl.tudelft.oopp.app.data.Quiz;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;



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
        HttpRequest request = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString("")).uri(URI.create("http://localhost:8080/question/upvote?questionId=" + questionId + "&userId=" + userId)).build();
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
        HttpRequest request = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString("")).uri(URI.create("http://localhost:8080/question/downvote?questionId=" + questionId + "&userId=" + userId)).build();
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
        System.out.println(question.getOwnerName());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String requestBody = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(question);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/question/add/"))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
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
                    .DELETE()
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
        HttpRequest request = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString("")).uri(URI.create("http://localhost:8080/question/toggleStatus/" + questionId)).build();
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

    /**
     * Adds a Quiz to the database.
     *
     * @param quiz the quiz.
     * @return "Quiz Added Successfully" if the quiz was added.
     */
    public static String addQuiz(Quiz quiz) {
        String requestBody = gson.toJson(quiz);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/quiz/add"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
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
        return response.body();
    }

    /**
     * Gets all quizzes in a certain room.
     *
     * @param roomId the room id.
     * @return a List of Quizzes.
     */
    public static List<Quiz> getAllQuizzes(String roomId) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/quiz/getAll/" + roomId)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }

        return gson.fromJson(response.body(), new TypeToken<List<Quiz>>(){}.getType());
    }

    /**
     * Submits an answer to an open Quiz in a certain room.
     *
     * @param roomId the room id.
     * @param answer the answer.
     * @return "Answer recorded!" if answer gets submitted.
     */
    public static String submitQuizAnswer(String roomId, String answer) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/quiz/answer" + roomId))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(answer))
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
        return response.body();
    }

    /**
     * Toggle open status of a quiz by its quizId.
     *
     * @param quizId the quiz id.
     * @return the Quiz.
     */
    public static Quiz quizToggleOpenStatus(String quizId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/quiz/toggleOpenStatus"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(quizId))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return gson.fromJson(response.body(), new TypeToken<Quiz>(){}.getType());
    }

    /**
     * Toggle answerDistributionReady status of a quiz by its quizId.
     *
     * @param quizId the quiz id.
     * @return the Quiz.
     */
    public static Quiz quizToggleAnswerDistributionReadyStatus(String quizId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/quiz/toggleAnswerDistributionReadyStatus"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(quizId))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return gson.fromJson(response.body(), new TypeToken<Quiz>(){}.getType());
    }

    /**
     * Deletes a quiz from server memory (if any exists).
     *
     * SHOULD BE CALLED WHEN THE ROOM IS CLOSED.
     *
     * @param roomId the room id.
     * @return the Quiz which was deleted.
     */
    public static Quiz deleteQuizFromServerMemory(String roomId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/quiz/deleteFromServerMemory" + roomId))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return gson.fromJson(response.body(), new TypeToken<Quiz>(){}.getType());
    }

    /**
     * Checks if there's an open quiz in the given room.
     *
     * @param roomId the room id.
     * @return a Quiz if there's one, null otherwise.
     */
    public static Quiz checkQuizOpen(String roomId) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/quiz/getOpen/" + roomId)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }

        return gson.fromJson(response.body(), new TypeToken<Quiz>(){}.getType());
    }

    /**
     * Checks if there's an answer distribution that is ready to publish in the given room.
     *
     * @param roomId the room id.
     * @return answerDistribution if it's ready, null otherwise.
     */
    public static Map<Character, Integer> checkAnswerDistributionReady(String roomId) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/quiz/answerDistribution/" + roomId)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }

        return gson.fromJson(response.body(), new TypeToken<Quiz>(){}.getType());
    }
}
