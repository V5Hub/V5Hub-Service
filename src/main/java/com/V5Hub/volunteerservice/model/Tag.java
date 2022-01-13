package com.V5Hub.volunteerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    /**
     * 唯一id
     */
    private int id;
    /**
     * tag内容
     */
    private String text;
}
