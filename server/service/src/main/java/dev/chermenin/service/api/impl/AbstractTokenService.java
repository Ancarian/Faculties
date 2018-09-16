package dev.chermenin.service.api.impl;

import dev.chermenin.model.impl.PasswordResetToken;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public abstract class AbstractTokenService {

    boolean isExpired(Date date) {
        Calendar cal = Calendar.getInstance();
        return (date.getTime() - cal.getTime().getTime()) <= 0;
    }


    Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, getExpiration());
        return new Date(cal.getTime().getTime());
    }

    abstract protected int getExpiration();
}
