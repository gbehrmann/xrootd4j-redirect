Channel handler plugin for xrootd4j and dCache
==============================================

This is a channel handler plugin for xrootd4j and dCache.

To compile the plugin, run:

    mvn package


The plugin can be tested by loading it with the xrootd4j standalone
server available from http://github.com/gbehrmann/xrootd4j:

    java -Dlog=debug 
         -jar /path/to/xrootd4j/xrootd4j-standalone-1.3.1-jar-with-dependencies.jar \
         --plugins target/xrootd4j-redirect-plugin-1.0-SNAPSHOT-standalone/ \
         --handler authn:none,redirect
