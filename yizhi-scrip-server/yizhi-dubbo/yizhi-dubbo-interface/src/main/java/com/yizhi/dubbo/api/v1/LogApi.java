package com.yizhi.dubbo.api.v1;

public interface LogApi {
    void save(Long id, String requestURI, String toString);
}
