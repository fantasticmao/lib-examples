module cn.fantasticmao.demo.java.lang {
    requires jdk.compiler;
    requires jdk.unsupported;

    requires cn.fantasticmao.demo.java.designpattern;

    requires static jsr305;

    exports cn.fantasticmao.demo.java.lang;
    exports cn.fantasticmao.demo.java.lang.ast;
    exports cn.fantasticmao.demo.java.lang.classloader;
    exports cn.fantasticmao.demo.java.lang.collection;
    exports cn.fantasticmao.demo.java.lang.concurrent;
    exports cn.fantasticmao.demo.java.lang.concurrent.simulation;
    exports cn.fantasticmao.demo.java.lang.generic;
    exports cn.fantasticmao.demo.java.lang.innerclass;
    exports cn.fantasticmao.demo.java.lang.io;
    exports cn.fantasticmao.demo.java.lang.io.net;
    exports cn.fantasticmao.demo.java.lang.java8;
    exports cn.fantasticmao.demo.java.lang.oom;
    exports cn.fantasticmao.demo.java.lang.spi;

    provides javax.annotation.processing.AbstractProcessor with cn.fantasticmao.demo.java.lang.ast.HelloProcessor;
}