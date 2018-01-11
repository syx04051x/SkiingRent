package com.alphaz.core.pojo.viewmodel.privilege;

import com.alphaz.core.pojo.dto.MenuDTO;
import com.alphaz.core.pojo.dto.OperationDTO;

import java.util.List;
import java.util.Map;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel
 * User: C0dEr
 * Date: 2017/6/14
 * Time: 下午3:06
 * Description:This is a class of com.alphaz.core.pojo.viewmodel
 */
public class MenuOperationModel {
    public Map<String, List<String>> namePair;
    public Map<MenuDTO, List<OperationDTO>> keyPair;

    public Map<String, List<String>> getNamePair() {
        return namePair;
    }

    public void setNamePair(Map<String, List<String>> namePair) {
        this.namePair = namePair;
    }

    public Map<MenuDTO, List<OperationDTO>> getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(Map<MenuDTO, List<OperationDTO>> keyPair) {
        this.keyPair = keyPair;
    }
}
