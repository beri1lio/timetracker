package com.timetracker.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class LogoTag extends SimpleTagSupport {
    private String image;

    public void setImage(String image) {
        this.image = image;
    }

    public void doTag() throws IOException {
        StringBuilder logoHtml = new StringBuilder();
        if (image != null) {
            logoHtml.append("<img src='")
                    .append(image)
                    .append("' alt='' width='30' height='24'")
                    .append("class='d-inline-block align-top'>");
        }
        JspWriter out = getJspContext().getOut();
        out.println(logoHtml);
    }
}