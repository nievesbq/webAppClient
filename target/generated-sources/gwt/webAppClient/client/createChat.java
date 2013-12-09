package webAppClient.client;

import com.google.gwt.cell.client.TextCell;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;
import java.util.List;


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

                 new Get(rp,chatList).getMessages();

                if(chatList!=null){
                cl.setRowCount(chatList.size(), true);
                cl.setRowData(0, chatList);
                }

                panel.setVerticalScrollPosition(panel.getMaximumVerticalScrollPosition() - 1);
            }

        };

        // Schedule the timer to run once every second.
        t.scheduleRepeating(1000);



    }

}
