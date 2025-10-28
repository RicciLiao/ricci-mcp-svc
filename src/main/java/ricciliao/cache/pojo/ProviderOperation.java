package ricciliao.cache.pojo;

import ricciliao.x.cache.pojo.AbstractCacheOperation;

import java.io.Serial;

public interface ProviderOperation {

    static Single of(ProviderCache cache) {

        return new Single(cache);
    }

    static Batch of(ProviderCache[] batch) {

        return new Batch(batch);
    }

    class Single extends AbstractCacheOperation.Single<ProviderCache> {
        @Serial
        private static final long serialVersionUID = -5686402731559236165L;

        public Single() {
        }

        public Single(ProviderCache cache) {
            super(cache);
        }
    }

    class Batch extends AbstractCacheOperation.Batch<ProviderCache> {
        @Serial
        private static final long serialVersionUID = -6738583214712630689L;

        public Batch() {
        }

        public Batch(ProviderCache[] data) {
            super(data);
        }
    }

}
