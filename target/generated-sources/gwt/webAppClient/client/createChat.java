package webAppClient.client;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.Set;

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
        rp.get("mainDiv2").setVisible(true);
        final TextBox message = new TextBox();
        final Button sendMessage= new Button();
        final VerticalPanel vp=new VerticalPanel();

        /*final CellList<Item>  cl=new CellList<Item>(new Cell<Item>() {
            public boolean dependsOnSelection() {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public Set<String> getConsumedEvents() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public boolean handlesSelection() {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public boolean isEditing(Context context, Element element, Item item) {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public void onBrowserEvent(Context context, Element element, Item item, NativeEvent nativeEvent, ValueUpdater<Item> itemValueUpdater) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void render(Context context, Item item, SafeHtmlBuilder safeHtmlBuilder) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public boolean resetFocus(Context context, Element element, Item item) {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public void setValue(Context context, Element element, Item item) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });*/
        rp.get("chatBox").add(vp);
        rp.get("message").add(message);
        rp.get("sendMessage").add(sendMessage);

    }


}
