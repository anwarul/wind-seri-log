package com.wind.LogsToRedis_329.Utlis;

/**
 * Created by habib on 27/07/2022.
 */
public class Constants {
    public static final String ENCRYPTION_SALT = "$2a$05$JbwNv3933Jwp1Ioo/MK8Xu";
    public static final String LOGGED_USER_ID_HEADER_KEY = "x-user-id";
    public static final String LOGGED_USERNAME_HEADER_KEY = "x-username";
    public static final String LOGGED_WALLET_HEADER_KEY = "x-wallet";
    public static final String LOGGED_SCW_HEADER_KEY = "x-scw";
    public static final String LOGGED_TOKEN_HEADER_KEY = "x-token";
    public static final String FCM_TOKEN_HEADER_KEY = "x-fcm";
    public static final String CLIENT_IP_ADDRESS = "x-forwarded-for";
    public static final String SIMPLE_DATE_FORMAT_API= "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String DATE_FORMAT_PLATFORM_CUSTOMER_API="yyyy-MM-dd'T'HH:mm:ss.SSSSSSX";
    public static final String SIMPLE_DATE_API= "MM-dd-yyyy HH:mm:ss";
    public static final String MESSAGE_FILE_PATH = "/messages_en.properties";
    public static final int TEMP_TOKEN_VALIDITY = 20;
    public static final String INTERNAL_SERVICE_ACCESS_PARAM_CHECK_KEY = "internalServiceCall";
    public static final String INTERNAL_SERVICE_ACCESS_PARAM_SECRET_KEY = "secret";
    public static final int USER_WRONG_PIN_MAX_ATTEMPT_TIME = 3;
    public static final int OTP_PER_IP_WITH_PHONE_PER_HOUR_MAX_ATTEMPT_TIME = 3;
    public static final String BORDER_STYLE = "style='border-top: 1px solid #808080a6; padding:5px'";
    public static final String NEW_DEVICE_LOGIN_EMAIL_HEADER = "We noticed a new login to your WIND {app}";
    public static final String NEW_DEVICE_LOGIN_EMAIL_BODY = "Hi {name},"
            + "<br><br>"
            + "<div style='font-size: 18px; font-weight: 600;'>A new login was detected for your Wind {app}.</div>"
            + "<br><br>"
            + "<b>Login Details:</b> <br>"
            + "<table>"
            + "<tbody>"
            + "<tr><td>Email:</td><td><b>{emailAddress}</b></td></tr>"
            + "<tr><td>Date:</td><td><b>{loginTime}</b></td></tr>"
            + "<tr><td>Device:</td><td><b>{device} ({deviceType})</b></td></tr>"
            + "<tr><td>OS:</td><td><b>{os}</b></td></tr>"
            + "<tr><td>IP address:</td><td><b>{ipAddress}</b></td></tr>"
            + "</tbody>"
            + "</table>"
            + "<br>"
            + "<b>Don't recognize this activity?</b> Reset your PIN immediately. Resetting your PIN will log you out on every device."
            + "<br><br>"
            + "For any further help, <a href='mailto:support@wind.app' target='_blank'>contact</a> us."
            + "<br><br>"
            + "Best regards,<br>"
            + "The Wind Team";

    public static final int PROFILE_CREATION_KEY_EXPIRY_TTL_IN_MINS = 59;

    public static final String AUTH0_ERROR_PATTERN = ":::type:::";
}
