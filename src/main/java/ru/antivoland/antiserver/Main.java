/*
 * @author antivoland
 */
package ru.antivoland.antiserver;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Scopes;
import com.google.inject.servlet.GuiceFilter;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import ru.antivoland.antiserver.guice.AntiserverMainModule;
import ru.antivoland.antiserver.servlet.InvalidRequestServlet;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception {
        App.config = new PropertiesConfiguration("conf/antiserver.properties");
        App.runMode = RunMode.valueOf(App.config.getString("runMode"));
        App.startMode = RunMode.valueOf(App.config.getString("runMode"));

        App.injector = Guice.createInjector(new AntiserverMainModule(), new AbstractModule() {
            @Override
            protected void configure() {
                binder().requireExplicitBindings();
                bind(GuiceFilter.class).in(Scopes.SINGLETON);
            }
        });

        ServletContextHandler handler = new ServletContextHandler();
        handler.setSessionHandler(new SessionHandler());
        handler.setContextPath("/");
        handler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
        handler.addServlet(InvalidRequestServlet.class, "/*");

        Server server = new Server(App.config.getInt("server.port"));
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
