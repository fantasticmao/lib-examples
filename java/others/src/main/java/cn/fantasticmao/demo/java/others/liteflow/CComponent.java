package cn.fantasticmao.demo.java.others.liteflow;

import com.yomahub.liteflow.core.NodeComponent;

/**
 * CComponent
 *
 * @author fantasticmao
 * @since 2023-02-15
 */
public class CComponent extends NodeComponent {

    @Override
    public void process() throws Exception {
        System.out.println("LiftFlow Component C");
    }
}
