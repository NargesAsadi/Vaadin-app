package com.mycompany;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {


    int i = 0;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        FormLayout layout = new FormLayout();
        Label label = new Label("Please Login");
        TextField usernameTextField = new TextField();
        usernameTextField.setCaption("Username*");
        PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Password*");
        Button button = new Button("Login");
        Label comment = new Label();

        layout.addComponents(label, usernameTextField, passwordField, button, comment);
        setContent(layout);

        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {

                int i = clicked();
                if (usernameTextField.isEmpty()) {
                    usernameTextField.setComponentError(new UserError("Username is required"));
                    comment.setVisible(false);
                }
                if (passwordField.isEmpty()) {
                    passwordField.setComponentError(new UserError("Password is required"));
                    comment.setVisible(false);
                }
                if (!passwordField.isEmpty() && !usernameTextField.isEmpty()) {
                    usernameTextField.setComponentError(null);
                    passwordField.setComponentError(null);
                    Employee employee = new Employee();
                    employee.setUsername(usernameTextField.getValue());
                    employee.setPassword(passwordField.getValue());
                    comment.setVisible(true);
                    comment.setCaption("You clicked " + i + " time(s) on login button!");
                }

            }
        });
    }

    private int clicked() {
        i += 1;
        return i;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
