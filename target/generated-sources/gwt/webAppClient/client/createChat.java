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


    public void run(RootPanel rp, String nick){

                            /*
        final TextBox nameField = new TextBox();
        nameField.getElement().setId("nameField");
        nameField.getElement().setAttribute("placeholder","Enter your username");
        rp.get("mainDiv2").add(nameField);
                                         */
        rp.get("mainDiv2").setVisible(true);
        final TextBox message = new TextBox();
        message.getElement().setAttribute("placeholder","Introduce your message");
        message.getElement().setAttribute("id","message");
        final TextCell textCell=new TextCell();
        final CellList<String> cl=new CellList<String>(textCell);
        cl.getElement().setAttribute("id","chatBox");
       // final  CellList<Item> cl=createList(te);
       // cl.getElement().setId("chatBox");
       /* final TextBox cb = new TextBox();
        cb.getElement().setAttribute("id","chatBox");*/
        final Button sendMessage= new Button();
        sendMessage.getElement().setAttribute("id", "sendMessage");
        sendMessage.setText("Send");
        final VerticalPanel vp=new VerticalPanel();
        vp.getElement().setAttribute("id","verticalPanel");
        final HorizontalPanel hp=new HorizontalPanel();

      List<Item> list=new ArrayList<Item>();
      list.add(new Item("Nombre","Blabalaba"));
     // cl.setRowCount(list.size(), true);
      //cl.setRowData(0,list);
        List<String> listado=new ArrayList<String>();
        listado.add("pruebas");

        cl.setRowCount(listado.size(),true);
        cl.setRowData(0,listado);



        hp.add(message);
        hp.add(sendMessage);
        vp.add(cl);
        vp.add(hp);



        rp.get("mainDiv2").add(vp);
       /* rp.get("mainDiv2").add(message);
        rp.get("mainDiv2").add(sendMessage);*/




        MyHandler2 handler = new MyHandler2(message.getText(),nick);
        sendMessage.addClickHandler(handler);


        Timer t = new Timer() {
            @Override
            public void run() {

                new Get().getMessages();
            }

        };

        // Schedule the timer to run once every second.
        t.schedule(1000);
        t.run();










    }




}

class MyHandler2 implements ClickHandler {
    private String message;
    private String nick;

    public MyHandler2(String message,String nick){
        this.message=message;
        this.nick=nick;
    }

    public void onClick(ClickEvent event) {

            new Post().postJson("http://172.16.100.125:8080/chat-kata/api/chat",nick,message);



    }

}


