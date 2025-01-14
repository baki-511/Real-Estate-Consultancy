package com.real.estate.service;

import com.real.estate.entity.Info;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InfoService {
    Info addInfo(Info info);
    
    Info getInfoById(Integer infoId);
    
    Info updateInfo(Info info);
}
