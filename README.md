# ClickableTextRenderer Add-on for Vaadin 7

This is a Renderer for Grid in Vaadin 7.

It allows text in a cell to be rendered as clickable link. The typical use-case
is for **drill-down** within the Grid.

## Usage

The renderer inherits from `ClickableRenderer` so the use is the same.

Nevertheless, here's a taste:

Suppose your Grid has a column named "city". Then the renderer may be used
like this:

```java
ClickableRenderer.RendererClickListener myListener
        = (ClickableRenderer.RendererClickEvent event) -> {
            // Do something here. Use event.getItemId() to figure out
            // which row was clicked.
        };

grid.getColumn("city").setRenderer(new ClickableTextRenderer(myListener));
```

For a complete demo see the demo project (under `/demo` in this project).
