package com.sujiakeji.user.util.common;

import com.sujiakeji.user.constant.CommonConstants;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

@Component
public class DateTimeUtils {

    public DateTime getCurrentDateTime() {
        return new DateTime(DateTimeZone.forID(CommonConstants.DEFAULT_TIME_ZONE));
    }

}
