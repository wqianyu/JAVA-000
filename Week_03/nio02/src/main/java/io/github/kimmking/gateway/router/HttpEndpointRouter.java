package io.github.kimmking.gateway.router;

import java.util.List;

/**
 * 随机路由算法
 */
public interface HttpEndpointRouter {
    
    String route(List<String> endpoints);
    
}
