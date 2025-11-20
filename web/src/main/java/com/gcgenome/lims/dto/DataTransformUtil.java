package com.gcgenome.lims.dto;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.regexp.shared.RegExp;
import lombok.experimental.UtilityClass;

import java.util.Date;

@UtilityClass
public final class DataTransformUtil {
    private DateTimeFormat DEFAULT_DATE_FORMAT = DateTimeFormat.getFormat("yyyy-MM-dd");
    private DateTimeFormat DEFAULT_DATETIME_FORMAT = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
    private final RegExp PATIENT_CODE_WITH_SEX = RegExp.compile("^(\\d{6})(-)*[0-9*]{1,7}");
    private final RegExp PATIENT_CODE_WITHOUT_SEX = RegExp.compile("^(\\d{6})$");

    public String formatSampleId(Double id) {
        if (id == null) return null;
        else {
            String cast = String.valueOf(id);
            if (cast.length() == 15) {
                String var10000 = cast.substring(0, 8);
                return var10000 + "-" + cast.substring(8, 11) + "-" + cast.substring(11);
            } else return cast;
        }
    }

    public static String formatDate(Long epoch) {
        return epoch == null ? null : DEFAULT_DATE_FORMAT.format(new Date(epoch));
    }

    public static String formatDateTime(Long epoch) {
        return epoch == null ? null : DEFAULT_DATETIME_FORMAT.format(new Date(epoch));
    }
}
