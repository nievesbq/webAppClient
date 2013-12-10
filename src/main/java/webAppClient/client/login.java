package webAppClient.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import webAppClient.shared.FieldVerifier;


public class login implements EntryPoint {

    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";


    public void onModuleLoad() {

        final Button sendButton = new Button("Send");
        sendButton.getElement().setId("sendButton");
        sendButton.getElement().setClassName("sendButton");

        final TextBox nameField = new TextBox();
        nameField.getElement().setId("nameField");
        nameField.getElement().setAttribute("placeholder", "Enter your username");

        final PasswordTextBox passField = new PasswordTextBox();
        passField.getElement().setId("passField");
        passField.getElement().setAttribute("placeholder", "Enter your password");

        final Label errorLabel = new Label();
        final Label nick = new Label();
        nick.setText("Log In");


        RootPanel.get("nameFieldContainer").add(nameField);
        RootPanel.get("passFieldContainer").add(passField);
        RootPanel.get("sendButtonContainer").add(sendButton);
        RootPanel.get("errorLabelContainer").add(errorLabel);
        RootPanel.get("nick").add(nick);
        RootPanel.get("mainDiv2").setVisible(false);
        class MyHandler implements ClickHandler {

            public void onClick(ClickEvent event) {
                if (!FieldVerifier.isValidName(nameField.getText())) {
                    errorLabel.setText("Please enter your username");
                    return;
                } else {
                    RootPanel.get("mainDiv").setVisible(false);
                    nick.setText("Welcome " + nameField.getText());
                    new createChat().run(RootPanel.get(), nameField.getText());
                }
            }


            private void sendNameToServer() {
                errorLabel.setText("");
                String textToServer = nameField.getText();
                if (!FieldVerifier.isValidName(textToServer)) {
                    errorLabel.setText("Please enter your username");
                    return;
                }
                RootLayoutPanel.get().clear();

            }
        }

        // Add a handler to send the name to the server
        MyHandler handler = new MyHandler();
        sendButton.addClickHandler(handler);
    }
}
