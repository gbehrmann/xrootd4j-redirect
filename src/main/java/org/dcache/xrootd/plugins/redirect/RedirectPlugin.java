package org.dcache.xrootd.plugins.redirect;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelDownstreamHandler;

import org.dcache.xrootd.protocol.XrootdProtocol;
import org.dcache.xrootd.protocol.messages.ErrorResponse;
import org.dcache.xrootd.protocol.messages.OpenRequest;
import org.dcache.xrootd.protocol.messages.RedirectResponse;

public class RedirectPlugin extends SimpleChannelDownstreamHandler
{
    private final String host;
    private final int port;

    public RedirectPlugin(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    @Override
    public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception
    {
        if (e.getMessage() instanceof ErrorResponse) {
            ErrorResponse error = (ErrorResponse) e.getMessage();
            if (error.getRequest() instanceof OpenRequest && error.getErrorNumber() == XrootdProtocol.kXR_NotFound) {
                e.getChannel().write(new RedirectResponse(error.getRequest(), host, port, "", ""));
                return;
            }
        }
        ctx.sendDownstream(e);
    }
}
