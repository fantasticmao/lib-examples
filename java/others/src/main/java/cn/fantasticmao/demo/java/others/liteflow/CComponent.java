package cn.fantasticmao.demo.java.others.liteflow;

import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * CComponent
 *
 * @author fantasticmao
 * @since 2023-02-15
 */
@Slf4j
public class CComponent extends NodeComponent {

    @Override
    public void process() throws Exception {
        log.info("LiftFlow Component C");
    }
}
