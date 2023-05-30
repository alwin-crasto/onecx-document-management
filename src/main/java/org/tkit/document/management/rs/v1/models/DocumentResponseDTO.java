package org.tkit.document.management.rs.v1.models;

import java.util.HashMap;

import org.tkit.quarkus.rs.models.TraceableDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@java.lang.SuppressWarnings("java:S2160")
public class DocumentResponseDTO extends TraceableDTO {

    private HashMap<String, Integer> attachmentResponse;
}
