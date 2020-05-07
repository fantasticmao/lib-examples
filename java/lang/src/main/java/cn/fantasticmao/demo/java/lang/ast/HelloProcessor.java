package cn.fantasticmao.demo.java.lang.ast;

import com.sun.source.tree.Tree;
import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.List;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * HelloProcessor
 *
 * <p>需要使用 Java SPI 注册服务</p>
 *
 * @author maomao
 * @see javax.annotation.processing.Processor
 * @since 2020-01-09
 */
public class HelloProcessor extends AbstractProcessor {
    private static final String GREET_METHOD_NAME = "greet";

    private Trees trees;
    private TreeMaker treeMaker;

    public HelloProcessor() {
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.trees = Trees.instance(processingEnv);
        this.treeMaker = TreeMaker.instance(((JavacProcessingEnvironment) processingEnv).getContext());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(HelloAnnotation.class)) {
            HelloAnnotation annotation = element.getAnnotation(HelloAnnotation.class);
            final Tree tree = trees.getTree(element);
            if (element.getKind() == ElementKind.CLASS && tree instanceof JCTree) {
                JCTree.JCClassDecl jcTree = (JCTree.JCClassDecl) tree;
                jcTree.accept(new TreeScanner() {
                    @Override
                    public void visitMethodDef(JCTree.JCMethodDecl jcMethodDecl) {
                        super.visitMethodDef(jcMethodDecl);
                        if (jcMethodDecl.name.contentEquals(GREET_METHOD_NAME)) {
                            JCTree.JCLiteral helloMessage = treeMaker.Literal("Hello " + annotation.username());
                            JCTree.JCStatement returnMessage = treeMaker.Return(helloMessage);
                            jcMethodDecl.body = treeMaker.Block(0L, List.of(returnMessage));
                        }
                    }
                });
                return true;
            } else {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                        HelloAnnotation.class.getName() + " can only be used for java class type", element);
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(HelloAnnotation.class.getName());
        return Collections.unmodifiableSet(annotationTypes);
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
