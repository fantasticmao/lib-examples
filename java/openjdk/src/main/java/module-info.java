module cn.fantasticmao.demo.java.openjdk {
    requires jdk.unsupported;
    requires java.management;

    requires jol.core;
    requires jmh.core;
    requires jmh.generator.annprocess;
    requires static jsr305;

    exports cn.fantasticmao.demo.java.openjdk.jmh;
    exports cn.fantasticmao.demo.java.openjdk.jol;
}