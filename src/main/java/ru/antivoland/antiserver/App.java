/*
 * @author antivoland
 */
package ru.antivoland.antiserver;

import com.google.inject.Injector;
import org.apache.commons.configuration.PropertiesConfiguration;

public class App {
    public static PropertiesConfiguration config;
    public static RunMode runMode;
    public static RunMode startMode;
    public static Injector injector;
}
