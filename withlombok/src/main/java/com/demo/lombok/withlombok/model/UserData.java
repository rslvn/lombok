/**
 * 
 */
package com.demo.lombok.withlombok.model;

import lombok.Data;
import lombok.NonNull;

/**
 * @author resulav
 *
 */
@Data
public class UserData {
    @NonNull
    private Long id;
    private String name;
}
