package webAppClient.client;

import com.google.gwt.http.client.*;
import com.google.gwt.user.client.Window;


public class Post {

    public void postJson(String url, String nick, String message) {

        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);
        builder.setHeader("Content-type", "application/json");

        StringBuilder sb = new StringBuilder();
        String json = "";

        json = createJson(nick, message);
        sb.append(json);


        try {
            Request response = builder.sendRequest(sb.toString(), new RequestCallback() {

                public void onError(Request request, Throwable exception) {
                    Window.alert("Error sending the message");
                }

                public void onResponseReceived(Request request, Response response) {
                    if (response.getStatusCode() != 200) {
                        Window.alert("Error in the message format" + response.getStatusCode());
                    }
                }
            });
        } catch (RequestException e) {
        }
    }


    public String createJson(String nick, String message) {
        return "{\"nick\": \"" + nick + "\", \"message\": \"" + message + "\"}";
    }
}

