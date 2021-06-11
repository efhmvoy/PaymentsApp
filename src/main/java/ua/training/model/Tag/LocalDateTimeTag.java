package ua.training.model.Tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTag extends SimpleTagSupport {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LocalDateTime date;

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        if (date != null) {
            out.println(date.format(formatter));
        } else {
            out.println("");
        }
    }
}
