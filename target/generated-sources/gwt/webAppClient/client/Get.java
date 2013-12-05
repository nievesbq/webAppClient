package webAppClient.client;

import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: montero
 * Date: 5/12/13
 * Time: 17:02
 * To change this template use File | Settings | File Templates.
 */
public class Get {


    public List<String> getMessages(){
       List<String> messagesList=new ArrayList<String>();
        String url = "http://www.myserver.com/getData?type=3";
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));

        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    // Couldn't connect to server (could be timeout, SOP violation, etc.)
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        // Process the response in response.getText()
                       response.getText();
                        JSONObject jsonObject=new JSONObject();


                    } else {
                        // Handle the error.  Can get the status text from response.getStatusText()
                    }
                }
            });
        } catch (RequestException e) {
            // Couldn't connect to server
        }

       return messagesList;
    }
}
