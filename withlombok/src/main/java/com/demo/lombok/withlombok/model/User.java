package com.demo.lombok.withlombok.model;

import lombok.*;

/**
 * Created by resulav on 06.08.2018.
 */

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class User {
    @NonNull
    private Long id;
    private String name;
}
