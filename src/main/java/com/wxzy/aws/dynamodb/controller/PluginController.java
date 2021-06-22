package com.wxzy.aws.dynamodb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.validator.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxzy.aws.dynamodb.model.enums.GardenSubtypesEnum;
import com.wxzy.aws.dynamodb.model.enums.TreeSubtypesEnum;
import com.wxzy.aws.dynamodb.model.pojo.DeviceZone;
import com.wxzy.aws.dynamodb.service.DeviceZoneService;
import com.wyze.common.exception.GeneralException;
import com.wyze.common.request.BodyReaderHttpServletRequestWrapper;
import com.wyze.common.response.ResultCode;
import com.wyze.common.response.ResultModel;
import com.wyze.common.response.ResultUtil;
import com.wyze.common.util.EncryptUtil;
import com.wyze.common.util.HMacMD5Util;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/plugin/irrigation")
@Slf4j
public class PluginController {

    private static final String PLAN_DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    private DeviceZoneService deviceZoneService;

    @PostMapping("/zone")
    public ResultModel saveZone(@RequestBody @Valid final DeviceZone deviceZone, final BindingResult result,
        HttpServletRequest request) {
        if (result.hasErrors()) {
            throw new GeneralException(ResultCode.ParameterError, result.getFieldError().getDefaultMessage());
        }
        List<DeviceZone.GardenSubtypes> gardenSubtypes = deviceZone.getGardenSubtypes();
        if (gardenSubtypes != null && gardenSubtypes.size() > 0) {
            for (DeviceZone.GardenSubtypes gardenSub : gardenSubtypes) {
                GardenSubtypesEnum gardenSubtype = GardenSubtypesEnum.queryByType(gardenSub.getSubtype());
                if (gardenSubtype == null) {
                    throw new GeneralException(ResultCode.ParameterError,
                        "Required String parameter 'garden_subtypes.subtype' is not present");
                }
                String plantDate = gardenSub.getPlantDate();
                if (!(DateValidator.getInstance().isValid(plantDate, PLAN_DATE_FORMAT, false))) {
                    throw new GeneralException(ResultCode.ParameterError,
                        "Required String parameter 'garden_subtypes.plant_date' must be format of yyyy-mm-dd");
                }
            }
        }

        List<DeviceZone.TreeSubtypes> treeSubtypes = deviceZone.getTreeSubtypes();
        if (treeSubtypes != null && treeSubtypes.size() > 0) {
            for (DeviceZone.TreeSubtypes subtypes : treeSubtypes) {
                TreeSubtypesEnum treeSubtype = TreeSubtypesEnum.queryByType(subtypes.getSubtype());
                if (treeSubtype == null) {
                    throw new GeneralException(ResultCode.ParameterError,
                        "Required String parameter 'tree_subtypes.subtype' is not present");
                }
                String plantDate = subtypes.getPlantDate();
                if (!(DateValidator.getInstance().isValid(plantDate, PLAN_DATE_FORMAT, false))) {
                    throw new GeneralException(ResultCode.ParameterError,
                        "Required String parameter 'tree_subtypes.plant_date' must be format of yyyy-mm-dd");
                }
            }
        }

        try {
            final BodyReaderHttpServletRequestWrapper requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
            String accessToken =
                "lvtx.Tl3UIsNWk0v9ULkhw4PDf0bNr7C+yHPVWr95enUEX9IHqP0SJlSbYRKN/v+8/mD4ga/Xd9cOszRG43Dhomskk0IZRAo58jV9qxkiqac5sWz0BhP779mnR1Svab5l6WFCRym+p/NV0O+cyphyeEFiDRdu0cbGiVhmMjA8FIwu8HTvoVsSpSDZahswxeqIRgjAu9KQOA==";
            String appKey = "XXXXXXXXXXXXXXXXX";
            final String md5 = EncryptUtil.getMD5(accessToken + appKey);
            final String oldSign = HMacMD5Util.HMacMD5Signature(md5, HMacMD5Util.getSignBody(requestWrapper));
            System.out.println(oldSign + "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");

        } catch (Exception e) {
            e.printStackTrace();
        }
        // this.deviceZoneService.saveZone(deviceZone);
        // this.deviceZoneService.saveZoneWithoutSqs(deviceZone);
        return ResultUtil.success();
    }
}
