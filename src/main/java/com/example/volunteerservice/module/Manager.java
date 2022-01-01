package com.example.volunteerservice.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    /**
     * 管理员本身也是user
     */
    private User user;
}
