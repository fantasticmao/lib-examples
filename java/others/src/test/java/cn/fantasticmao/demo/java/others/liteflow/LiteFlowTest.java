package cn.fantasticmao.demo.java.others.liteflow;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.core.FlowExecutorHolder;
import com.yomahub.liteflow.entity.data.DefaultSlot;
import com.yomahub.liteflow.entity.data.LiteflowResponse;
import com.yomahub.liteflow.property.LiteflowConfig;
import org.junit.Assert;
import org.junit.Test;

/**
 * LiteFlowTest
 *
 * @author fantasticmao
 * @see <a href="https://liteflow.yomahub.com/pages/v2.6.X/07dc9a/">LiteFlow 执行器</a>
 * @since 2023-02-15
 */
public class LiteFlowTest {

    private final FlowExecutor flowExecutor;

    public LiteFlowTest() {
        LiteflowConfig config = new LiteflowConfig();
        config.setRuleSource("liteflow.json");
        this.flowExecutor = FlowExecutorHolder.loadInstance(config);
    }

    @Test
    public void chain1() {
        LiteflowResponse<DefaultSlot> response = flowExecutor.execute2Resp("chain1");
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void chain2() {
        String toNodeId = "B";
        LiteflowResponse<DefaultSlot> response = flowExecutor.execute2Resp("chain2", toNodeId);
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void chain3() {
        String toNodeId = "C";
        LiteflowResponse<DefaultSlot> response = flowExecutor.execute2Resp("chain2", toNodeId);
        Assert.assertTrue(response.isSuccess());
    }
}
