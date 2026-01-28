package ricciliao.mcp.pojo;


import java.io.Serial;
import java.io.Serializable;

public abstract class ProviderCacheMessage<T extends Serializable> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1405990681223875295L;

    private T data;

    protected ProviderCacheMessage() {
    }

    protected ProviderCacheMessage(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Single of(ProviderCache cache) {

        return new Single(cache);
    }

    public static Batch of(ProviderCache[] cache) {

        return new Batch(cache);
    }

    public static class Single extends ProviderCacheMessage<ProviderCache> {
        @Serial
        private static final long serialVersionUID = -5489299128428705085L;

        public Single() {
        }

        private Single(ProviderCache data) {
            super(data);
        }
    }

    public static class Batch extends ProviderCacheMessage<ProviderCache[]> {
        @Serial
        private static final long serialVersionUID = -5489299128428705085L;

        public Batch() {
        }

        private Batch(ProviderCache[] data) {
            super(data);
        }
    }
}
