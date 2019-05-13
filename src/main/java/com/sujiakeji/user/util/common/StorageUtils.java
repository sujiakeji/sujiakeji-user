package com.sujiakeji.user.util.common;

import com.sujiakeji.user.config.StorageConfig;
import com.sujiakeji.user.dto.common.StorageUriDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageUtils {

    @Autowired
    private StorageConfig storageConfig;

    public StorageUriDto convertToDto(String path) {
        StorageUriDto dto = null;
        if (path != null && path.length() > 0) {
            dto = new StorageUriDto();
            dto.setPath(path);
            dto.setUrl(storageConfig.getBaseUrl() + path);
        }
        return dto;
    }

}