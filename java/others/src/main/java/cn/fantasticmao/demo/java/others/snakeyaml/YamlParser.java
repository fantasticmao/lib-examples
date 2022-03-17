package cn.fantasticmao.demo.java.others.snakeyaml;

import cn.fantasticmao.demo.java.others.Employee;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

/**
 * YamlParser
 *
 * @author fantasticmao
 * @see <a href="https://bitbucket.org/snakeyaml/snakeyaml/wiki/Home">SnakeYAML Wiki</a>
 * @since 2022-03-16
 */
public class YamlParser {
    private final Yaml yaml;

    public YamlParser() {
        this.yaml = new Yaml(new Constructor(Employee.class));
    }

    public Employee parse(InputStream input) {
        return this.yaml.load(input);
    }
}
