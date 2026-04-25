//package com.example.demo.cache;
//
///**
// * @Title: CaffeineDemo
// * @Author rockyae
// * @Package com.example.demo.cache
// * @Date 2025/10/8 22:18
// * @description:
// */
//import com.github.benmanes.caffeine.cache.*;
//import com.github.benmanes.caffeine.cache.stats.CacheStats;
//import lombok.Getter;
//
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.Executor;
//import java.util.concurrent.TimeUnit;
//
//public class CaffeineDemo {
//    public static void main(String[] args) throws Exception {
//        // 1. 基础缓存（手动存取值）
//        Cache<String, String> basicCache = Caffeine.newBuilder()
//                .maximumSize(10_000) // 最大缓存数量
//                .expireAfterWrite(5, TimeUnit.MINUTES) // 写入后5分钟过期
//                .build();
//
//        // 存值
//        basicCache.put("name", "caffeine");
//        // 取值（不存在返回null）
//        String name = basicCache.getIfPresent("name");
//        // 取值（不存在时执行函数计算）
//        String key1 = basicCache.get("key", k -> computeValue(k));
//
//
//        // 2. 加载式缓存（自动加载数据）
//        LoadingCache<String, User> loadingCache = Caffeine.newBuilder()
//                .maximumSize(1000)
//                .expireAfterAccess(10, TimeUnit.MINUTES) // 访问后10分钟过期
//                .refreshAfterWrite(1, TimeUnit.MINUTES) // 1分钟后自动刷新（不阻塞读）
//                .recordStats() // 开启统计
//                .removalListener((key, value, cause) -> { // 移除监听器
//                    System.out.printf("Key %s 被移除，原因：%s%n", key, cause);
//                })
//                .build(new CacheLoader<String, User>() {
//                    @Override
//                    public User load(String userId) { // 缓存不存在时加载数据
//                        return fetchUserFromDB(userId);
//                    }
//                });
//
//        // 自动加载并获取（若不存在则调用load方法）
//        User user = loadingCache.get("1001");
//        // 批量获取
//        loadingCache.getAll(List.of("1001", "1002", "1004"));
//
//        // 打印统计信息（命中率、加载时间等）
//        CacheStats stats = loadingCache.stats();
//        System.out.println("命中率：" + stats.hitRate());
//        System.out.println("平均加载时间：" + stats.averageLoadPenalty() + "ns");
//        User user2 = loadingCache.get("1004");
//
//        // 3. 异步缓存（非阻塞加载）
//        AsyncLoadingCache<String, User> asyncCache = Caffeine.newBuilder()
//                .expireAfterWrite(1, TimeUnit.HOURS)
//                .buildAsync(new AsyncCacheLoader<String, User>() {
//                    @Override
//                    public CompletableFuture<User> asyncLoad(String userId, Executor executor) {
//                        // 异步加载（如调用远程API）
//                        return CompletableFuture.supplyAsync(() -> fetchUserFromRemote(userId), executor);
//                    }
//                });
//
//        // 异步获取（返回CompletableFuture）
//        CompletableFuture<User> futureUser = asyncCache.get("1003");
//        futureUser.thenAccept(u -> System.out.println("异步获取用户：" + u.getName()));
//
//        // 阻塞等待结果（实际使用中建议用异步回调）
//        User asyncResult = futureUser.get();
//    }
//
//    // 模拟数据库查询
//    private static User fetchUserFromDB(String userId) {
//        System.out.println("从数据库加载用户：" + userId);
//        return new User(userId, "用户" + userId);
//    }
//
//    // 模拟远程服务调用
//    private static User fetchUserFromRemote(String userId) {
//        System.out.println("从远程服务加载用户：" + userId);
//        return new User(userId, "远程用户" + userId);
//    }
//
//    // 模拟计算值
//    private static String computeValue(String key) {
//        return "计算结果：" + key;
//    }
//
//    // 实体类
//    static class User {
//        private String id;
//        @Getter
//        private String name;
//
//        public User(String id, String name) {
//            this.id = id;
//            this.name = name;
//        }
//
//    }
//}
//
