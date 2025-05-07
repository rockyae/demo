package com.example.demo.algo;
public class BuyCandy {
    public static int maxUnbuyableCandy() {
        // 大包糖果数量
        int bigPack = 32;
        // 小包糖果数量
        int smallPack = 27;
        // 从 1 开始枚举
        int n = 1;
        // 记录连续可购买的数量
        int consecutiveBuyable = 0;
        while (true) {
            boolean canBuy = false;
            // 枚举大包糖果的数量
            for (int i = 0; i <= n / bigPack; i++) {
                // 枚举小包糖果的数量
                for (int j = 0; j <= n / smallPack; j++) {
                    if (i * bigPack + j * smallPack == n) {
                        canBuy = true;
                        break;
                    }
                }
                if (canBuy) {
                    break;
                }
            }
            if (canBuy) {
                consecutiveBuyable++;
            } else {
                consecutiveBuyable = 0;
            }

            if (consecutiveBuyable == bigPack) {
                return n - bigPack;
            }
            n++;
        }
    }

    public static void main(String[] args) {
        int result = maxUnbuyableCandy();
        System.out.println("不能购买的最大糖果数是: " + result);
    }
}
