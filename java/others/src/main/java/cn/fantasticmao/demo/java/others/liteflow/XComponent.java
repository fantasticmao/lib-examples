package cn.fantasticmao.demo.java.others.liteflow;

import com.yomahub.liteflow.core.NodeCondComponent;

/**
 * XComponent
 *
 * @author fantasticmao
 * @since 2023-02-15
 */
public class XComponent extends NodeCondComponent {

    @Override
    public String processCond() throws Exception {
        return this.getSlot().getRequestData();
    }
}
