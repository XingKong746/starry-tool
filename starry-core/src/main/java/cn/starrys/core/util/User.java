package cn.starrys.core.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * creationTime: 2023/2/27 19:38 .
 *
 * @author XingKong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;

    private int age;

    private int gender;

}
