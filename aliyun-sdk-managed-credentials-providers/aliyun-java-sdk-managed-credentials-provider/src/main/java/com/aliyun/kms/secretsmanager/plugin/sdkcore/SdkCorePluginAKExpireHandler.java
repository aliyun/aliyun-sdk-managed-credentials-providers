package com.aliyun.kms.secretsmanager.plugin.sdkcore;

import com.aliyun.kms.secretsmanager.plugin.common.AKExpireHandler;
import com.aliyuncs.exceptions.ClientException;

import java.util.HashSet;
import java.util.Set;


public class SdkCorePluginAKExpireHandler implements AKExpireHandler<ClientException> {

    private final static String[] AK_EXPIRE_ERROR_CODES = new String[]{"InvalidAccessKeyId.NotFound", "InvalidAccessKeyId"};

    private Set<String> errorCodeSet = new HashSet<>();

    public SdkCorePluginAKExpireHandler() {
        for (String akExpireCode : AK_EXPIRE_ERROR_CODES) {
            errorCodeSet.add(akExpireCode);
        }
    }

    public SdkCorePluginAKExpireHandler(String[] akExpireErrorCodes) {
        if (akExpireErrorCodes == null || akExpireErrorCodes.length == 0) {
            for (String akExpireCode : AK_EXPIRE_ERROR_CODES) {
                errorCodeSet.add(akExpireCode);
            }
        } else {
            for (String akExpireCode : akExpireErrorCodes) {
                errorCodeSet.add(akExpireCode);
            }
        }
    }

    @Override
    public boolean judgeAKExpire(ClientException e) {
        return errorCodeSet.contains(e.getErrCode());
    }

}
