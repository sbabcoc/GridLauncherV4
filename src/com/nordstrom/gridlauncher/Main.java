package com.nordstrom.gridlauncher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.os.CommandLine;

import com.nordstrom.common.file.PathUtils;
import com.nordstrom.common.jar.JarUtils;

public class Main {
	
    private static final String[] DEPENDENCY_CONTEXTS = {
            "org.openqa.selenium.grid.Main",
            "org.openqa.selenium.io.Zip",
            "org.openqa.selenium.remote.http.Route",
            "org.openqa.selenium.net.Urls",
            "org.openqa.selenium.json.Json",
            "com.google.common.base.Utf8",
            "com.beust.jcommander.Strings",
            "io.opentelemetry.sdk.autoconfigure.SpiUtil",
            "io.opentelemetry.sdk.autoconfigure.spi.Ordered",
            "io.opentelemetry.api.trace.Span",
            "io.opentelemetry.api.logs.Logger",
            "io.opentelemetry.api.events.EventEmitter",
            "io.opentelemetry.sdk.trace.SdkSpan",
            "io.opentelemetry.context.Scope",
            "io.opentelemetry.sdk.metrics.View",
            "io.opentelemetry.sdk.logs.LogLimits",
            "io.opentelemetry.sdk.common.Clock",
            "io.opentelemetry.semconv.trace.attributes.SemanticAttributes",
            "io.opentelemetry.sdk.OpenTelemetrySdk",
            "org.zeromq.Utils",
            "dev.failsafe.Call",
            "graphql.Assert",
            "org.slf4j.MDC",
            "io.netty.channel.Channel",
            "io.netty.util.Timer",
            "io.netty.handler.ssl.SslUtils",
            "org.slf4j.impl.StaticLoggerBinder",
            "ch.qos.logback.core.Layout",
            "io.netty.buffer.ByteBuf",
            "io.netty.handler.codec.http.Cookie",
            "io.netty.handler.codec.Headers",
            "com.google.common.util.concurrent.internal.InternalFutures",
    		"org.openqa.selenium.chrome.ChromeDriver",
            "net.bytebuddy.matcher.ElementMatcher",
            "org.openqa.selenium.chromium.ChromiumDriver",
            "org.dataloader.DataLoader"};

	public static void main(String[] args) {
        List<String> argsList = new ArrayList<>();
        
        // specify Grid launcher class name
        argsList.add("org.openqa.selenium.grid.Bootstrap");
        argsList.add("standalone");
        
        // get dependency context paths
        List<String> contextPaths = JarUtils.getContextPaths(DEPENDENCY_CONTEXTS);
        // extract classpath specification
        String classPath = contextPaths.remove(0);
        // for each specified Java agent...
        for (String agentSpec : contextPaths) {
            // ... specify a 'javaagent' argument
            argsList.add(0, agentSpec);
        }
        
        // specify Java class path
        argsList.add(0, classPath);
        argsList.add(0, "-cp");
        
        String executable = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        CommandLine process = new CommandLine(executable, argsList.toArray(new String[0]));
        process.setEnvironmentVariable("PATH", PathUtils.getSystemPath());
        process.executeAsync();
        process.waitForProcessStarted(300, TimeUnit.SECONDS);
	}

}
