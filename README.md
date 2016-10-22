# ClickableTextRenderer Add-on for Vaadin 7

This is a Renderer for Grid in Vaadin 7.

It allows text in a cell to be rendered as a clickable link. The typical use-case
is for **drill-down** within the Grid.

## Usage

The renderer inherits from `ClickableRenderer` so the use is the same.

Nevertheless, here's a taste:

Suppose your Grid has a column named "city". Then the renderer may be used
like this:

```java
// Define what to do when clicked
ClickableRenderer.RendererClickListener myListener
        = (ClickableRenderer.RendererClickEvent event) -> {
            // Do something here. Use event.getItemId() to figure out
            // which row was clicked.
        };

// Add the renderer to some column
grid.getColumn("city").setRenderer(new ClickableTextRenderer(myListener));
```

For a complete demo see the demo project (under `/demo` in this project).

## It's not really a link

What the user sees walks and talks like a link, but it is not a link
in the normal HTML way, i.e. `<a href="...">text</a>`. This means the user
cannot hoover over the link and see where it will take him. Whether this
is good or bad is up to you to decide.

The text is styled with `link` and this it why it will display as if it was
a link.



