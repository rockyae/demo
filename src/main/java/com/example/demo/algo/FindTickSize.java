package com.example.demo.algo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class FindTickSize {

    @Data
    @AllArgsConstructor
    public static class PriceTier {
        private BigDecimal price;
        private BigDecimal tickSize;
    }

    public static int getTickBetweenPrice(String priceTickSizeRule, BigDecimal first, BigDecimal second) {
        if (priceTickSizeRule == null || priceTickSizeRule.isEmpty()) {
            throw new IllegalArgumentException("priceTickSizeRule不能为空!");
        }
        if (first == null || second == null) {
            throw new IllegalArgumentException("价格不能为空!");
        }
        if (first.compareTo(second) == 0) {
            return 0;
        }
        BigDecimal min = first.min(second);
        BigDecimal max = first.max(second);
        BigDecimal defaultTickSize;
        int tickCount = 0;
        String[] rule = priceTickSizeRule.split(",");
        int N = Integer.parseInt(rule[0]);
        defaultTickSize = new BigDecimal(rule[1]);
        if(N == 0){
            tickCount = (max.subtract(min).divide(defaultTickSize, RoundingMode.DOWN)).intValue();
            return tickCount;
        }
        //1.存储端点
        List<PriceTier> tiers = new ArrayList<>();
        int temp = 2;
        tiers.add(new PriceTier(new BigDecimal(Integer.MIN_VALUE), defaultTickSize));//负无穷
        for (int i = 1; i <= N; i++) {
            tiers.add(new PriceTier(new BigDecimal(rule[temp]), new BigDecimal(rule[temp+1])));
            temp += 2;
        }
        //右端点，正无穷
        tiers.add(new PriceTier(new BigDecimal(Integer.MAX_VALUE), null));
        ArrayList<Integer> postions = new ArrayList<>();
        //2.枚举n+1个区间
        for(int i=1; i<=N+1; i++){
          //根据min,max所处的区间确定i
           if(min.compareTo(tiers.get(i-1).getPrice())>=0 && min.compareTo(tiers.get(i).getPrice()) <=0){
               postions.add(i-1);
           }
           if(max.compareTo(tiers.get(i-1).getPrice())>=0 && max.compareTo(tiers.get(i).getPrice()) <=0){
               postions.add(i-1);
           }
        }
        //3.计算tick
        int start = postions.get(0);
        int end = postions.get(1);
        //如果位于同一区间
        if(start == end){
            tickCount += (max.subtract(min).divide(tiers.get(start).getTickSize(), RoundingMode.DOWN)).intValue();
            return tickCount;
        }
        //位于不同区间
        for(int i=start; i<=end; i++){
            if(i == start){
                tickCount += tiers.get(i+1).getPrice().subtract(min).divide(tiers.get(i)
                                .getTickSize(), RoundingMode.DOWN).intValue();
            }else if(i == end){
                tickCount += max.subtract(tiers.get(i).getPrice()).divide(tiers.get(i)
                        .getTickSize(), RoundingMode.DOWN).intValue();
            }else{
                tickCount += tiers.get(i+1).getPrice().subtract(tiers.get(i).getPrice()).
                        divide( tiers.get(i).getTickSize(), RoundingMode.DOWN).intValue();
            }
        }
        return tickCount;
    }

    public static void main(String[] args) {
//        String priceTickSizeRule = "3,0.25,5,0.5,6,1,9,2";
//        BigDecimal first = new BigDecimal("4.75");
//        BigDecimal second = new BigDecimal("17");
        String priceTickSizeRule = "1,0.25,5,0.5";
        BigDecimal first = new BigDecimal("0");
        BigDecimal second = new BigDecimal("3");
        int tickBetweenPrice = getTickBetweenPrice(priceTickSizeRule, first, second);
        System.out.println(tickBetweenPrice);
    }
}
