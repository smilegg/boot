package top.djp.mby.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Test {

    private Long id;

    private String userId;

}
