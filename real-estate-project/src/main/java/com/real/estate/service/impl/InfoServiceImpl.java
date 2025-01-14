package com.real.estate.service.impl;

import com.real.estate.entity.Info;
import com.real.estate.exception.InfoNotFoundException;
import com.real.estate.repository.InfoRepository;
import com.real.estate.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private InfoRepository infoRepository;
    
    @Override
    public Info addInfo(Info info) {
        return infoRepository.save(info);
    }
    
    @Override
    public Info getInfoById(Integer infoId) {
        return infoRepository.findById(infoId)
                .orElseThrow(() -> new InfoNotFoundException(infoId));
    }
    
    @Override
    public Info updateInfo(Info info) {
        getInfoById(info.getInfoId());
        return infoRepository.save(info);
    }
}
