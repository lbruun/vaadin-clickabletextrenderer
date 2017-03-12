package org.vaadin.community.addon.renderers.demo;


import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ClickableRenderer;
import java.util.Locale;
import org.vaadin.community.addon.renderers.AbstractClickableTextConverter;
import org.vaadin.community.addon.renderers.ClickableTextRenderer;
import org.vaadin.community.addon.renderers.ClickableTextRendererAdv;
import org.vaadin.community.addon.renderers.client.ClickableText;

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

        
        // Use the simple form of the Clickable Text Renderer on 
        // column "company". 
        grid.getColumn("company").setRenderer(new ClickableTextRenderer(getCompanyClickListener()));

        // Use the advanced form of the Clickable Text Renderer on 
        // column "city". This will require a converter where the PRESENTATION
        // class is of type ClickableText.
        grid.getColumn("city").setRenderer(new ClickableTextRendererAdv(getCityClickListener()),
                new ClickableTextConverter());
        
        layout.addComponent(grid);
        setContent(layout);
    }

    /**
     * Listener which determines what happens when a city is clicked upon.
     */
    private ClickableRenderer.RendererClickListener getCityClickListener() {
        return new ClickableRenderer.RendererClickListener() {
            @Override
            public void click(ClickableRenderer.RendererClickEvent event) {
                DemoPerson rowClicked = (DemoPerson) event.getItemId();

                Notification.show("You clicked",
                        "A city : " + rowClicked.getCity(),
                        Notification.Type.HUMANIZED_MESSAGE);
            }
        };
    }
    
    /**
     * Listener which determines what happens when a company is clicked upon.
     */
    private ClickableRenderer.RendererClickListener getCompanyClickListener() {
        return new ClickableRenderer.RendererClickListener() {
            @Override
            public void click(ClickableRenderer.RendererClickEvent event) {
                DemoPerson rowClicked = (DemoPerson) event.getItemId();

                Notification.show("You clicked",
                        "A company : " + rowClicked.getCompany(),
                        Notification.Type.HUMANIZED_MESSAGE);
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    /**
     * Converts column value into ClickableText for use with the advanced form 
     * of the renderer.
     */
    private static class ClickableTextConverter extends AbstractClickableTextConverter {
        @Override
        public ClickableText convertToPresentation(String value, Class<? extends ClickableText> targetType, Locale locale) throws ConversionException {
            ClickableText ct = new ClickableText();
            ct.description = "Will take you to " + value;
            ct.value = value + " " + FontAwesome.EXTERNAL_LINK.getHtml();
            ct.isHTML = true;
            return ct;
        }
    }
    
    
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

}
