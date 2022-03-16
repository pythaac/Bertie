package com.pythaac.bertie.time;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Seoul implements BertieTimeHandler{
    public Seoul() {
    }

    @Override
    public Timestamp getStandardCurrentTime() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        return Timestamp.valueOf(now);
    }
}
