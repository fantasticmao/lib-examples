package cn.fantasticmao.demo.java.openjdk.jol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.management.ManagementFactory;

/**
 * JolUtil
 *
 * @author fantasticmao
 * @since 2020-05-11
 */
public class JolUtil {

    public static String getCurrentThreadInfo() throws IOException {
        final String threadName = Thread.currentThread().getName();
        final String processName = ManagementFactory.getRuntimeMXBean().getName();
        final String pid = processName.split("@")[0];
        final String[] cmd = new String[]{
            "/bin/bash",
            "-c",
            String.format("jstack %s | grep -e '^\"%s\"'", pid, threadName)
        };
        Process process = Runtime.getRuntime().exec(cmd);
        try (Reader reader = new InputStreamReader(process.getInputStream());
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            return bufferedReader.readLine();
        }
    }

}
