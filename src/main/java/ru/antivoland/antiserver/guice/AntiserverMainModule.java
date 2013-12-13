/*
 * @author antivoland
 */
package ru.antivoland.antiserver.guice;

import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import ru.antivoland.antiserver.res.StatusRes;

import java.util.HashMap;
import java.util.Map;

public class AntiserverMainModule extends ServletModule {
    @Override
    protected void configureServlets() {
        bind(StatusRes.class).in(Scopes.SINGLETON);

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        jacksonJsonProvider.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        bind(JacksonJsonProvider.class).toInstance(jacksonJsonProvider);

        bind(GuiceContainer.class).in(Scopes.SINGLETON);
        Map<String, String> params = new HashMap<String, String>();
        params.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
        serve("/*").with(GuiceContainer.class, params);
    }
}
