package com.kstt.admin.config.datasource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 动态数据源上下文
 */
public final class DynamicDataSourceContextHolder {

    private static final ThreadLocal<DynamicDataSourceType> CONTEXT = ThreadLocal.withInitial(() -> DynamicDataSourceType.MASTER);
    private static final AtomicBoolean HAS_SLAVE = new AtomicBoolean(false);

    private DynamicDataSourceContextHolder() {
    }

    public static void useMaster() {
        CONTEXT.set(DynamicDataSourceType.MASTER);
    }

    public static void useSlave() {
        if (HAS_SLAVE.get()) {
            CONTEXT.set(DynamicDataSourceType.SLAVE);
        } else {
            useMaster();
        }
    }

    public static DynamicDataSourceType getDataSourceType() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }

    public static void setHasSlave(boolean hasSlave) {
        HAS_SLAVE.set(hasSlave);
    }

    public static boolean hasSlave() {
        return HAS_SLAVE.get();
    }
}
