package com.zero.code.generation.util;

import org.springframework.stereotype.Component;

/**
 * @author herenpeng
 * @since 2020-11-15 15:58
 */
@Component
public class CamelCaseUtils {

    /**
     * 下划线分隔符
     */
    private static final char SEPARATOR = '_';

    /**
     * 小驼峰命名，转换为下划线命名
     *
     * @param string 小驼峰命名名称
     * @return 下划线命名名称
     */
    public String toUnderlineName(String string) {
        if (string == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            if ((i > 0) && Character.isUpperCase(ch)) {
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(ch));
        }
        return sb.toString();
    }

    /**
     * 下划线命名，转换为小驼峰命名
     *
     * @param string 下划线命名名称
     * @return 小驼峰命名名称
     */
    public String toCamelCase(String string) {
        if (string == null) {
            return null;
        }
        string = string.toLowerCase();
        StringBuilder sb = new StringBuilder(string.length());
        boolean upperCase = false;
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            if (ch == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(ch));
                upperCase = false;
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线命名，转换为大驼峰命名
     *
     * @param string 下划线命名名称
     * @return 大驼峰命名名称
     */
    public String toCapitalizeCamelCase(String string) {
        if (string == null) {
            return null;
        }
        string = toCamelCase(string);
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

}
