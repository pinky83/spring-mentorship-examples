package org.pinky83.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@Data
@RequiredArgsConstructor
public class BaseEntity {
    private Integer id;

    public boolean isNew(){
        return this.id==null;
    }
}
