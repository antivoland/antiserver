/*
 * @author antivoland
 */
package ru.antivoland.antiserver.res;

import ru.antivoland.antiserver.App;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/status")
public class StatusRes {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return App.runMode.name();
    }
}
