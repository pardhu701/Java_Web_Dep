package com.example.api;



import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.Level;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.classic.filter.ThresholdFilter;
import org.slf4j.LoggerFactory;

public class LogConfig {

    public static void configure() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.reset(); // reset old configuration

        // INFO Appender (INFO, WARN, ERROR)
        FileAppender infoAppender = new FileAppender();
        infoAppender.setContext(context);
        infoAppender.setFile("logs/info.log");

        PatternLayoutEncoder infoEncoder = new PatternLayoutEncoder();
        infoEncoder.setContext(context);
        infoEncoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5level %logger - %msg%n");
        infoEncoder.start();
        infoAppender.setEncoder(infoEncoder);

        ThresholdFilter infoFilter = new ThresholdFilter();
        infoFilter.setLevel("INFO");
        infoFilter.start();
        infoAppender.addFilter(infoFilter);
        infoAppender.start();

        // ERROR Appender (ERROR only)
        FileAppender errorAppender = new FileAppender();
        errorAppender.setContext(context);
        errorAppender.setFile("logs/error.log");

        PatternLayoutEncoder errorEncoder = new PatternLayoutEncoder();
        errorEncoder.setContext(context);
        errorEncoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5level %logger - %msg%n");
        errorEncoder.start();
        errorAppender.setEncoder(errorEncoder);

        ThresholdFilter errorFilter = new ThresholdFilter();
        errorFilter.setLevel("ERROR");
        errorFilter.start();
        errorAppender.addFilter(errorFilter);
        errorAppender.start();

        // ROOT LOGGER
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.DEBUG); // capture all logs DEBUG and above
        root.addAppender(infoAppender);
        root.addAppender(errorAppender);
    }
}


//import ch.qos.logback.classic.Logger;
//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
//import ch.qos.logback.classic.filter.LevelFilter;
//import ch.qos.logback.classic.Level;
//import ch.qos.logback.core.FileAppender;
//import ch.qos.logback.core.spi.FilterReply;
//
//import org.slf4j.LoggerFactory;
//
//public class LogConfig {
//
//    public static void configure() {
//        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
//
//        // ---- INFO FILE APPENDER ----
//        FileAppender infoAppender = new FileAppender();
//        infoAppender.setContext(context);
//        infoAppender.setFile("logs/info.log");
//
//        PatternLayoutEncoder infoEncoder = new PatternLayoutEncoder();
//        infoEncoder.setContext(context);
//        infoEncoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5level %logger - %msg%n");
//        infoEncoder.start();
//
//        LevelFilter infoFilter = new LevelFilter();
//        infoFilter.setLevel(Level.INFO);
//        infoFilter.setOnMatch(FilterReply.ACCEPT);
//        infoFilter.setOnMismatch(FilterReply.DENY);
//        infoFilter.start();
//
//        infoAppender.addFilter(infoFilter);
//        infoAppender.setEncoder(infoEncoder);
//        infoAppender.start();
//
//        // ---- ERROR FILE APPENDER ----
//        FileAppender errorAppender = new FileAppender();
//        errorAppender.setContext(context);
//        errorAppender.setFile("logs/error.log");
//
//        PatternLayoutEncoder errorEncoder = new PatternLayoutEncoder();
//        errorEncoder.setContext(context);
//        errorEncoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5level %logger - %msg%n");
//        errorEncoder.start();
//
//        LevelFilter errorFilter = new LevelFilter();
//        errorFilter.setLevel(Level.ERROR);
//        errorFilter.setOnMatch(FilterReply.ACCEPT);
//        errorFilter.setOnMismatch(FilterReply.DENY);
//        errorFilter.start();
//
//        errorAppender.addFilter(errorFilter);
//        errorAppender.setEncoder(errorEncoder);
//        errorAppender.start();
//
//        // ---- ROOT LOGGER ----
//        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
//        root.addAppender(infoAppender);
//        root.addAppender(errorAppender);
//    }
//}

