package priv.mm.disruptor.asynclogger;

/**
 * LogEvent
 *
 * @author maomao
 * @since 2019-09-01
 */
public class LogEvent {
    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
