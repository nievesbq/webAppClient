package webAppClient.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.ui.RootPanel;
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
    final RootPanel rp;
    List<String> messagesList;

    public Get(RootPanel rp,List<String> messagesList) {
        this.rp=rp;
        this.messagesList=messagesList;
    }

    public void getMessages(){

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
                        IResponse serverResponse = decodeJSON(response.getText());
                        messagesList=processResponse(serverResponse);

                       //processGetResponse(json);


                    } else {
                        // Handle the error.  Can get the status text from response.getStatusText()
                    }
                }
            });
        } catch (RequestException e) {
            // Couldn't connect to server
        }

    }

    private List<String> processResponse(IResponse serverResponse) {
               List<String> listado=new ArrayList<String>();
         List<IChatMessage>chatMessageList=serverResponse.getMessages();
         int seqNumber=serverResponse.getNextSeq();
        for(IChatMessage message : chatMessageList){
            listado.add(message.getNick()+": " +message.getMessage());
        }


        /*ChatState chatState = ChatState.getChatState();
        chatState.setNextSeq(serverResponse.getNextSeq());

        List<IChatMessage> messages = serverResponse.getMessages();
        for(IChatMessage message : messages){
            ChatState.getChatState().getMessages().add(new ChatMessage(message.getNick(), message.getMessage()));
        }*/
        return listado;
    }

    private IResponse decodeJSON(String json) {
        IResponseFactory factory = GWT.create(IResponseFactory.class);
        AutoBean<IResponse> bean = AutoBeanCodex.decode(factory, IResponse.class, json);
        return bean.as();
    }

   /* public void processGetResponse(String getResponse) {
        ServerResponseAutoBeanFactory serverResponseAutoBeanFactory = GWT.create(ServerResponseAutoBeanFactory.class);
        AutoBean<IServerResponse> autoBean = AutoBeanCodex.decode(serverResponseAutoBeanFactory, IServerResponse.class,
                getResponse);
        IServerResponse serverResponse = autoBean.as();
        //lastSeq = serverResponse.getNextSeq();
        List<Item> messages = serverResponse.getMessages();
        //chatView.addToMessageList(serverResponse.getMessages());
    }*/



}
