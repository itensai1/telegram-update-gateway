package com.tensai.telegram.service.internal;

import com.tensai.telegram.dto.command.CmsCommand;
import org.springframework.core.io.Resource;

public interface InternalService {
    void dispatch(CmsCommand command);
    Resource getFileResource(String fileId);
}
