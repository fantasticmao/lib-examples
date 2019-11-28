package priv.mm.apache.maven;

import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

/**
 * MavenInvokerDemo
 *
 * @author maomao
 * @since 2019-11-28
 */
public class MavenInvokerDemo {

    static {
        System.setProperty("maven.home", "/usr/local/Cellar/maven/3.6.0/libexec");
    }

    public static void main(String[] args) throws MavenInvocationException {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("./pom.xml"));
        request.setGoals(Arrays.asList("clean"));
        Properties properties = new Properties();
        //properties.setProperty("maven.test.skip", "true");
        request.setProperties(properties);

        Invoker invoker = new DefaultInvoker();
        InvocationResult result = invoker.execute(request);

        if (result.getExitCode() != 0) {
            throw new IllegalStateException("Build failed!");
        }
    }
}
