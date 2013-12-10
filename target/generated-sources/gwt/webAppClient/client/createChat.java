package webAppClient.client;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import webAppClient.client.interfaces.IChatMessage;
import webAppClient.client.interfaces.IResponse;
import webAppClient.client.interfaces.IResponseFactory;

import java.util.ArrayList;
import java.util.List;


public class createChat {

    List<String> chatList = new ArrayList<String>();
    final TextBox message = new TextBox();
    final TextCell textCell = new TextCell();
    final CellList<String> cl = new CellList<String>(textCell);
    final VerticalPanel vp = new VerticalPanel();
    final HorizontalPanel hp = new HorizontalPanel();
    final ScrollPanel panel = new ScrollPanel();
    final String SERVERURL = "http://172.16.100.125:8080/chat-kata/api/chat";
    final String URLGET = "http://172.16.100.125:8080/chat-kata/api/chat/?seq=0";


    public void run(final RootPanel rp, final String nick) {

        if (Cookies.getCookie(nick) == null)
            Cookies.setCookie(nick, "" + 0);


        cl.setPageSize(500);

        final Button sendMessage = new Button("sendMessage", new ClickHandler() {

            public void onClick(ClickEvent event) {

                if (!message.getText().equals("")) {
                    new Post().postJson(SERVERURL, nick.toString(), message.getText());
                    message.setText("");

                }
            }

        });

        rp.get("mainDiv2").setVisible(true);
        message.getElement().setAttribute("placeholder", "Introduce your message");
        message.getElement().setAttribute("id", "message");

        cl.getElement().setAttribute("id", "chatBox");


        sendMessage.getElement().setAttribute("id", "sendMessage");
        sendMessage.setText("Send");

        vp.getElement().setAttribute("id", "verticalPanel");
        hp.getElement().setAttribute("id", "horizontalPanel");

        panel.getElement().setAttribute("id", "scroller");

        hp.add(message);
        hp.add(sendMessage);
        panel.add(cl);
        vp.add(panel);

        vp.add(hp);
        rp.get("mainDiv2").add(vp);


        Timer t = new Timer() {
            @Override
            public void run() {
                getMessages();

                if (chatList != null && Integer.parseInt(Cookies.getCookie(nick)) < chatList.size()) {
                    cl.setRowCount(chatList.size() + 1, true);
                    cl.setRowData(Integer.parseInt(Cookies.getCookie(nick)), chatList.subList(Integer.parseInt(Cookies.getCookie(nick)), chatList.size()));
                    panel.setVerticalScrollPosition(panel.getMaximumVerticalScrollPosition() - 1);
                    Cookies.setCookie(nick, "" + chatList.size());

                }
            }

        };
        t.scheduleRepeating(1000);


    }

    public void getMessages() {

        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(URLGET));


        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        IResponse serverResponse = decodeJSON(response.getText());
                        processResponse(serverResponse);

                    } else {

                        Window.alert("Messages couldn't  been recieved");
                    }
                }
            });
        } catch (RequestException e) {
            // Couldn't connect to server
        }

    }

    private void processResponse(IResponse serverResponse) {
        List<String> stringList = new ArrayList<String>();
        List<IChatMessage> chatMessageList = serverResponse.getMessages();
        serverResponse.getNextSeq();
        for (IChatMessage message : chatMessageList) {
            stringList.add(message.getNick() + ": " + message.getMessage());
        }
        chatList = stringList;
    }

    private IResponse decodeJSON(String json) {
        IResponseFactory factory = GWT.create(IResponseFactory.class);
        AutoBean<IResponse> bean = AutoBeanCodex.decode(factory, IResponse.class, json);
        return bean.as();
    }


}
