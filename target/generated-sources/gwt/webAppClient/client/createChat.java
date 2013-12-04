package webAppClient.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Created with IntelliJ IDEA.
 * User: montero
 * Date: 4/12/13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class createChat {


    public void run(RootPanel rp){
                            /*
        final TextBox nameField = new TextBox();
        nameField.getElement().setId("nameField");
        nameField.getElement().setAttribute("placeholder","Enter your username");
        rp.get("mainDiv2").add(nameField);
                                         */
        RootPanel.get("mainDiv2").setVisible(true);
        final TextBox message = new TextBox();
        final Button sendMessage= new Button();
        RootPanel.get("mainDiv2").add(message);
        RootPanel.get("mainDiv2").add(sendMessage);

    }


}
