package webAppClient.client;

/**
 * Created with IntelliJ IDEA.
 * User: bqnieves
 * Date: 4/12/13
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.Arrays;
import java.util.List;

public class chatView implements EntryPoint {

    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    private final Messages messages = GWT.create(Messages.class);

    /**
     * The list of data to display.
     */
    private static final List<String> DAYS = Arrays.asList("Sunday", "Monday",
            "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");

    @Override
    public void onModuleLoad() {
        String nick = "pepe";
        Label label = new Label("Hello "+nick+"!!!");

        final Button sendButton = new Button(messages.sendButton());
        sendButton.getElement().setId("sendButton");
        sendButton.getElement().setClassName("sendButton");

        sendButton.addClickHandler(new ClickHandler() {


            @Override
            public void onClick(ClickEvent event) {
                Window.alert("Hello, again");
            }
        });

        RootPanel.get().add(label);
        RootPanel.get().add(sendButton);
    }
}
