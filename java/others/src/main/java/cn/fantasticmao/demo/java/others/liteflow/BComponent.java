package cn.fantasticmao.demo.java.others.liteflow;

import com.yomahub.liteflow.core.NodeComponent;

/**
 * BComponent
 *
 * @author fantasticmao
 * @since 2023-02-15
 */
public class BComponent extends NodeComponent {

    @Override
    public void process() throws Exception {
        System.out.println("LiftFlow Component B");
    }
}
