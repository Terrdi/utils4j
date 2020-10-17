package org.terrdi.util;

public class MathUtil {
    /**
     * 将比较结果转换成标准比较结果
     * @param result
     * @return
     */
    public static int toComparableResult(int result) {
        if (result == 0) return result;
        else if (result > 0) return 1;
        else return -1;
    }
}
