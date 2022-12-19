package cn.fantasticmao.demo.java.lang.jsr269;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * SoonDeprecatedProcessor
 *
 * @author fantasticmao
 * @since 2022-12-19
 */
public class SoonDeprecatedProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(SoonDeprecated.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_17;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(SoonDeprecated.class)) {
            SoonDeprecated soonDeprecated = element.getAnnotation(SoonDeprecated.class);
            processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING,
                String.format("Uses API scheduled for removal in future version '%s'", soonDeprecated.version()),
                element);
        }
        return true;
    }
}
