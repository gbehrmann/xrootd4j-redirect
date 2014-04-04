package org.dcache.xrootd.plugins.redirect;

import java.util.Properties;

import org.dcache.xrootd.plugins.ChannelHandlerProvider;
import org.dcache.xrootd.plugins.ChannelHandlerFactory;

public class RedirectPluginProvider implements ChannelHandlerProvider
{
    @Override
    public ChannelHandlerFactory
        createFactory(String plugin, Properties properties)
    {
        if (RedirectPluginFactory.hasName(plugin)) {
            return new RedirectPluginFactory(properties);
        }
        return null;
    }
}
