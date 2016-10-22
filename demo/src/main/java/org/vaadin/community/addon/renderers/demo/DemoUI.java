package org.vaadin.community.addon.renderers.demo;


import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ClickableRenderer;
import org.vaadin.community.addon.renderers.ClickableTextRenderer;

@Theme("demo")
@Title("LinkTextRenderer Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{


    @Override
    protected void init(VaadinRequest request) {

        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.setSizeFull();
        
        Grid grid = new Grid(DemoPerson.getDemoContainer());
        grid.setSizeFull();
        grid.setCaption("<b>Demo Grid</b>");
        grid.setCaptionAsHtml(true);
        grid.setEditorEnabled(false);
        grid.setColumnOrder(DemoPerson.getPropertyOrder());
        grid.setSelectionMode(Grid.SelectionMode.NONE);

        ClickableRenderer.RendererClickListener myListener
                = (ClickableRenderer.RendererClickEvent event) -> {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                };
        
        
        grid.getColumn("company").setRenderer(new ClickableTextRenderer(getCompanyClickListener()));
        grid.getColumn("city").setRenderer(new ClickableTextRenderer(getCityClickListener()));
        
        layout.addComponent(grid);
        setContent(layout);
    }

    private ClickableRenderer.RendererClickListener getCityClickListener() {
        return (ClickableRenderer.RendererClickEvent event) -> {
            DemoPerson rowClicked = (DemoPerson) event.getItemId();
            
            Notification.show("You clicked",
                    "A city : " + rowClicked.getCity(),
                    Notification.Type.HUMANIZED_MESSAGE);
        };
    }
    
    private ClickableRenderer.RendererClickListener getCompanyClickListener() {
        return (ClickableRenderer.RendererClickEvent event) -> {
            DemoPerson rowClicked = (DemoPerson) event.getItemId();
            
            Notification.show("You clicked",
                    "A company : " + rowClicked.getCompany(),
                    Notification.Type.HUMANIZED_MESSAGE);
        };
    }

    
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

}
