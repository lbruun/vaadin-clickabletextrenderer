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
cannot hoover over the link and see where it will take him. You can,
however, create a tooltip for the link.

The text is styled with `v-link` and this it why it will display as if it was
a real link.

## Advanced use

`ClickableTextRenderer` renders `String` input and doesn't allow HTML.
It is simple to use.

However, `ClickableTextRendererAdv` is more advanced (hence the name) and
can be used when HTML text rendering is required or when there's a need
to set the link's tooltip (or 'description' as is the Vaadin terminology).
Use of `ClickableTextRendererAdv` will always require a `Converter` because
it renders objects of type `ClickableText`, so this needs to be column's
Presentation type. It may seem cumbersome to have to convert to this
Presentation type but the advantage is that both the link text and the 
tooltip can depend on the value of the cell.

The demo project contains an example of usage of `ClickableTextRendererAdv`.

## What's the alternative ?

You can add an `ItemClickListener` to your Grid. This allows you to catch
when a row is clicked. This may be a simpler solution for you if you want to
enable some drill-down from your Grid. However beware of the following when 
using this method:

* There's no styling so is it obvious for your user that the row can be 
clicked ?
* What if you want different links in different columns? Of course from 
within your `ItemClickListener` you can figure out which column was clicked
but it all becomes somewhat muddy from a UX perspective.
* If your Grid has editing enabled then by default double-clicking a row
opens the editor. So single-clicking will take you somewhere else while
double-clicking will enter edit mode. This is not a very logical for
an end-user, IMHO.



