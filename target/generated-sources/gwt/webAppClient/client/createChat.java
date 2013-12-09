package webAppClient.client;

import com.google.gwt.cell.client.TextCell;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.user.client.Cookies;


/**
 * Created with IntelliJ IDEA.
 * User: montero
 * Date: 4/12/13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class createChat {

    int count = 0;
    List<String> chatList = new ArrayList<String>();
    final TextBox message = new TextBox();
    final TextCell textCell = new TextCell();
    final CellList<String> cl = new CellList<String>(textCell);
    final VerticalPanel vp=new VerticalPanel();
    final HorizontalPanel hp=new HorizontalPanel();
    final ScrollPanel panel = new ScrollPanel();



    public void run(final RootPanel rp, final String nick){

        final int  seqNumber;
        if(Cookies.getCookie(nick)==null)
            Cookies.setCookie(nick, ""+0);


        cl.setPageSize(500);

        final Button sendMessage = new Button("sendMessage", new ClickHandler() {

            public void onClick(ClickEvent event) {




                if(!message.getText().equals("")){
                   /* cl.setRowCount(count++, true);
                    chatList.add(message.getText());
                    cl.setRowData(count, chatList);

                    panel.setVerticalScrollPosition(panel.getMaximumVerticalScrollPosition() - 1);*/
                    new Post().postJson("http://172.16.100.125:8080/chat-kata/api/chat",nick.toString(),message.getText());
                    message.setText("");

                }
            }

        });

        rp.get("mainDiv2").setVisible(true);
        message.getElement().setAttribute("placeholder","Introduce your message");
        message.getElement().setAttribute("id","message");

        cl.getElement().setAttribute("id","chatBox");


        sendMessage.getElement().setAttribute("id", "sendMessage");
        sendMessage.setText("Send");

        vp.getElement().setAttribute("id", "verticalPanel");

        panel.getElement().setAttribute("id","scroller");

        hp.add(message);
        hp.add(sendMessage);
        panel.add(cl);
        vp.add(panel);

        //vp.add(cl);
        vp.add(hp);
        rp.get("mainDiv2").add(vp);


        Timer t = new Timer() {
            @Override
            public void run() {

                 //new Get(rp,chatList).getMessages();
                getMessages(nick);

                if(chatList!=null && Integer.parseInt(Cookies.getCookie(nick))<chatList.size()){
                cl.setRowCount(chatList.size()+1, true);
                cl.setRowData(Integer.parseInt(Cookies.getCookie(nick)), chatList.subList(Integer.parseInt(Cookies.getCookie(nick)),chatList.size()));
                panel.setVerticalScrollPosition(panel.getMaximumVerticalScrollPosition() - 1);
                    Cookies.setCookie(nick, ""+chatList.size());

                }
            }

        };

        // Schedule the timer to run once every second.
        t.scheduleRepeating(1000);



    }
    public void getMessages(final String nick){

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
                        processResponse(serverResponse,nick);

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
    private List<String> processResponse(IResponse serverResponse, String nick) {
        List<String> listado=new ArrayList<String>();
        List<IChatMessage>chatMessageList=serverResponse.getMessages();
        serverResponse.getNextSeq();
        for(IChatMessage message : chatMessageList){
            listado.add(message.getNick()+": " +message.getMessage());
        }
        chatList=listado;
        return listado;

}

    private IResponse decodeJSON(String json) {
        IResponseFactory factory = GWT.create(IResponseFactory.class);
        AutoBean<IResponse> bean = AutoBeanCodex.decode(factory, IResponse.class, json);
        return bean.as();
    }


}
