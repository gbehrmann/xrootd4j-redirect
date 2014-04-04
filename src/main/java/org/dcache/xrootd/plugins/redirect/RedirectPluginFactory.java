package org.dcache.xrootd.plugins.redirect;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.dcache.xrootd.plugins.ChannelHandlerFactory;

import com.google.common.base.Strings;
import org.jboss.netty.channel.ChannelHandler;

import static com.google.common.base.Preconditions.checkArgument;

public class RedirectPluginFactory implements ChannelHandlerFactory
{
    static final String NAME = "redirect";

    static final Set<String> ALTERNATIVE_NAMES =
        new HashSet<>(Arrays.asList(NAME));
    private final String host;
    private final int port;

    public RedirectPluginFactory(Properties properties)
    {
        String host = properties.getProperty("xrootd.redirect.host");
        String port = properties.getProperty("xrootd.redirect.port");

        checkArgument(!Strings.isNullOrEmpty(host), "xrootd.redirect.host is a required property");
        checkArgument(!Strings.isNullOrEmpty(port), "xrootd.redirect.port is a required property");

        this.port = Integer.parseInt(port);
        this.host = host;
    }

    static boolean hasName(String name)
    {
        return ALTERNATIVE_NAMES.contains(name);
    }

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public String getDescription()
    {
        return "xrootd4j redirect plugin";
    }

    @Override
    public ChannelHandler createHandler()
    {
        return new RedirectPlugin(host, port);
    }
}
