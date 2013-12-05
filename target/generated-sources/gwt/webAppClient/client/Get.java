package webAppClient.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.http.client.*;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;

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
        String url = "http://172.16.100.125:8080/chat-kata/api/chat/?seq=0";
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));

        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    // Couldn't connect to server (could be timeout, SOP violation, etc.)
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        // Process the response in response.getText()
                       String json=response.getText();
                        //processGetResponse(json);



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

    public void processGetResponse(String getResponse) {
        ServerResponseAutoBeanFactory serverResponseAutoBeanFactory = GWT.create(ServerResponseAutoBeanFactory.class);
        AutoBean<IServerResponse> autoBean = AutoBeanCodex.decode(serverResponseAutoBeanFactory, IServerResponse.class,
                getResponse);
        IServerResponse serverResponse = autoBean.as();
        //lastSeq = serverResponse.getNextSeq();
        List<IChatMessage> messages = serverResponse.getMessages();
        //chatView.addToMessageList(serverResponse.getMessages());
    }
}
