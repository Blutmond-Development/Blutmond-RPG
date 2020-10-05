package de.blutmondgilde.blutmondrpg.gui.widgetlayouts;

import com.feed_the_beast.mods.ftbguilibrary.widget.Panel;
import com.feed_the_beast.mods.ftbguilibrary.widget.Widget;
import com.feed_the_beast.mods.ftbguilibrary.widget.WidgetLayout;
import java.util.Iterator;

public class FillVertical implements WidgetLayout {
    private final int pre, spacing, post, xStart, xSpacing;

    public FillVertical(int pre, int spacing, int post, int xStart, int xSpacing) {
        this.pre      = pre;
        this.spacing  = spacing;
        this.post     = post;
        this.xStart   = xStart;
        this.xSpacing = xSpacing;
    }

    @Override
    public int align(Panel panel) {
        int currentX = this.xStart;
        int i = this.pre;
        if (!panel.widgets.isEmpty()) {
            Widget widget;
            for (Iterator var3 = panel.widgets.iterator(); var3.hasNext(); i += widget.height + this.spacing) {
                widget = (Widget) var3.next();
                if (panel.height < i + widget.height) {
                    i        = this.pre;
                    currentX = currentX + widget.width + this.xSpacing;
                }

                widget.setX(currentX);
                widget.setY(i);
            }

            i -= this.spacing;
        }

        return i + this.post;
    }
}
