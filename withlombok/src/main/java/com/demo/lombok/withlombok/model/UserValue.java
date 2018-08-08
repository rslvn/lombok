/**
 * 
 */
package com.demo.lombok.withlombok.model;

import lombok.NonNull;
import lombok.Value;

/**
 * @author resulav
 *
 */
@Value
public class UserValue {
	@NonNull
	private final Long id;
	private String name;
}
