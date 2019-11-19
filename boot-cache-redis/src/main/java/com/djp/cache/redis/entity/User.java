package com.djp.cache.redis.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Setter
@Getter
@Accessors(chain = true)
public class User {
    private Integer id;
    private String name;
}
