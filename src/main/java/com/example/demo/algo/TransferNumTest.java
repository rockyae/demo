package com.example.demo.algo;

public class TransferNumTest {
    // 将十进制数转换为指定进制的字符串表示
    public static String convertDecimalToBase(int decimalNumber, int base) {
        // 检查目标进制是否在有效范围内（2到16之间）
        if (base < 2 || base > 16) {
            throw new IllegalArgumentException("Base must be between 2 and 16");
        }

        // 如果十进制数为0，直接返回字符串 "0"
        if (decimalNumber == 0) {
            return "0";
        }

        // 用于构建最终结果的StringBuilder
        StringBuilder result = new StringBuilder();

        // 循环进行转换
        while (decimalNumber > 0) {
            // 取余数
            int remainder = decimalNumber % base;

            // 将余数转换为对应的字符（0-9 或 A-F）
            char digitChar = (remainder < 10) ? (char) ('0' + remainder) : (char) ('A' + remainder - 10);

            // 将字符插入到结果的开头，确保正确的顺序
            result.insert(0, digitChar);

            // 更新十进制数为其除以进制的结果
            decimalNumber /= base;
        }

        // 返回结果的字符串表示
        return result.toString();
    }

    public static void main(String[] args) {
        // 要转换的十进制数
        int decimalNumber = 10;

        // 目标进制（这里是八进制）
        int base = 4;

        // 调用方法进行转换，并输出结果
        String result = convertDecimalToBase(decimalNumber, base);
        System.out.println("Decimal " + decimalNumber + " in base " + base + ": " + result);
    }
}
