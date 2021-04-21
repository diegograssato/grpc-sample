package org.dtux.agenda.domain;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(callSuper = false)
public class Response<T> implements Serializable {
    private List<T> data;
    private ResponseLinks links;
    private Meta meta;
}
