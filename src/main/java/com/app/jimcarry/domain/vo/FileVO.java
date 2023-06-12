package com.app.jimcarry.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FileVO {
    public static final String ABSOLUTE_PATH = "/C:/upload";
    private Long fileId;
    private String filePath;
    private String fileOrgName;
    private String fileUuid;
}
